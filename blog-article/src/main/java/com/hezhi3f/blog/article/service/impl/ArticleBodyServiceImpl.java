package com.hezhi3f.blog.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.article.dao.ArticleBodyMapper;
import com.hezhi3f.blog.article.service.ArticleBodyService;
import com.hezhi3f.blog.common.entity.article.ArticleBodyPO;
import com.hezhi3f.blog.common.util.Assert;
import org.springframework.stereotype.Service;

@Service
public class ArticleBodyServiceImpl
        extends ServiceImpl<ArticleBodyMapper, ArticleBodyPO>
        implements ArticleBodyService {

    @Override
    public ArticleBodyPO save(String content) {
        ArticleBodyPO po = new ArticleBodyPO();
        po.setContent(content);
        boolean save = this.save(po);
        Assert.isTrue(save, "插入文章体失败");
        return po;
    }
}
