package com.lgzf.dao;

import com.lgzf.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-27 - 13:36
 **/
@Mapper
@Repository
public interface TypeDao {

    int saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    int deleteType(Long id);

    int updateType(Type type);

    List<Type> getAllTypeNum();
}
