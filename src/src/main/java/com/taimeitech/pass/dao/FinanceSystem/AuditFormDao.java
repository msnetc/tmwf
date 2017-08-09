package com.taimeitech.pass.dao.FinanceSystem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

/**
 * Created by yanjie.miao on 2017/8/9.
 */
public class AuditFormDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 流程完成后清理detail表中的表单类型数据
     * @param processInstanceId
     * @return
     */
    public int deleteFormPropertyByProcessInstanceId(String processInstanceId) {
        int i = entityManager.createNativeQuery("delete from act_hi_detail where proc_inst_id_ = ? and type_ = 'FormProperty' ")
                .setParameter(1, processInstanceId).executeUpdate();
        return i;
    }

    public int deleteHistoryVariable(String processInstanceId, String variableName) {
        int i = entityManager.createNativeQuery("delete from act_hi_varinst where proc_inst_id_ = ? and name_ = ? ")
                .setParameter(1, processInstanceId).setParameter(2, variableName).executeUpdate();
        return i;
    }

    /**
     * 统计表中数量的行数
     * @param tableName
     * @return
     */
    public Long countTableRows(String tableName) {
        return ((BigInteger)entityManager.createNativeQuery("select count(*) from " + tableName).getSingleResult()).longValue();
    }
}
