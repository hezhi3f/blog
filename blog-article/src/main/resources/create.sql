use `blog`;

drop table if exists blog_article_tag;
drop table if exists `blog_article`;
drop table if exists `blog_article_kind`;
drop table if exists blog_article_body;

create table if not exists blog_article_body
(
    `id`      bigint unsigned auto_increment,
    `content` text not null,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;

create table if not exists `blog_article_kind`
(
    `id`   tinyint unsigned auto_increment,
    `kind` varchar(16) not null,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;

create table if not exists `blog_article`
(
    `id`              bigint unsigned auto_increment,
    `user_id`         bigint unsigned  not null,
    `title`           varchar(64)      not null,
    `article_kind_id` tinyint unsigned not null,
    `article_body_id` bigint unsigned  not null,
    `gmt_created`     datetime         not null,
    `gmt_modified`    datetime         default null,
    `deleted`         tinyint unsigned default 0,
    primary key (`id`),
    foreign key (`user_id`) references blog_user (`id`),
    foreign key (`article_body_id`) references blog_article_body (`id`),
    foreign key (`article_kind_id`) references blog.blog_article_kind (`id`)
) engine = InnoDB
  default charset = utf8mb4;


create table if not exists blog_article_tag
(
    `id`         bigint unsigned auto_increment,
    `article_id` bigint unsigned not null,
    `tag`        varchar(32)     not null,
    primary key (`id`),
    foreign key (`article_id`) references blog_article (`id`)
) engine = InnoDB
  default charset = utf8mb4;

insert into `blog_article_kind`(`kind`)
values ('kind1'),
       ('kind2'),
       ('kind3'),
       ('kind4'),
       ('kind5')