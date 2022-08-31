package com.hezhi3f.blog.authority.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hezhi3f.blog.common.entity.authority.AuthorityPO;
import com.hezhi3f.blog.common.entity.user.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorityMapper extends BaseMapper<UserPO> {
}
