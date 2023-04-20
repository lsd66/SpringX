package com.lsd.myoauth2.model.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsd.myoauth2.model.entity.MyClientDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MyClientDetailMapper extends BaseMapper<MyClientDetails> {

//    @Select("select * from oauth_client_details where client_id = #{clientId}")
//    MyClientDetails selectById(String clientId);
}
