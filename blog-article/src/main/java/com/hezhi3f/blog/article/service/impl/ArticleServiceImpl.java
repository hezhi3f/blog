package com.hezhi3f.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.article.api.UserService;
import com.hezhi3f.blog.article.dao.ArticleMapper;
import com.hezhi3f.blog.article.service.ArticleBodyService;
import com.hezhi3f.blog.article.service.ArticleKindService;
import com.hezhi3f.blog.article.service.ArticleService;
import com.hezhi3f.blog.article.service.ArticleTagService;
import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.entity.article.*;
import com.hezhi3f.blog.common.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl
        extends ServiceImpl<ArticleMapper, ArticlePO> implements ArticleService {
    private final UserService userService;
    private final ArticleBodyService bodyService;
    private final ArticleKindService kindService;
    private final ArticleTagService tagService;

    @Override
    @Transactional
    public void create(ArticleCreateDTO dto) {
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

        if (dto.getTags() != null) {
            tagService.saveBatch(po.getId(), dto.getTags());
        }

        po.setGmtCreated(new Date());
        this.save(po);

    }

    @Override
    @Transactional
    public ArticleVO getArticleVOByArticleId(Long articleId) {
        Assert.isNotNull(articleId, "articleId不能为空");
        ArticlePO po = this.getById(articleId);
        Assert.isNotNull(po, "文章不存在");
        return poToVo(po);
    }

    @Override
    public List<ArticleVO> listByUserId(Long userId) {
        List<ArticlePO> pos = this.list(Wrappers.<ArticlePO>query().eq("user_id", userId));
        return pos.stream()
                .map(ArticlePO::getId)
                .map(this::getArticleVOByArticleId)
                .collect(Collectors.toList());
    }

    @Override
    public String update(ArticleUpdateDTO dto) {
        Long articleId = dto.getArticleId();
        ArticlePO po = this.getById(articleId);
        List<String> poChange = new ArrayList<>();
        List<String> otherChange = new ArrayList<>();

        updateTitle(dto, po, poChange);
        updateBody(dto, po, otherChange);
        updateKind(dto, po, poChange);
        updateTags(dto, articleId, otherChange);

        if (poChange.isEmpty() && otherChange.isEmpty()) {
            return "未作任何改变";
        }

        StringBuilder sb = new StringBuilder();
        if (!poChange.isEmpty()) {
            this.updateById(po);
            sb.append(String.join("、", poChange)).append("修改成功");
        }

        if (!otherChange.isEmpty()) {
            sb.append(String.join("、", otherChange)).append("修改成功");
        }

        return sb.toString();
    }

    @Override
    public ArticlePageVO main(ArticleMainDTO dto) {
        ArticlePageVO vo = new ArticlePageVO();
        QueryWrapper<ArticlePO> order = Wrappers.<ArticlePO>query().orderByDesc("gmt_created");
        Page<ArticlePO> page = this.page(new Page<>(dto.getPage(), dto.getSize()), order);
        List<ArticlePO> records = page.getRecords();
        vo.setPages(page.getPages());
        vo.setSize(page.getSize());
        vo.setTotal(page.getTotal());
        vo.setCurrent(page.getCurrent());
        vo.setRecords(records.stream().map(this::poToVo).collect(Collectors.toList()));
        return vo;
    }


    private ArticleVO poToVo(ArticlePO po) {
        ArticleVO vo = new ArticleVO();

        Long userId = po.getUserId();
        String nickname = userService.getNickname(userId).getData();

        vo.setArticleId(po.getId());
        vo.setNickName(nickname);
        vo.setTitle(po.getTitle());
        vo.setGmtCreated(po.getGmtCreated());
        vo.setGmtModified(po.getGmtModified());

        ArticleKindPO kind = kindService.getById(po.getArticleKindId());
        vo.setKind(kind.getKind());

        ArticleBodyPO body = bodyService.getById(po.getArticleBodyId());
        vo.setContent(body.getContent());

        List<String> tags = tagService.getTags(po.getId())
                .stream()
                .map(ArticleTagPO::getTag)
                .collect(Collectors.toList());

        vo.setTags(tags);

        return vo;
    }

    private void updateTitle(ArticleUpdateDTO dto, ArticlePO po, List<String> msg) {
        String oldTitle = po.getTitle();
        String newTitle = dto.getTitle();

        if (!Objects.equals(oldTitle, newTitle)) {
            po.setTitle(newTitle);
            msg.add("标题");
        } else {
            po.setTitle(null);
        }
    }

    private void updateBody(ArticleUpdateDTO dto, ArticlePO po, List<String> msg) {
        ArticleBodyPO body = bodyService.getById(po.getArticleBodyId());
        String oldContent = body.getContent();
        String newContent = dto.getContent();

        if (!Objects.equals(oldContent, newContent)) {
            body.setContent(newContent);
            bodyService.updateById(body);
            msg.add("正文");
        } else {
            po.setArticleBodyId(null);
        }
    }

    private void updateKind(ArticleUpdateDTO dto, ArticlePO po, List<String> msg) {
        ArticleKindPO kind = kindService.getById(po.getArticleKindId());
        String oldKind = kind.getKind();
        String newKind = dto.getKind();
        if (!Objects.equals(oldKind, newKind)) {
            po.setArticleKindId(kindService.save(newKind).getId());
            msg.add("类型");
        } else {
            po.setArticleKindId(null);
        }
    }

    private void updateTags(ArticleUpdateDTO dto, Long articleId, List<String> msg) {
        List<ArticleTagPO> oldTagPOS = tagService.getTags(articleId);
        List<String> newTags = dto.getTags();

        List<String> same = newTags.stream()
                .filter(tag -> oldTagPOS.stream()
                        .anyMatch(tagPO -> tagPO.getTag().equals(tag)))
                .collect(Collectors.toList());
        if (same.size() == oldTagPOS.size() && same.size() == newTags.size()) {

            return;
        }

        List<ArticleTagPO> remove = oldTagPOS.stream()
                .filter(tagPO -> !same.contains(tagPO.getTag()))
                .collect(Collectors.toList());
        List<String> save = newTags.stream()
                .filter(tag -> !same.contains(tag))
                .collect(Collectors.toList());

        tagService.removeBatchByIds(remove);
        tagService.saveBatch(articleId, save);
        msg.add("标签");
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
