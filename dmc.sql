/*
navicat mysql data transfer

source server         : 121.41.119.158
source server version : 50622
source host           : 121.41.119.158:3306
source database       : dmc

target server type    : mysql
target server version : 50622
file encoding         : 65001

date: 2016-12-27 22:23:47
*/

set foreign_key_checks=0;

-- ----------------------------
-- table structure for resource
-- ----------------------------
drop table if exists `resource`;
create table `resource` (
  `id` bigint(36) not null,
  `name` varchar(100) not null,
  `remark` varchar(200) default null,
  `seq` int(11) default null,
  `url` varchar(200) default null,
  `method` varchar(20) default null,
  `pid` bigint(36) default null,
  `type` int(11) not null,
  primary key (`id`),
  key `fk_sogl6f9lioeptbf7s105wbx82` (`pid`),
  key `fk_bjlrqegc9iu81src5vlta7p00` (`type`),
  constraint `resource_ibfk_1` foreign key (`pid`) references `resource` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of resource
-- ----------------------------
insert into `resource` values ('0', '系统管理', null, '0', null, null, null, '0');
insert into `resource` values ('1', '角色管理', '角色列表', '2', '../rolelist/rolelist.html', null, '0', '0');
insert into `resource` values ('2', '用户管理', '用户列表', '3', '../userlist/userlist.html', null, '0', '0');
insert into `resource` values ('3', '资源管理', '管理系统中所有的菜单或功能', '1', '../menu/menu.html', null, '0', '0');
insert into `resource` values ('4', '添加角色', null, '3', '/role', 'post', '1', '1');
insert into `resource` values ('5', '删除角色', null, '6', '/role/*', 'delete', '1', '1');
insert into `resource` values ('6', '编辑角色', null, '5', '/role', 'put', '1', '1');
insert into `resource` values ('7', '角色授权', null, '8', '/role/grant', 'post', '1', '1');
insert into `resource` values ('9', '添加用户', null, '3', '/user', 'post', '2', '1');
insert into `resource` values ('12', '删除用户', null, '6', '/user/*', 'delete', '2', '1');
insert into `resource` values ('13', '编辑用户', null, '5', '/user', 'put', '2', '1');
insert into `resource` values ('14', '用户修改密码', null, '11', '/user/editpwd', 'post', '2', '1');
insert into `resource` values ('15', '用户授权', null, '9', '/user/grant', 'post', '2', '1');
insert into `resource` values ('16', '添加资源', null, '4', '/resource', 'post', '3', '1');
insert into `resource` values ('17', '删除资源', null, '7', '/resource/*', 'delete', '3', '1');
insert into `resource` values ('18', '编辑资源', null, '6', '/resource', 'put', '3', '1');
insert into `resource` values ('19', '资源树列表', null, '2', '/resource/tree', 'post', '3', '1');
insert into `resource` values ('3907913782690816', '角色详情', null, null, '/role/*', 'get', '1', '1');

-- ----------------------------
-- table structure for role
-- ----------------------------
drop table if exists `role`;
create table `role` (
  `id` bigint(36) not null,
  `name` varchar(100) not null,
  `remark` varchar(200) default null,
  `seq` int(11) default null,
  `pid` bigint(36) default null,
  primary key (`id`),
  key `fk_tealaj0x99w9xj8on8ax0jgjb` (`pid`),
  constraint `role_ibfk_1` foreign key (`pid`) references `role` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of role
-- ----------------------------
insert into `role` values ('0', '超管', '超级管理员角色，拥有系统中所有的资源访问权限', '0', null);

-- ----------------------------
-- table structure for role_resource
-- ----------------------------
drop table if exists `role_resource`;
create table `role_resource` (
  `role_id` bigint(36) not null,
  `resource_id` bigint(36) not null,
  primary key (`resource_id`,`role_id`),
  key `resource_id` (`resource_id`),
  key `role_resource_ibfk_1` (`role_id`),
  constraint `role_resource_ibfk_1` foreign key (`role_id`) references `role` (`id`) on delete cascade,
  constraint `role_resource_ibfk_2` foreign key (`resource_id`) references `resource` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of role_resource
-- ----------------------------
insert into `role_resource` values ('0', '0');
insert into `role_resource` values ('0', '1');
insert into `role_resource` values ('0', '2');
insert into `role_resource` values ('0', '3');
insert into `role_resource` values ('0', '4');
insert into `role_resource` values ('0', '5');
insert into `role_resource` values ('0', '6');
insert into `role_resource` values ('0', '7');
insert into `role_resource` values ('0', '9');
insert into `role_resource` values ('0', '12');
insert into `role_resource` values ('0', '13');
insert into `role_resource` values ('0', '14');
insert into `role_resource` values ('0', '15');
insert into `role_resource` values ('0', '16');
insert into `role_resource` values ('0', '17');
insert into `role_resource` values ('0', '18');
insert into `role_resource` values ('0', '19');
insert into `role_resource` values ('0', '3907913782690816');

-- ----------------------------
-- table structure for user
-- ----------------------------
drop table if exists `user`;
create table `user` (
  `id` bigint(36) not null,
  `create_time` datetime default current_timestamp,
  `modify_time` datetime default current_timestamp,
  `name` varchar(100) not null,
  `password` varchar(100) not null,
  `username` varchar(100) not null,
  primary key (`id`),
  unique key `uk_o3uyea7py4jnln0qxrtg1qqhq` (`username`) using btree
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of user
-- ----------------------------
insert into `user` values ('0', '2015-05-02 17:50:05', '2016-12-27 21:58:01', '管理员', '96e79218965eb72c92a549dd5a330112', 'admin');
insert into `user` values ('1', '2015-05-02 17:50:06', '2016-12-27 18:34:10', 'admin1', '202cb962ac59075b964b07152d234b70', 'admin1');

-- ----------------------------
-- table structure for user_role
-- ----------------------------
drop table if exists `user_role`;
create table `user_role` (
  `user_id` bigint(20) not null,
  `role_id` bigint(20) not null,
  primary key (`user_id`,`role_id`),
  key `role_id` (`role_id`),
  key `role_id_2` (`role_id`),
  key `role_id_3` (`role_id`),
  constraint `user_role_ibfk_1` foreign key (`user_id`) references `user` (`id`) on delete cascade,
  constraint `user_role_ibfk_2` foreign key (`role_id`) references `role` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of user_role
-- ----------------------------
insert into `user_role` values ('0', '0');
