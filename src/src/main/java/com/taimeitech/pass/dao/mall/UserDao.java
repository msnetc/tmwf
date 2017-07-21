package com.taimeitech.pass.dao.mall;

import com.taimeitech.pass.entity.mall.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by devin on 2017/5/13.
 */
public interface UserDao {
    List<User> listUser(@Param("offset") int offset, @Param("pageSize") int pageSize);
}
