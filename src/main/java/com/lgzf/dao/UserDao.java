package com.lgzf.dao;

import com.lgzf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @auther lgzf
 * @data 2020-02-26 - 16:17
 **/
@Mapper
@Repository
public interface UserDao {
    User queryByUsernameAndPassword(@Param("username") String username,
                                    @Param("password") String password);
}
