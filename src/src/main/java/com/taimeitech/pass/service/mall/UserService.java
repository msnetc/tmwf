package com.taimeitech.pass.service.mall;

import com.taimeitech.framework.common.dto.ActionResult;

/**
 * Created by devin on 2017/5/13.
 */
public interface UserService {
    ActionResult listUser(int pageIndex, int pageSize);
}
