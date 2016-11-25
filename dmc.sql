/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : dmc

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-05-03 15:37:37
*/


SET FOREIGN_KEY_CHECKS=0;
CREATE DATABASE  IF NOT EXISTS `dmc` ;
USE `dmc`;
-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `ID` varchar(36) NOT NULL,
  `ICON` varchar(100) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `PID` varchar(36) DEFAULT NULL,
  `RESOURCETYPE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_sogl6f9lioeptbf7s105wbx82` (`PID`),
  KEY `FK_bjlrqegc9iu81src5vlta7p00` (`RESOURCETYPE_ID`),
  CONSTRAINT `FK_bjlrqegc9iu81src5vlta7p00` FOREIGN KEY (`RESOURCETYPE_ID`) REFERENCES `resourcetype` (`ID`),
  CONSTRAINT `FK_sogl6f9lioeptbf7s105wbx82` FOREIGN KEY (`PID`) REFERENCES `resource` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('chart', 'chart_bar', '图表管理', null, '7', null, null, '0');
INSERT INTO `resource` VALUES ('jeasyuiApi', 'book', 'EasyUI API', null, '1000', 'http://jeasyui.com/documentation', null, '0');
INSERT INTO `resource` VALUES ('jsgl', 'tux', '角色管理', null, '2', '/roleController/manager', 'xtgl', '0');
INSERT INTO `resource` VALUES ('jsglAdd', 'wrench', '添加角色', null, '3', '/roleController/add', 'jsgl', '1');
INSERT INTO `resource` VALUES ('jsglAddPage', 'wrench', '添加角色页面', null, '2', '/roleController/addPage', 'jsgl', '1');
INSERT INTO `resource` VALUES ('jsglDelete', 'wrench', '删除角色', null, '6', '/roleController/delete', 'jsgl', '1');
INSERT INTO `resource` VALUES ('jsglEdit', 'wrench', '编辑角色', null, '5', '/roleController/edit', 'jsgl', '1');
INSERT INTO `resource` VALUES ('jsglEditPage', 'wrench', '编辑角色页面', null, '4', '/roleController/editPage', 'jsgl', '1');
INSERT INTO `resource` VALUES ('jsglGrant', 'wrench', '角色授权', null, '8', '/roleController/grant', 'jsgl', '1');
INSERT INTO `resource` VALUES ('jsglGrantPage', 'wrench', '角色授权页面', null, '7', '/roleController/grantPage', 'jsgl', '1');
INSERT INTO `resource` VALUES ('jsglTreeGrid', 'wrench', '角色表格', null, '1', '/roleController/treeGrid', 'jsgl', '1');
INSERT INTO `resource` VALUES ('sjygl', 'server_database', '数据源管理', null, '5', '/druidController/druid', 'xtgl', '0');
INSERT INTO `resource` VALUES ('userCreateDatetimeChart', 'chart_curve', '用户图表', null, '1', '/chartController/userCreateDatetimeChart', 'chart', '0');
INSERT INTO `resource` VALUES ('wjgl', 'server_database', '文件管理', null, '6', '', 'xtgl', '1');
INSERT INTO `resource` VALUES ('wjglUpload', 'server_database', '上传文件', null, '2', '/fileController/upload', 'wjgl', '1');
INSERT INTO `resource` VALUES ('wjglView', 'server_database', '浏览服务器文件', null, '1', '/fileController/fileManage', 'wjgl', '1');
INSERT INTO `resource` VALUES ('xtgl', 'plugin', '系统管理', null, '0', null, null, '0');
INSERT INTO `resource` VALUES ('yhgl', 'status_online', '用户管理', null, '3', '/userController/manager', 'xtgl', '0');
INSERT INTO `resource` VALUES ('yhglAdd', 'wrench', '添加用户', null, '3', '/userController/add', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglAddPage', 'wrench', '添加用户页面', null, '2', '/userController/addPage', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglBatchDelete', 'wrench', '批量删除用户', null, '7', '/userController/batchDelete', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglDateGrid', 'wrench', '用户表格', null, '1', '/userController/dataGrid', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglDelete', 'wrench', '删除用户', null, '6', '/userController/delete', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglEdit', 'wrench', '编辑用户', null, '5', '/userController/edit', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglEditPage', 'wrench', '编辑用户页面', null, '4', '/userController/editPage', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglEditPwd', 'wrench', '用户修改密码', null, '11', '/userController/editPwd', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglEditPwdPage', 'wrench', '用户修改密码页面', null, '10', '/userController/editPwdPage', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglGrant', 'wrench', '用户授权', null, '9', '/userController/grant', 'yhgl', '1');
INSERT INTO `resource` VALUES ('yhglGrantPage', 'wrench', '用户授权页面', null, '8', '/userController/grantPage', 'yhgl', '1');
INSERT INTO `resource` VALUES ('zygl', 'database_gear', '资源管理', '管理系统中所有的菜单或功能', '1', '/resourceController/manager', 'xtgl', '0');
INSERT INTO `resource` VALUES ('zyglAdd', 'wrench', '添加资源', null, '4', '/resourceController/add', 'zygl', '1');
INSERT INTO `resource` VALUES ('zyglAddPage', 'wrench', '添加资源页面', null, '3', '/resourceController/addPage', 'zygl', '1');
INSERT INTO `resource` VALUES ('zyglDelete', 'wrench', '删除资源', null, '7', '/resourceController/delete', 'zygl', '1');
INSERT INTO `resource` VALUES ('zyglEdit', 'wrench', '编辑资源', null, '6', '/resourceController/edit', 'zygl', '1');
INSERT INTO `resource` VALUES ('zyglEditPage', 'wrench', '编辑资源页面', null, '5', '/resourceController/editPage', 'zygl', '1');
INSERT INTO `resource` VALUES ('zyglMenu', 'wrench', '功能菜单', null, '2', '/resourceController/tree', 'zygl', '1');
INSERT INTO `resource` VALUES ('zyglTreeGrid', 'wrench', '资源表格', '显示资源列表', '1', '/resourceController/treeGrid', 'zygl', '1');

-- ----------------------------
-- Table structure for resourcetype
-- ----------------------------
DROP TABLE IF EXISTS `resourcetype`;
CREATE TABLE `resourcetype` (
  `ID` varchar(36) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of resourcetype
-- ----------------------------
INSERT INTO `resourcetype` VALUES ('0', '菜单');
INSERT INTO `resourcetype` VALUES ('1', '功能');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` varchar(36) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `PID` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_tealaj0x99w9xj8on8ax0jgjb` (`PID`),
  CONSTRAINT `FK_tealaj0x99w9xj8on8ax0jgjb` FOREIGN KEY (`PID`) REFERENCES `role` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', '超管', '超级管理员角色，拥有系统中所有的资源访问权限', '0', null);
INSERT INTO `role` VALUES ('bugAdmin', 'BUG管理员', null, '5', '0');
INSERT INTO `role` VALUES ('guest', 'Guest', '只拥有只看的权限', '1', null);
INSERT INTO `role` VALUES ('jsAdmin', '角色管理员', null, '2', '0');
INSERT INTO `role` VALUES ('sjyAdmin', '数据源管理员', null, '4', '0');
INSERT INTO `role` VALUES ('test1', '测试角色', null, '1', null);
INSERT INTO `role` VALUES ('yhAdmin', '用户管理员', null, '3', '0');
INSERT INTO `role` VALUES ('zyAdmin', '资源管理员', null, '1', '0');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `ROLE_ID` varchar(36) NOT NULL,
  `RESOURCE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`RESOURCE_ID`,`ROLE_ID`),
  KEY `FK_eyrr8fy4xk8nqs4haoqav5vs4` (`RESOURCE_ID`),
  KEY `FK_7ig5nfqrf5vuwgic6g7ybk72a` (`ROLE_ID`),
  CONSTRAINT `FK_7ig5nfqrf5vuwgic6g7ybk72a` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_eyrr8fy4xk8nqs4haoqav5vs4` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `resource` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('0', 'chart');
INSERT INTO `role_resource` VALUES ('0', 'jeasyuiApi');
INSERT INTO `role_resource` VALUES ('guest', 'jeasyuiApi');
INSERT INTO `role_resource` VALUES ('0', 'jsgl');
INSERT INTO `role_resource` VALUES ('guest', 'jsgl');
INSERT INTO `role_resource` VALUES ('jsAdmin', 'jsgl');
INSERT INTO `role_resource` VALUES ('test1', 'jsgl');
INSERT INTO `role_resource` VALUES ('0', 'jsglAdd');
INSERT INTO `role_resource` VALUES ('jsAdmin', 'jsglAdd');
INSERT INTO `role_resource` VALUES ('test1', 'jsglAdd');
INSERT INTO `role_resource` VALUES ('0', 'jsglAddPage');
INSERT INTO `role_resource` VALUES ('jsAdmin', 'jsglAddPage');
INSERT INTO `role_resource` VALUES ('0', 'jsglDelete');
INSERT INTO `role_resource` VALUES ('jsAdmin', 'jsglDelete');
INSERT INTO `role_resource` VALUES ('0', 'jsglEdit');
INSERT INTO `role_resource` VALUES ('jsAdmin', 'jsglEdit');
INSERT INTO `role_resource` VALUES ('0', 'jsglEditPage');
INSERT INTO `role_resource` VALUES ('jsAdmin', 'jsglEditPage');
INSERT INTO `role_resource` VALUES ('0', 'jsglGrant');
INSERT INTO `role_resource` VALUES ('jsAdmin', 'jsglGrant');
INSERT INTO `role_resource` VALUES ('0', 'jsglGrantPage');
INSERT INTO `role_resource` VALUES ('jsAdmin', 'jsglGrantPage');
INSERT INTO `role_resource` VALUES ('0', 'jsglTreeGrid');
INSERT INTO `role_resource` VALUES ('guest', 'jsglTreeGrid');
INSERT INTO `role_resource` VALUES ('jsAdmin', 'jsglTreeGrid');
INSERT INTO `role_resource` VALUES ('0', 'sjygl');
INSERT INTO `role_resource` VALUES ('sjyAdmin', 'sjygl');
INSERT INTO `role_resource` VALUES ('0', 'userCreateDatetimeChart');
INSERT INTO `role_resource` VALUES ('0', 'wjgl');
INSERT INTO `role_resource` VALUES ('0', 'wjglUpload');
INSERT INTO `role_resource` VALUES ('0', 'wjglView');
INSERT INTO `role_resource` VALUES ('0', 'xtgl');
INSERT INTO `role_resource` VALUES ('guest', 'xtgl');
INSERT INTO `role_resource` VALUES ('0', 'yhgl');
INSERT INTO `role_resource` VALUES ('guest', 'yhgl');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhgl');
INSERT INTO `role_resource` VALUES ('0', 'yhglAdd');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglAdd');
INSERT INTO `role_resource` VALUES ('0', 'yhglAddPage');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglAddPage');
INSERT INTO `role_resource` VALUES ('0', 'yhglBatchDelete');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglBatchDelete');
INSERT INTO `role_resource` VALUES ('0', 'yhglDateGrid');
INSERT INTO `role_resource` VALUES ('guest', 'yhglDateGrid');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglDateGrid');
INSERT INTO `role_resource` VALUES ('0', 'yhglDelete');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglDelete');
INSERT INTO `role_resource` VALUES ('0', 'yhglEdit');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglEdit');
INSERT INTO `role_resource` VALUES ('0', 'yhglEditPage');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglEditPage');
INSERT INTO `role_resource` VALUES ('0', 'yhglEditPwd');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglEditPwd');
INSERT INTO `role_resource` VALUES ('0', 'yhglEditPwdPage');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglEditPwdPage');
INSERT INTO `role_resource` VALUES ('0', 'yhglGrant');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglGrant');
INSERT INTO `role_resource` VALUES ('0', 'yhglGrantPage');
INSERT INTO `role_resource` VALUES ('yhAdmin', 'yhglGrantPage');
INSERT INTO `role_resource` VALUES ('0', 'zygl');
INSERT INTO `role_resource` VALUES ('guest', 'zygl');
INSERT INTO `role_resource` VALUES ('zyAdmin', 'zygl');
INSERT INTO `role_resource` VALUES ('0', 'zyglAdd');
INSERT INTO `role_resource` VALUES ('zyAdmin', 'zyglAdd');
INSERT INTO `role_resource` VALUES ('0', 'zyglAddPage');
INSERT INTO `role_resource` VALUES ('zyAdmin', 'zyglAddPage');
INSERT INTO `role_resource` VALUES ('0', 'zyglDelete');
INSERT INTO `role_resource` VALUES ('zyAdmin', 'zyglDelete');
INSERT INTO `role_resource` VALUES ('0', 'zyglEdit');
INSERT INTO `role_resource` VALUES ('zyAdmin', 'zyglEdit');
INSERT INTO `role_resource` VALUES ('0', 'zyglEditPage');
INSERT INTO `role_resource` VALUES ('zyAdmin', 'zyglEditPage');
INSERT INTO `role_resource` VALUES ('0', 'zyglMenu');
INSERT INTO `role_resource` VALUES ('zyAdmin', 'zyglMenu');
INSERT INTO `role_resource` VALUES ('0', 'zyglTreeGrid');
INSERT INTO `role_resource` VALUES ('guest', 'zyglTreeGrid');
INSERT INTO `role_resource` VALUES ('zyAdmin', 'zyglTreeGrid');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(36) NOT NULL,
  `CREATEDATETIME` datetime DEFAULT NULL,
  `MODIFYDATETIME` datetime DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `PWD` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_o3uyea7py4jnln0qxrtg1qqhq` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', '2015-05-02 17:50:05', null, '孙宇', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('1', '2015-05-02 17:50:06', null, 'admin1', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('2', '2015-05-02 17:50:06', null, 'admin2', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('3', '2015-05-02 17:50:06', null, 'admin3', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('4', '2015-05-02 17:50:06', null, 'admin4', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('5', '2015-05-02 17:50:06', null, 'admin5', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('a9957e88-9923-4ccb-8db2-35408af124c9', '2015-05-03 00:05:44', null, '马1', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('guest', '2015-05-02 17:50:06', null, 'guest', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `USER_ID` varchar(36) NOT NULL,
  `ROLE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`USER_ID`),
  KEY `FK_qxlog73d0t2auuod93t5qfw9h` (`ROLE_ID`),
  KEY `FK_4b3tcd0afi19cpbf14t0llnb2` (`USER_ID`),
  CONSTRAINT `FK_4b3tcd0afi19cpbf14t0llnb2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK_qxlog73d0t2auuod93t5qfw9h` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('0', '0');
INSERT INTO `user_role` VALUES ('a9957e88-9923-4ccb-8db2-35408af124c9', '0');
INSERT INTO `user_role` VALUES ('0', 'bugAdmin');
INSERT INTO `user_role` VALUES ('5', 'bugAdmin');
INSERT INTO `user_role` VALUES ('a9957e88-9923-4ccb-8db2-35408af124c9', 'bugAdmin');
INSERT INTO `user_role` VALUES ('0', 'guest');
INSERT INTO `user_role` VALUES ('a9957e88-9923-4ccb-8db2-35408af124c9', 'guest');
INSERT INTO `user_role` VALUES ('guest', 'guest');
INSERT INTO `user_role` VALUES ('0', 'jsAdmin');
INSERT INTO `user_role` VALUES ('2', 'jsAdmin');
INSERT INTO `user_role` VALUES ('a9957e88-9923-4ccb-8db2-35408af124c9', 'jsAdmin');
INSERT INTO `user_role` VALUES ('0', 'sjyAdmin');
INSERT INTO `user_role` VALUES ('4', 'sjyAdmin');
INSERT INTO `user_role` VALUES ('a9957e88-9923-4ccb-8db2-35408af124c9', 'sjyAdmin');
INSERT INTO `user_role` VALUES ('a9957e88-9923-4ccb-8db2-35408af124c9', 'test1');
INSERT INTO `user_role` VALUES ('0', 'yhAdmin');
INSERT INTO `user_role` VALUES ('3', 'yhAdmin');
INSERT INTO `user_role` VALUES ('a9957e88-9923-4ccb-8db2-35408af124c9', 'yhAdmin');
INSERT INTO `user_role` VALUES ('0', 'zyAdmin');
INSERT INTO `user_role` VALUES ('1', 'zyAdmin');
INSERT INTO `user_role` VALUES ('a9957e88-9923-4ccb-8db2-35408af124c9', 'zyAdmin');
