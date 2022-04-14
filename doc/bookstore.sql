create database bookstore;

create table author
(
    id int auto_increment
        primary key,
    username varchar(32) null,
    password varchar(64) null,
    email varchar(64) null
);

create table blog
(
    id int auto_increment
        primary key,
    title varchar(64) null,
    author_id int null
);

create table book
(
    id int auto_increment
        primary key,
    name varchar(32) null comment '书名',
    category_id int null comment '分类id',
    author_id varchar(32) null comment '作者',
    status varchar(32) null comment '状态',
    book_cover varchar(128) null comment '封面地址',
    sub_category_id varchar(32) null comment '子分类id',
    intro varchar(1024) null comment '简介'
)
    comment '书籍信息';

create index name
    on book (name);

create index name_2
    on book (name);

create table book_category
(
    id int auto_increment comment '分类id'
        primary key,
    category_name varchar(32) null comment '分类名称',
    category_icon varchar(64) null comment '分类的图标'
)
    comment '书籍分类';

create table chapter
(
    id int auto_increment
        primary key,
    no int null comment '章节编码',
    name varchar(128) null comment '章节名称',
    create_time date null,
    content mediumtext null,
    book_id int not null
)
    comment '章节';

create table comment
(
    id int null,
    post_id int null,
    content varchar(4096) null
);

create table post
(
    id int auto_increment
        primary key,
    content varchar(4096) null,
    blog_id int null,
    draft int null
);

create table record
(
    id int auto_increment
        primary key,
    name varchar(32) null,
    create_date date null
);

create table register_center
(
    id int auto_increment
        primary key,
    service_name varchar(128) null,
    service_url varchar(256) null
);

create table sys_user
(
    id bigint auto_increment comment '主键'
        primary key,
    user_name varchar(60) null comment '用户名',
    real_name varchar(60) null comment '真实姓名',
    password varchar(60) null comment '密码',
    phone varchar(60) null comment '手机',
    email varchar(60) null comment '邮件',
    avatar varchar(60) null comment '头像',
    status int null comment '状态',
    CREATED_BY varchar(32) null comment '创建人',
    CREATED_TIME datetime null comment '创建时间',
    UPDATED_BY varchar(32) null comment '更新人',
    UPDATED_TIME datetime null comment '更新时间'
)
    comment '系统用户表';

create table task_relation
(
    id int auto_increment
        primary key,
    task_id int null,
    related_task_id int null
);

create table user_sign
(
    id bigint not null comment '主键'
        primary key,
    user_key char(36) null comment '用户ID',
    sign_month char(6) default '190001' not null comment '签到月份',
    sign_record int default 0 not null comment '签到记录',
    sign_count int default 0 not null comment '连续签到天数',
    last_sign_date date null comment '上次签到日期',
    replenish_sign tinyint default 0 not null comment '补签次数'
);