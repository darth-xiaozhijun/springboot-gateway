package com.springboot.dao;

import com.springboot.base.entity.User;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectPageList(Map map);

    List<User> selectList(Map map);

    int selectCount(Map map);

    User selectOne(User t);
}