package com.hezhi3f.blog.article.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.article.api.UserService;
import com.hezhi3f.blog.article.dao.ArticleMapper;
import com.hezhi3f.blog.article.service.ArticleBodyService;
import com.hezhi3f.blog.article.service.ArticleKindService;
import com.hezhi3f.blog.article.service.ArticleService;
import com.hezhi3f.blog.article.service.ArticleTagService;
import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.entity.article.*;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.util.Assert;
import com.hezhi3f.blog.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl
        extends ServiceImpl<ArticleMapper, ArticlePO> implements ArticleService {
    private final UserService userService;
    private final ArticleBodyService bodyService;
    private final ArticleKindService kindService;
    private final ArticleTagService tagService;

    @Override
    public Result<Void> create(ArticleCreateDTO dto) {
        ArticlePO po = new ArticlePO();

        Long id = UserContext.get().getId();
        po.setUserId(id);
        po.setTitle(dto.getTitle());

        // 根据kind获得id
        ArticleKindPO kind = kindService.save(dto.getKind());
        po.setArticleKindId(kind.getId());

        // 插入body并获得id
        ArticleBodyPO body = bodyService.save(dto.getContent());
        po.setArticleBodyId(body.getId());

        po.setGmtCreated(new Date());
        this.save(po);

        tagService.save(po.getId(), dto.getTags());

        return ResultUtils.success();
    }

    @Override
    @Transactional
    public ArticleVO getArticleVOByArticleId(Long articleId) {
        Assert.isNotNull(articleId, "articleId不能为空");
        ArticleVO vo = new ArticleVO();
        ArticlePO po = this.getById(articleId);
        Assert.isNotNull(po, "文章不存在");

        Long userId = po.getUserId();
        Result<String> userPO = userService.getNickname(userId);

        vo.setNickName(userPO.getData());
        vo.setTitle(po.getTitle());
        vo.setGmtCreated(po.getGmtCreated());
        vo.setGmtModified(po.getGmtModified());

        ArticleKindPO kind = kindService.getById(po.getArticleKindId());
        vo.setKind(kind.getKind());

        ArticleBodyPO body = bodyService.getById(po.getArticleBodyId());
        vo.setContent(body.getContent());

        List<String> tags = tagService.getTags(articleId);
        vo.setTags(tags);

        return vo;
    }

    @Override
    public List<ArticleVO> listByUserId(Long userId) {
        List<ArticlePO> pos = this.list(Wrappers.<ArticlePO>query().eq("user_id", userId));
        return pos.stream()
                .map(ArticlePO::getId)
                .map(this::getArticleVOByArticleId)
                .collect(Collectors.toList());
    }

    @Autowired
    public ArticleServiceImpl(UserService userService,
                              ArticleBodyService bodyService,
                              ArticleKindService kindService,
                              ArticleTagService tagService) {
        this.userService = userService;
        this.bodyService = bodyService;
        this.kindService = kindService;
        this.tagService = tagService;
    }
}
