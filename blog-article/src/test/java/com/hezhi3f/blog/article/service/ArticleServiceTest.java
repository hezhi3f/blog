package com.hezhi3f.blog.article.service;


import com.hezhi3f.blog.common.entity.article.ArticleMainDTO;
import com.hezhi3f.blog.common.entity.article.ArticlePageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void testPage() {
        ArticleMainDTO dto = new ArticleMainDTO();
        dto.setPage(1);
        dto.setSize(5);
        ArticlePageVO main = articleService.main(dto);



    }


}
