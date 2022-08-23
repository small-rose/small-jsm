
-- auto-generated definition
create table js_user_info
(
    id           bigint       not null comment '主键ID'
        primary key ,
    nick_name    varchar(255) null comment '用户昵称',
    nick_name_py varchar(255) null comment '用户昵称拼音',
    precommender int          not null comment '是否是推荐人1-是，0-否',
    slug         varchar(255) null comment '用户slug',
    slug_url     varchar(255) null comment '主页地址'
)ENGINE=InnoDB;

-- 专题表
create table js_subject_info
(
    id           bigint       not null comment '主键ID'
        primary key,
    subject_slug varchar(255) null comment '专题slug',
    title        varchar(255) null comment '专题名称',
    title_py     varchar(255) null comment '专题名称拼音'
)ENGINE=InnoDB;

-- auto-generated definition
create table js_subject_data_info
(
    id               bigint       not null comment '主键ID'
        primary key,
    comments         longtext     null comment '专题slug',
    lp_reward        int   default  0  not null comment '理事会赞赏',
    nick_name        varchar(255) null comment '作者昵称',
    recommender      varchar(255) null comment '推荐人',
    recommender_slug varchar(255) null comment '推荐人slug',
    rewards          longtext     null comment '赞赏数据',
    shou_date        String     null comment '收录日期',
    shou_time        datetime     null comment '收录时间',
    update_time      datetime     null comment '更新时间',
    subject_id       varchar(255) null comment '专题slug',
    title            varchar(255) null comment '文章标题',
    user_slug        varchar(255) null comment '用户slug',
    wen_id           varchar(255) null comment '文章id',
    wen_slug         varchar(255) null comment '文章slug',
    wen_url          varchar(255) null comment '文章地址'
)ENGINE=InnoDB;
