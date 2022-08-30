# TODO

- 写一个接口，所有需要user id的DTO实现这个接口，通过切面自动把从token中获取的数据存入

```java
interface TokenAccess {
    void setTokenId(Long id);
}

class ArticleCreateDTO implements TokenAccess {
    private Long userId;

    @Override
    public void setTokenId(Long id) {
        this.setUserId(id);
    }

}
```