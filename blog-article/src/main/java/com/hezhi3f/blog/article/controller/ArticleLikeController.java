package com.hezhi3f.blog.article.controller;

import com.hezhi3f.blog.article.service.ArticleLikeService;
import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.service.RedisService;
import com.hezhi3f.blog.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article/like")
public class ArticleLikeController {
    private final ArticleLikeService likeService;

    @RequestMapping("/add")
    public Result<Void> add(@RequestParam Long articleId) {
        likeService.like(articleId);
        return ResultUtils.success();
    }

    @RequestMapping("/del")
    public Result<Void> del(@RequestParam Long articleId) {
        likeService.dislike(articleId);
        return ResultUtils.success();
    }

    @RequestMapping("/count")
    public Result<Long> count(@RequestParam Long articleId) {
        Long likes = likeService.count(articleId);
        return ResultUtils.success(likes);
    }


    @Autowired
    public ArticleLikeController(ArticleLikeService likeService) {
        this.likeService = likeService;
    }
}
