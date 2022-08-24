package com.hezhi3f.bloguser.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hezhi3f.bloguser.entity.user.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
}
