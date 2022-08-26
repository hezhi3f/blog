# 博客-文章 微服务

## 文章表

blog_article

| column          | type             | memo         |
|-----------------|------------------|--------------|
| id              | bigint unsigned  | 主键id         |
| user_id         | bigint unsigned  | 作者(用户id)     |
| title           | varchar(32)      | 标题           |
| article_kind_id | tinyint unsigned | 类型id         |
| article_body_id | bigint unsigned  | 文章id         |
| gmt_created     | datetime         | 创建时间         |
| gmt_modified    | datetime         | 修改时间         |
| is_deleted      | tinyint unsigned | 是否删除         |

blog_article_body

| column  | type            | memo |
|---------|-----------------|------|
| id      | bigint unsigned |      |
| content | text            |      |

blog_article_tag

| column     | type            | memo |
|------------|-----------------|------|
| id         | bigint unsigned |      |
| article_id | bigint unsigned |      |
| tag        | varchar(32)     |      |

blog_article_kind

| column | type            | memo |
|--------|-----------------|------|
| id     | bigint unsigned |      |
| kind   | text            |      |






