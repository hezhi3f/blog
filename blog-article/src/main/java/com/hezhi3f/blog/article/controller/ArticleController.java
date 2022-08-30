package com.hezhi3f.blog.article.controller;

import com.hezhi3f.blog.article.service.ArticleService;
import com.hezhi3f.blog.common.entity.article.ArticleCreateDTO;
import com.hezhi3f.blog.common.entity.article.ArticleVO;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.validate.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/create")
    public Result<Void> create(
            @Validated @RequestBody ArticleCreateDTO articleCreateDTO) {
        articleCreateDTO.setUserId(1L);
        return articleService.create(articleCreateDTO);
    }

    @GetMapping("/get")
    public Result<ArticleVO> getArticleVOByArticleId(@RequestParam("articleId") Long articleId) {
        return articleService.getArticleVOByArticleId(articleId);
    }

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
}
