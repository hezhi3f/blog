package com.hezhi3f.blog.article.controller;

import com.hezhi3f.blog.article.service.ArticleService;
import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.entity.article.ArticleCreateDTO;
import com.hezhi3f.blog.common.entity.article.ArticlePO;
import com.hezhi3f.blog.common.entity.article.ArticleVO;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.util.ResultUtils;
import com.hezhi3f.blog.common.validate.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/create")
    public Result<Void> create(
            @Validated @RequestBody ArticleCreateDTO articleCreateDTO) {
        return articleService.create(articleCreateDTO);
    }

    @GetMapping("/get")
    public Result<ArticleVO> getArticleVOByArticleId(@RequestParam("articleId") Long articleId) {
        ArticleVO vo = articleService.getArticleVOByArticleId(articleId);
        return ResultUtils.success(vo);
    }

    @RequestMapping("/list")
    public Result<List<ArticleVO>> getLoginUserArticleList() {
        List<ArticleVO> vos = articleService.listByUserId(UserContext.get().getId());
        return ResultUtils.success(vos);
    }

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
}
