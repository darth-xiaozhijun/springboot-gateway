package com.springboot.dao;

import com.springboot.base.entity.Role;
import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectPageList(Map map);

    List<Role> selectList(Map map);

    int selectCount(Map map);

    Role selectOne(Role t);
}