package com.hezhi3f.blog.article.controller;

import com.hezhi3f.blog.article.service.ArticleService;
import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.entity.article.*;
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

    @PostMapping("/main")
    public Result<ArticlePageVO> main(
            @Validated @RequestBody ArticleMainDTO dto) {
        ArticlePageVO vo = articleService.main(dto);
        return ResultUtils.success(vo);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result<Void> create(
            @Validated @RequestBody ArticleCreateDTO articleCreateDTO) {
        articleService.create(articleCreateDTO);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<String> update(@RequestBody ArticleUpdateDTO articleUpdateDTO) {
        String msg = articleService.update(articleUpdateDTO);
        return ResultUtils.success(msg);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Result<ArticleVO> getArticleVOByArticleId(@RequestParam("articleId") Long articleId) {
        ArticleVO vo = articleService.getArticleVOByArticleId(articleId);
        return ResultUtils.success(vo);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<List<ArticleVO>> getLoginUserArticleList() {
        List<ArticleVO> vos = articleService.listByUserId(UserContext.get().getId());
        return ResultUtils.success(vos);
    }

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
}
