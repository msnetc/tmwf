package com.taimeitech.pass.dao.appoint;

import com.taimeitech.pass.entity.appoint.Appoint;

public interface AppointDao {

	/**
	 * 插入预约图书记录
	 * 
	 * @param appoint
	 * @return 插入的行数
	 */
	int insertAppoint(Appoint appoint);

	/**
	 * 通过主键查询预约图书记录，并且携带图书实体
	 * 
	 * @param bookId
	 * @param studentId
	 * @return
	 */
	Appoint getAppoint(long bookId, long studentId);

}
