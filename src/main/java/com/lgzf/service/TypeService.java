package com.lgzf.service;

import com.lgzf.pojo.Type;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-27 - 13:28
 **/

public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    int updateType(Type type);

    int deleteType(Long id);

    List<Type> getAllTypeNum();
}
