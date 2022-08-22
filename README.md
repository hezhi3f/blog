# 个人博客
博文的前台
微服务后台

## 数据库表
### 用户表
colum|type|memo
-|-|-
id| bigint unsigned|主键id
email| varchar|唯一、登录认证
password| varchar|密码
nickname|varchar|昵称、可以重复
sex|varchar|性别
secret|varchar|jwt的加密信息
gmt_modified|datatime|上次修改
gmt_created|datatime|创建时间
# 前台

## 后台
微服务架构
### 注册中心
docker

注册中心(配置中心) nacos单机

缓存 redis


### 用户微服务 BLOG_USER_SERVICE
用户的注册、登录、修改信息、注销、登出等等



### 博文
博文的增删改查
博文的信息包括多个标签、一个分类、信息、阅读量、点赞数


