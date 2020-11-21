package com.tjspace.mpdemo.mapper;

import com.tjspace.mpdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

//MP定义好了接口， 不需要来写增删查改了！哈哈！
//接口类没有实现类， 需要在启动类来实现一个mapperScan

@Repository
public interface UserMapper extends BaseMapper<User> {

}
