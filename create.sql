use blog;
# 文章评论
create table `blog_article_comment`
(
    `id`               bigint unsigned auto_increment,
    `user_id`          bigint unsigned not null,
    `super_comment_id` bigint unsigned not null,
    `article_id`       bigint unsigned not null,
    `content`          varchar(128)    not null,
    `gmt_created`      datetime        not null,
    `is_deleted`       tinyint unsigned default '0',
    primary key (`id`),
    foreign key (`super_comment_id`) references `blog_article_comment` (`id`),
    foreign key (`user_id`) references `blog_user` (`id`),
    foreign key (`article_id`) references `blog_article` (`id`)
) engine = InnoDB
  default charset = utf8mb4