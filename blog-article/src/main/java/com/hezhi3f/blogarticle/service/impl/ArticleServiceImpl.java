package com.hezhi3f.blogarticle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blogarticle.dao.ArticleMapper;
import com.hezhi3f.blogarticle.service.ArticleBodyService;
import com.hezhi3f.blogarticle.service.ArticleKindService;
import com.hezhi3f.blogarticle.service.ArticleService;
import com.hezhi3f.blogarticle.service.ArticleTagService;
import com.hezhi3f.common.entity.article.*;
import com.hezhi3f.common.entity.result.Result;
import com.hezhi3f.common.util.Assert;
import com.hezhi3f.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl
        extends ServiceImpl<ArticleMapper, ArticlePO> implements ArticleService {
    private final ArticleBodyService bodyService;
    private final ArticleKindService kindService;
    private final ArticleTagService tagService;

    @Override
    public Result<Void> create(ArticleCreateDTO dto) {
        ArticlePO po = new ArticlePO();

        po.setUserId(dto.getUserId());
        po.setTitle(dto.getTitle());

        ArticleKindPO kind = kindService.create(dto.getKind());
        po.setArticleKindId(kind.getId());

        ArticleBodyPO body = bodyService.create(dto.getContent());
        po.setArticleBodyId(body.getId());

        po.setGmtCreated(new Date());
        this.save(po);

        tagService.save(po.getId(), dto.getTags());

        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result<ArticleVO> getArticleVOByArticleId(Long articleId) {
        Assert.isNotNull(articleId, "articleId不能为空");
        ArticleVO vo = new ArticleVO();
        ArticlePO po = this.getById(articleId);
        Assert.isNotNull(po, "文章不存在");
        // todo 未被删除
        Assert.isFalse(po.getDeleted(), "文章已被删除");
        // todo feign get username
        vo.setNickName(po.getUserId().toString());
        vo.setTitle(po.getTitle());

        ArticleKindPO kind = kindService.getById(po.getArticleKindId());
        vo.setKind(kind.getKind());

        ArticleBodyPO body = bodyService.getById(po.getArticleBodyId());
        vo.setContent(body.getContent());

        List<String> tags = tagService.getTags(articleId);
        vo.setTags(tags);

        vo.setGmtCreated(po.getGmtCreated());
        vo.setGmtModified(po.getGmtModified());
        return ResultUtils.success(vo);
    }

    @Autowired
    public ArticleServiceImpl(ArticleBodyService bodyService,
                              ArticleKindService kindService,
                              ArticleTagService tagService) {
        this.bodyService = bodyService;
        this.kindService = kindService;
        this.tagService = tagService;
    }
}
