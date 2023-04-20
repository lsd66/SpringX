package com.lsd.myoauth2.model.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsd.myoauth2.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Role record);
//
//    int insertSelective(Role record);
//
//    Role selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Role record);
//
//    int updateByPrimaryKey(Role record);
}
