package com.hezhi3f.blogarticle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blogarticle.dao.ArticleBodyMapper;
import com.hezhi3f.blogarticle.service.ArticleBodyService;
import com.hezhi3f.common.entity.article.ArticleBodyPO;
import org.springframework.stereotype.Service;

@Service
public class ArticleBodyServiceImpl
        extends ServiceImpl<ArticleBodyMapper, ArticleBodyPO>
        implements ArticleBodyService {

    @Override
    public ArticleBodyPO create(String content) {
        ArticleBodyPO po = new ArticleBodyPO();
        po.setContent(content);
        boolean save = this.save(po);
        // todo 判空
        return po;
    }
}
