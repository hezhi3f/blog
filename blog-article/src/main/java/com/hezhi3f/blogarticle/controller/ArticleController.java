package com.hezhi3f.blogarticle.controller;

import com.hezhi3f.blogarticle.service.ArticleService;
import com.hezhi3f.common.entity.article.ArticleCreateDTO;
import com.hezhi3f.common.entity.article.ArticleVO;
import com.hezhi3f.common.entity.result.Result;
import com.hezhi3f.common.validate.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/create")
    public Result<Void> create(
            @Validated @RequestBody ArticleCreateDTO articleCreateDTO) {
//        @RequestAttribute("id") Long id
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
