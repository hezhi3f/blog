package com.hezhi3f.blog.article.controller;

import com.hezhi3f.blog.article.service.ArticleCommentService;
import com.hezhi3f.blog.common.entity.article.ArticleCommentCreateDTO;
import com.hezhi3f.blog.common.entity.article.ArticleCommentDTO;
import com.hezhi3f.blog.common.entity.article.ArticleCommentPageVO;
import com.hezhi3f.blog.common.entity.article.ArticleCommentVO;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article/comment")
public class ArticleCommentController {
    private final ArticleCommentService commentService;

    @RequestMapping("/create")
    public Result<ArticleCommentVO> create(@RequestBody ArticleCommentCreateDTO dto) {
        ArticleCommentVO vo = commentService.create(dto);
        return ResultUtils.success(vo);
    }


    @RequestMapping("/list")
    public Result<ArticleCommentPageVO> list(@RequestBody ArticleCommentDTO dto) {
        ArticleCommentPageVO vo = commentService.list(dto);
        return ResultUtils.success(vo);
    }

    @Autowired
    public ArticleCommentController(ArticleCommentService commentService) {
        this.commentService = commentService;
    }
}
