package com.tjspace.mpdemo.entity;

// 不需要你写getter， setter， 有五参数构造

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

// 无敌！
@Data
public class User {
    // mp自动生成 id mp方式，不写默认是ID_WORKER中的一种
    @TableId(type = IdType.ID_WORKER)
    // AUTO :自动增长
    // ID_WORKER : MP自带策略，生成19位值，必须要数字类型，比如long double
    // ID_WORKER_STR : MP自带策略，生成19位值，必须使用字符串类型！
    // INPUT : 用户输入
    // NONE : 没有策略，自己输入
    // UUID : 随机唯一值
    private Long id;
    private String name;
    private Integer age;
    private String email;


    // 数据库是asd_asd
    // 实体类位asdAsd!
    // TODO：自动填充功能，使用mp实现, 创建一个类，实现自动填种的接口（UserMetaObjectHandler）方法！在handler里面！
    // 添加自动填充注解, 当insert的时候自动添加
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    // 当insert或者update的时候自动添加
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    // 实现乐观锁！
    // TODO:添加version（乐观锁注解),配置 mybatics 插件在配置类config;
    @Version
    @TableField(fill = FieldFill.INSERT) // 当新数据填充的时候自动初始化
    private Integer version; // 版本号！

    // 实现逻辑删除！
    // TODO:TableLogic（逻辑删除注解),配置 mybatics 插件在配置类config;
    @TableLogic
    private Integer deleted; // 版本号！


}

