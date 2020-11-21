package com.tjspace.mpdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


// 把这个类注册到spring里去， 不然没有用
@Component
public class UserMetaObjectHandler implements MetaObjectHandler {
    // 重载接口定义的方法！

    @Override
    // 当想向数据库添加的时候，就会执行这个！
    public void insertFill(MetaObject metaObject) {
        // metaObject 元数据， 关于数据库的数据，java版本的数据库metaData
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

        // 乐观锁！
        this.setFieldValByName("version", 1, metaObject);
    }

    @Override
    // 当数据库修改的时候，就会执行这个！
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
