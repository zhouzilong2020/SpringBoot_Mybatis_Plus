package com.tjspace.mpdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjspace.mpdemo.entity.User;
import com.tjspace.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MpdemoApplicationTests {

    // 注入数据
    @Autowired
    private UserMapper userMapper;

    // 测试功能：user表中所有数据
    @Test
    void selectAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    void addUser() {
        // 新建user
        User user = new User();
        user.setAge(20);
        user.setEmail("lutianyiSB");
        user.setName("lutianyiSB");
        // 插入
        int insert = userMapper.insert(user);
        // 返回插入的行数
        System.out.println(insert);
    }

    @Test
    void updateUser() {
        // 需要修改的用户的对象！
        User user = new User();
        //id是long类型, 设置需要修改的用户key
        user.setId(1329992099597361153L);
        // 需要修改的信息
        user.setAge(120);
        // 传入需要修改的user对象
        int row = userMapper.updateById(user);
        System.out.println(row);
    }


    // 乐观锁是先查询，在修改，和上面的直接updateUser不一样
    @Test
    void testOptimisticLock() {
        // 需要修改的用户的对象！
        User user = userMapper.selectById(1329992099597361153L);
        user.setAge(-100);
        // 传入需要修改的user对象
        int row = userMapper.updateById(user);
        System.out.println(row);

        // 乐观锁生效的话version应当+1
    }

    // 批量查询
    @Test
    void testBatchSelect() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
        System.out.println(users);
    }

    // API方式查询，前端json传参
    // 条件查询
    @Test
    void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 19);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }


    // 分页测试！
    @Test
    void testPagination() {
        // 传入当前页， 和每页显示的数量
        Page<User> page = new Page(1, 3);
        userMapper.selectPage(page, null);
        // 或读到的数据都在page中
        System.out.println(page.getCurrent());  // 当前页面
        System.out.println(page.getRecords());  //每一页的数据集合
        System.out.println(page.getSize());     //总共数据大小
        System.out.println(page.getPages());    //总页数
        System.out.println(page.hasNext());     //有没有下一页
        System.out.println(page.getPages());    //有没有上一页
    }


    // 物理删除, 删掉库;逻辑删除，设置一个标识位，如果为真则不返回，逻辑删除
    // 但是一旦配置好了逻辑删除， deleteById自动执行逻辑删除
    @Test
    void testPhysicalDelete() {
        // delete方法同select
        int result = userMapper.deleteById(1L);
        System.out.println(result);
    }

    // 逻辑删除，设置一个标识位，如果为真则不返回，逻辑删除
    // TODO:如何查询逻辑删除掉的东西？需要使用原生SQL来执行
    @Test
    void testLogicDelete() {
        // delete方法同select
        int result = userMapper.deleteById(1329999688343183362L);
        System.out.println(result);
    }


    // 复杂的mybatis查询
    @Test
    void testSelectQuery() {
        // 创建QueryWrapper
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 实现的是comparable的接口
        // ge 大于/gt 大于等于/le 小于等于/lt 小于等于
        // eq 等于/ ne不等于
        // between 之间
        // like 模糊查询！
        // orderBy 排序
        // last 拼接
        // 查询制定的列

        // 年龄大于等于30
        wrapper
                .ge("age", "30")
                .eq("name", "Jack");

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }


}
