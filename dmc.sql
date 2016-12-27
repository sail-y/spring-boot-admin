/*
Navicat MySQL Data Transfer

Source Server         : 121.41.119.158
Source Server Version : 50622
Source Host           : 121.41.119.158:3306
Source Database       : dmc

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2016-12-27 22:23:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `ID` bigint(36) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `PID` bigint(36) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_sogl6f9lioeptbf7s105wbx82` (`PID`),
  KEY `FK_bjlrqegc9iu81src5vlta7p00` (`type`),
  CONSTRAINT `resource_ibfk_1` FOREIGN KEY (`PID`) REFERENCES `resource` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('0', '系统管理', null, '0', null, null, null, '0');
INSERT INTO `resource` VALUES ('1', '角色管理', '角色列表', '2', '../roleList/roleList.html', null, '0', '0');
INSERT INTO `resource` VALUES ('2', '用户管理', '用户列表', '3', '../userList/userList.html', null, '0', '0');
INSERT INTO `resource` VALUES ('3', '资源管理', '管理系统中所有的菜单或功能', '1', '../menu/menu.html', null, '0', '0');
INSERT INTO `resource` VALUES ('4', '添加角色', null, '3', '/role', 'post', '1', '1');
INSERT INTO `resource` VALUES ('5', '删除角色', null, '6', '/role/*', 'delete', '1', '1');
INSERT INTO `resource` VALUES ('6', '编辑角色', null, '5', '/role', 'put', '1', '1');
INSERT INTO `resource` VALUES ('7', '角色授权', null, '8', '/role/grant', 'post', '1', '1');
INSERT INTO `resource` VALUES ('9', '添加用户', null, '3', '/user', 'post', '2', '1');
INSERT INTO `resource` VALUES ('12', '删除用户', null, '6', '/user/*', 'delete', '2', '1');
INSERT INTO `resource` VALUES ('13', '编辑用户', null, '5', '/user', 'put', '2', '1');
INSERT INTO `resource` VALUES ('14', '用户修改密码', null, '11', '/user/editPwd', 'post', '2', '1');
INSERT INTO `resource` VALUES ('15', '用户授权', null, '9', '/user/grant', 'post', '2', '1');
INSERT INTO `resource` VALUES ('16', '添加资源', null, '4', '/resource', 'post', '3', '1');
INSERT INTO `resource` VALUES ('17', '删除资源', null, '7', '/resource/*', 'delete', '3', '1');
INSERT INTO `resource` VALUES ('18', '编辑资源', null, '6', '/resource', 'put', '3', '1');
INSERT INTO `resource` VALUES ('19', '资源树列表', null, '2', '/resource/tree', 'post', '3', '1');
INSERT INTO `resource` VALUES ('3907913782690816', '角色详情', null, null, '/role/*', 'get', '1', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` bigint(36) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `PID` bigint(36) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_tealaj0x99w9xj8on8ax0jgjb` (`PID`),
  CONSTRAINT `role_ibfk_1` FOREIGN KEY (`PID`) REFERENCES `role` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', '超管', '超级管理员角色，拥有系统中所有的资源访问权限', '0', null);

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `ROLE_ID` bigint(36) NOT NULL,
  `RESOURCE_ID` bigint(36) NOT NULL,
  PRIMARY KEY (`RESOURCE_ID`,`ROLE_ID`),
  KEY `RESOURCE_ID` (`RESOURCE_ID`),
  KEY `role_resource_ibfk_1` (`ROLE_ID`),
  CONSTRAINT `role_resource_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `role_resource_ibfk_2` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `resource` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('0', '0');
INSERT INTO `role_resource` VALUES ('0', '1');
INSERT INTO `role_resource` VALUES ('0', '2');
INSERT INTO `role_resource` VALUES ('0', '3');
INSERT INTO `role_resource` VALUES ('0', '4');
INSERT INTO `role_resource` VALUES ('0', '5');
INSERT INTO `role_resource` VALUES ('0', '6');
INSERT INTO `role_resource` VALUES ('0', '7');
INSERT INTO `role_resource` VALUES ('0', '9');
INSERT INTO `role_resource` VALUES ('0', '12');
INSERT INTO `role_resource` VALUES ('0', '13');
INSERT INTO `role_resource` VALUES ('0', '14');
INSERT INTO `role_resource` VALUES ('0', '15');
INSERT INTO `role_resource` VALUES ('0', '16');
INSERT INTO `role_resource` VALUES ('0', '17');
INSERT INTO `role_resource` VALUES ('0', '18');
INSERT INTO `role_resource` VALUES ('0', '19');
INSERT INTO `role_resource` VALUES ('0', '3907913782690816');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(36) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o3uyea7py4jnln0qxrtg1qqhq` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', '2015-05-02 17:50:05', '2016-12-27 21:58:01', '管理员', '96e79218965eb72c92a549dd5a330112', 'admin');
INSERT INTO `user` VALUES ('1', '2015-05-02 17:50:06', '2016-12-27 18:34:10', 'admin1', '202cb962ac59075b964b07152d234b70', 'admin1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  KEY `role_id_2` (`role_id`),
  KEY `role_id_3` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('0', '0');
