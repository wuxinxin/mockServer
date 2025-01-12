package com.example.demo.dao;

import com.example.demo.domain.MockTemplates;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Mapper
@Component
public interface MockTemplatesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MockTemplates record);

    int insertSelective(MockTemplates record);

    MockTemplates selectByPrimaryKey(Long id);

    MockTemplates selectByMethodAndUrl(@Param("method") String method, @Param("url") String url);

    int updateByPrimaryKeySelective(MockTemplates record);

    int updateByPrimaryKeyWithBLOBs(MockTemplates record);

    int updateByPrimaryKey(MockTemplates record);
}