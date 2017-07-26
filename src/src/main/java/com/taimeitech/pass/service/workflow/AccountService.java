package com.taimeitech.pass.service.workflow;
import com.taimeitech.pass.common.exception.OrganizationNotFoundException;
import com.taimeitech.pass.common.exception.ServiceException;
import org.activiti.engine.identity.User;

import java.util.List;

public interface AccountService {
    /**
     * 添加用户并[同步其他数据库]
     * <ul>
     * <li>step 1: 保存系统用户，同时设置和部门的关系</li>
     * <li>step 2: 同步用户信息到activiti的identity.User，同时设置角色</li>
     * </ul>
     *
     * @param user              用户对象
     * @param orgId             部门ID
     * @param roleIds           角色ID集合
     * @param synToActiviti     是否同步到Activiti数据库，通过配置文件方式设置，使用属性：account.user.add.syntoactiviti
     * @throws OrganizationNotFoundException    关联用户和部门的时候从数据库查询不到哦啊部门对象
     * @throws  Exception                       其他未知异常
     */
    public void save(User user, Long orgId, List<Long> roleIds, boolean synToActiviti)
            throws OrganizationNotFoundException, ServiceException, Exception;

    /**
     * 删除用户
     * @param userId        用户ID
     * @param synToActiviti     是否同步到Activiti数据库，通过配置文件方式设置，使用属性：account.user.add.syntoactiviti
     * @throws Exception
     */
    public void delete(Long userId, boolean synToActiviti) throws ServiceException, Exception;

    /**
     * 同步用户、角色数据到工作流
     * @throws Exception
     */
    public void synAllUserAndRoleToActiviti() throws Exception;

    /**
     * 删除工作流引擎Activiti的用户、角色以及关系
     * @throws Exception
     */
    public void deleteAllActivitiIdentifyData() throws Exception;
}
