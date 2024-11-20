-- 创建库
create database if not exists test_user;

-- 切换库
use test_user;

-- 用户表
drop table if exists `user`;
create table if not exists `user`
(
    `id`            bigint                                 not null comment 'id',
    `user_account`  varchar(256)                           not null comment '账号',
    `user_password` varchar(512)                           not null comment '密码',
    `user_name`     varchar(256)                           null comment '用户昵称',
    `user_avatar`   varchar(1024)                          null comment '用户头像',
    `user_profile`  varchar(512)                           null comment '用户简介',
    `user_role`     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    `create_time`   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`     tinyint      default 0                 not null comment '是否删除'
) default charset = utf8mb4 comment '用户';
