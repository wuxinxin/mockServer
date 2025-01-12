package com.example.demo.dao;

import com.example.demo.domain.MockData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Component
public interface MockDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MockData record);

    int insertSelective(MockData record);

    MockData selectByPrimaryKey(Long id);

    List<MockData> selectByTemplatesID(@Param("templates_id") Long templates_id);

    int updateByPrimaryKeySelective(MockData record);

    int updateByPrimaryKeyWithBLOBs(MockData record);

    int updateByPrimaryKey(MockData record);
}