package com.hezhi3f.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.article.api.UserService;
import com.hezhi3f.blog.article.dao.ArticleCommentMapper;
import com.hezhi3f.blog.article.service.ArticleCommentService;
import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.entity.article.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleCommentServiceImpl
        extends ServiceImpl<ArticleCommentMapper, ArticleCommentPO> implements ArticleCommentService {
    private static final long COMMENT_PAGE_SIZE = 5;
    private final UserService userService;

    @Override
    public void create(ArticleCommentCreateDTO dto) {
        ArticleCommentPO po = new ArticleCommentPO();
        po.setArticleId(dto.getArticleId());
        po.setSuperCommentId(dto.getSuperCommentId());
        po.setCommentUserId(UserContext.get().getId());
        po.setToUserId(dto.getToUserId());
        po.setContent(dto.getContent());
        po.setGmtCreated(new Date());
    }

    @Override
    public ArticleCommentPageVO list(ArticleCommentDTO dto) {
        QueryWrapper<ArticleCommentPO> wrapper = Wrappers.<ArticleCommentPO>query();

        wrapper.eq("article_id", dto.getArticleId());

        if (dto.getSuperCommentId() == null) {
            wrapper.isNull("super_comment_id");
        } else {
            wrapper.eq("super_comment_id", dto.getSuperCommentId());
        }

        Page<ArticleCommentPO> page = this.page(new Page<>(dto.getCurrent(), COMMENT_PAGE_SIZE), wrapper);
        ArticleCommentPageVO vo = new ArticleCommentPageVO();
        vo.setPages(page.getPages());
        vo.setTotal(page.getTotal());

        List<ArticleCommentPO> records = page.getRecords();
        vo.setComments(records.stream().map(this::poToVo).collect(Collectors.toList()));
        return vo;
    }

    private ArticleCommentVO poToVo(ArticleCommentPO po) {
        ArticleCommentVO vo = new ArticleCommentVO();
        vo.setId(po.getId());
        String commentUserNickname = userService.getNickname(po.getCommentUserId()).getData();
        vo.setCommentUserNickname(commentUserNickname);
        String toUserNickname = userService.getNickname(po.getToUserId()).getData();
        vo.setToUserNickname(toUserNickname);
        long count = this.count(Wrappers.<ArticleCommentPO>query().eq("super_comment_id", po.getId()));
        vo.setChildCommentCount(count);
        vo.setContent(po.getContent());
        vo.setGmtCreated(po.getGmtCreated());
        return vo;
    }

    @Autowired
    public ArticleCommentServiceImpl(UserService userService) {
        this.userService = userService;
    }
}
