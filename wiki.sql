drop database if exists `wiki`;
create database `wiki`;

CREATE TABLE IF NOT EXISTS `demo` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT 'name',
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `ebook` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `category1_id` bigint(20) DEFAULT NULL COMMENT '分类1',
  `category2_id` bigint(20) DEFAULT NULL COMMENT '分类2',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `cover` varchar(200) DEFAULT NULL COMMENT '封面',
  `doc_count` int(11) DEFAULT NULL COMMENT '文档数',
  `view_count` int(11) DEFAULT NULL COMMENT '阅读数',
  `vote_count` int(11) DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `test` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT 'name',
  `password` varchar(50) DEFAULT NULL COMMENT 'password',
  PRIMARY KEY (`id`)
);


drop table if exists `doc`;
create table `doc`(
	`id` bigint not null comment 'id',
	`ebook_id` bigint not null default 0 comment '电子书id',
	`parent` bigint not null default 0 comment '父id',
	`name` varchar(50) not null comment '名称',
	`sort` int comment '顺序',
	`view_count` int default 0 comment '阅读数',
	`vote_count` int default 0 comment '点赞数',
	primary key(`id`)
);

insert into `doc` (id, ebook_id, parent, name,sort, view_count, vote_count) values(1,1,0,'文档1',1,0,0);
insert into `doc` (id, ebook_id, parent, name,sort, view_count, vote_count) values(2,1,0,'文档1.1',1,0,0);
insert into `doc` (id, ebook_id, parent, name,sort, view_count, vote_count) values(3,1,0,'文档2',2,0,0);
insert into `doc` (id, ebook_id, parent, name,sort, view_count, vote_count) values(4,1,3,'文档2.1',1,0,0);
insert into `doc` (id, ebook_id, parent, name,sort, view_count, vote_count) values(5,1,3,'文档2.2',2,0,0);
insert into `doc` (id, ebook_id, parent, name,sort, view_count, vote_count) values(6,1,5,'文档2.2.1',1,0,0);

# 分类
drop table if exists `category`;
create table `category`(
	`id` bigint not null comment 'id',
	`parent` bigint not null default 0 comment '父id',
	`name` varchar(50) not null comment '名称',
	`sort` int comment '顺序',
	primary key(`id`)
);