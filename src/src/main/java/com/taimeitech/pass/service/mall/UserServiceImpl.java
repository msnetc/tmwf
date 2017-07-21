package com.taimeitech.pass.service.mall;

import com.taimeitech.framework.cache.RedisDao;
import com.taimeitech.framework.common.dto.ActionResult;
import com.taimeitech.framework.common.dto.ErrorInfo;
import com.taimeitech.pass.dao.mall.UserDao;
import com.taimeitech.pass.entity.mall.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by devin on 2017/5/13.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisDao redisDao;

    @Override
    public ActionResult listUser(int pageIndex, int pageSize) {
        String usersCacheKey = "user:all:" + pageIndex + "to" + pageSize;

        List<User> users;
        Object cacheData = redisDao.getObject(usersCacheKey);
        if (cacheData != null) {
            users = (List<User>) cacheData;
            return new ActionResult(true, null, users);
        }

        users = userDao.listUser(pageIndex, pageSize);
        if (!CollectionUtils.isEmpty(users)) {
            redisDao.setObjectWithSecond(usersCacheKey, users, 5 * 60);
            return new ActionResult(true, null, users);
        } else {
            return new ActionResult(false, Arrays.asList(new ErrorInfo(-1, "数据为空")), null);
        }
    }

}
