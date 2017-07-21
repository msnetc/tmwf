package com.taimeitech.pass.service.multids;

import com.taimeitech.pass.dao.multids.BookMultiDsDao;
import com.taimeitech.pass.entity.appoint.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhengjun.zhang on 2017/6/7.
 */
@Service
public class BookMultiDsService {
    @Autowired
    private BookMultiDsDao multiDsDao;

    //若使用多数据源，请在属性文件总进行配置
//    @TargetDataSource("ds_write")
    public List<Book> listBook1(){
        return multiDsDao.listBook1();
    }

//    @TargetDataSource("ds_read")
    public List<Book> listBook2(){
        return multiDsDao.listBook2();
    }
}
