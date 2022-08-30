# 个人博客

博文的前台
微服务后台

## 数据库表

### 用户表

| colum        | type           | memo     |
|--------------|----------------|----------|
| id           | bigintunsigned | 主键id     |
| email        | varchar        | 唯一、登录认证  |
| password     | varchar        | 密码       |
| nickname     | varchar        | 昵称、可以重复  |
| sex          | varchar        | 性别       |
| secret       | varchar        | jwt的加密信息 |
| gmt_modified | datatime       | 上次修改     |
| gmt_created  | datatime       | 创建时间     |

# 前台

## 后台

微服务架构

### 注册中心

docker

注册中心(配置中心) nacos单机

缓存 redis

### 用户微服务 BLOG_USER_SERVICE

#### 全局参数校验
- Required 必需的参数，不能为空
- Length 参数的长度，必须为String类型
- Type 正则表达式，

[//]: # (TODO 加入一些常用的正则，如纯数字、邮箱、密码等等)

#### 用户的注册、登录

登录和注册一起判断，如果是邮箱未注册，就去注册；如果是已经注册就走登录

、修改信息、注销、登出等等



### 博文

博文的增删改查
博文的信息包括多个标签、一个分类、信息、阅读量、点赞数


### 权限认证微服务

userid？email？secret？login？


