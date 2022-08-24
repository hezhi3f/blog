create database `blog`;
use `blog`;

drop table if exists `blog_user`;
create table `blog_user`
(
    `id`           bigint unsigned auto_increment,
    `email`        varchar(36) not null,
    `password`     varchar(36) not null,
    `nickname`     varchar(36) not null,
    `gender`       tinyint unsigned default 0,
    `secret`       varchar(36) not null,
    `gmt_created`  datetime    not null,
    `gmt_modified` datetime         default null,
    `is_deleted`   tinyint unsigned default 0,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4