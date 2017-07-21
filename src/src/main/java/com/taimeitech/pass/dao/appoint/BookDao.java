package com.taimeitech.pass.dao.appoint;

import com.taimeitech.pass.entity.appoint.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookDao {

	//命名规则：操作动词 + ValueObject 名

	/**
	 * 通过ID查询单本图书
	 * 
	 * @param bookId
	 * @return
	 */
	Book getBookById(long bookId);

	/**
	 * 查询所有图书
	 * 
	 * @param offset 查询起始位置
	 * @param limit 查询条数
	 * @return
	 */
	List<Book> listBook(@Param("offset") int offset, @Param("limit") int limit);

	/**
	 * 减少馆藏数量
	 * 
	 * @param bookId
	 * @return 如果影响行数等于>1，表示更新的记录行数
	 */
	int updateBookNumber(long bookId);

}
