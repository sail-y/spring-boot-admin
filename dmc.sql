/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost
 Source Database       : dmc

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : utf-8

 Date: 11/28/2016 17:58:34 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `resource`
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
  `type` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_sogl6f9lioeptbf7s105wbx82` (`PID`),
  KEY `FK_bjlrqegc9iu81src5vlta7p00` (`type`),
  CONSTRAINT `FK_sogl6f9lioeptbf7s105wbx82` FOREIGN KEY (`PID`) REFERENCES `resource` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `resource`
-- ----------------------------
BEGIN;
INSERT INTO `resource` VALUES ('chart', 'chart_bar', '图表管理', null, '7', null, null, '0'), ('jeasyuiApi', 'book', 'EasyUI API', null, '1000', 'http://jeasyui.com/documentation', null, '0'), ('jsgl', 'tux', '角色管理', null, '2', '/role/manager', 'xtgl', '0'), ('jsglAdd', 'wrench', '添加角色', null, '3', '/role/add', 'jsgl', '1'), ('jsglDelete', 'wrench', '删除角色', null, '6', '/role/delete', 'jsgl', '1'), ('jsglEdit', 'wrench', '编辑角色', null, '5', '/role/edit', 'jsgl', '1'), ('jsglGrant', 'wrench', '角色授权', null, '8', '/role/grant', 'jsgl', '1'), ('jsglTreeGrid', 'wrench', '角色表格', null, '1', '/role/treeGrid', 'jsgl', '1'), ('sjygl', 'server_database', '数据源管理', null, '5', '/resource/druid', 'xtgl', '0'), ('userCreateDatetimeChart', 'chart_curve', '用户图表', null, '1', '/chartController/userCreateDatetimeChart', 'chart', '0'), ('wjgl', 'server_database', '文件管理', null, '6', '', 'xtgl', '0'), ('wjglUpload', 'server_database', '上传文件', null, '2', '/resource/upload', 'wjgl', '1'), ('wjglView', 'server_database', '浏览服务器文件', null, '1', '/resource/fileManage', 'wjgl', '1'), ('xtgl', 'plugin', '系统管理', null, '0', null, null, '0'), ('yhgl', 'status_online', '用户管理', null, '3', '/user/manager', 'xtgl', '0'), ('yhglAdd', 'wrench', '添加用户', null, '3', '/role/add', 'yhgl', '1'), ('yhglBatchDelete', 'wrench', '批量删除用户', null, '7', '/role/batchDelete', 'yhgl', '1'), ('yhglDateGrid', 'wrench', '用户表格', null, '1', '/user/dataGrid', 'yhgl', '1'), ('yhglDelete', 'wrench', '删除用户', null, '6', '/user/delete', 'yhgl', '1'), ('yhglEdit', 'wrench', '编辑用户', null, '5', '/user/edit', 'yhgl', '1'), ('yhglEditPwd', 'wrench', '用户修改密码', null, '11', '/user/editPwd', 'yhgl', '1'), ('yhglGrant', 'wrench', '用户授权', null, '9', '/user/grant', 'yhgl', '1'), ('zygl', 'database_gear', '资源管理', '管理系统中所有的菜单或功能', '1', '/resource/manager', 'xtgl', '0'), ('zyglAdd', 'wrench', '添加资源', null, '4', '/resource/add', 'zygl', '1'), ('zyglDelete', 'wrench', '删除资源', null, '7', '/resource/delete', 'zygl', '1'), ('zyglEdit', 'wrench', '编辑资源', null, '6', '/resource/edit', 'zygl', '1'), ('zyglMenu', 'wrench', '功能菜单', null, '2', '/resource/tree', 'zygl', '1'), ('zyglTreeGrid', 'wrench', '资源表格', '显示资源列表', '1', '/resource/treeGrid', 'zygl', '1');
COMMIT;

-- ----------------------------
--  Table structure for `role`
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
--  Records of `role`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('0', '超管', '超级管理员角色，拥有系统中所有的资源访问权限', '0', null), ('bugAdmin', 'BUG管理员', null, '5', '0'), ('guest', 'Guest', '只拥有只看的权限', '1', null), ('jsAdmin', '角色管理员', null, '2', '0'), ('sjyAdmin', '数据源管理员', null, '4', '0'), ('test1', '测试角色', null, '1', null), ('yhAdmin', '用户管理员', null, '3', '0'), ('zyAdmin', '资源管理员', null, '1', '0');
COMMIT;

-- ----------------------------
--  Table structure for `role_resource`
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
--  Records of `role_resource`
-- ----------------------------
BEGIN;
INSERT INTO `role_resource` VALUES ('0', 'chart'), ('0', 'jeasyuiApi'), ('guest', 'jeasyuiApi'), ('0', 'jsgl'), ('guest', 'jsgl'), ('jsAdmin', 'jsgl'), ('test1', 'jsgl'), ('0', 'jsglAdd'), ('jsAdmin', 'jsglAdd'), ('test1', 'jsglAdd'), ('0', 'jsglDelete'), ('jsAdmin', 'jsglDelete'), ('0', 'jsglEdit'), ('jsAdmin', 'jsglEdit'), ('0', 'jsglGrant'), ('jsAdmin', 'jsglGrant'), ('0', 'jsglTreeGrid'), ('guest', 'jsglTreeGrid'), ('jsAdmin', 'jsglTreeGrid'), ('0', 'sjygl'), ('sjyAdmin', 'sjygl'), ('0', 'userCreateDatetimeChart'), ('0', 'wjgl'), ('0', 'wjglUpload'), ('0', 'wjglView'), ('0', 'xtgl'), ('guest', 'xtgl'), ('0', 'yhgl'), ('guest', 'yhgl'), ('yhAdmin', 'yhgl'), ('0', 'yhglAdd'), ('yhAdmin', 'yhglAdd'), ('0', 'yhglBatchDelete'), ('yhAdmin', 'yhglBatchDelete'), ('0', 'yhglDateGrid'), ('guest', 'yhglDateGrid'), ('yhAdmin', 'yhglDateGrid'), ('0', 'yhglDelete'), ('yhAdmin', 'yhglDelete'), ('0', 'yhglEdit'), ('yhAdmin', 'yhglEdit'), ('0', 'yhglEditPwd'), ('yhAdmin', 'yhglEditPwd'), ('0', 'yhglGrant'), ('yhAdmin', 'yhglGrant'), ('0', 'zygl'), ('guest', 'zygl'), ('zyAdmin', 'zygl'), ('0', 'zyglAdd'), ('zyAdmin', 'zyglAdd'), ('0', 'zyglDelete'), ('zyAdmin', 'zyglDelete'), ('0', 'zyglEdit'), ('zyAdmin', 'zyglEdit'), ('0', 'zyglMenu'), ('zyAdmin', 'zyglMenu'), ('0', 'zyglTreeGrid'), ('guest', 'zyglTreeGrid'), ('zyAdmin', 'zyglTreeGrid');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o3uyea7py4jnln0qxrtg1qqhq` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('0', '2015-05-02 17:50:05', null, 'DMC', 'e10adc3949ba59abbe56e057f20f883e', 'admin'), ('1', '2015-05-02 17:50:06', null, 'admin1', 'e10adc3949ba59abbe56e057f20f883e', 'admin1'), ('2', '2015-05-02 17:50:06', null, 'admin2', 'e10adc3949ba59abbe56e057f20f883e', null), ('3', '2015-05-02 17:50:06', null, 'admin3', 'e10adc3949ba59abbe56e057f20f883e', null), ('4', '2015-05-02 17:50:06', null, 'admin4', 'e10adc3949ba59abbe56e057f20f883e', null), ('5', '2015-05-02 17:50:06', null, 'admin5', 'e10adc3949ba59abbe56e057f20f883e', null), ('a9957e88-9923-4ccb-8db2-35408af124c9', '2015-05-03 00:05:44', null, '马1', 'e10adc3949ba59abbe56e057f20f883e', null), ('guest', '2015-05-02 17:50:06', null, 'guest', 'e10adc3949ba59abbe56e057f20f883e', 'guest');
COMMIT;

-- ----------------------------
--  Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `USER_ID` varchar(36) NOT NULL,
  `ROLE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`USER_ID`),
  KEY `FK_qxlog73d0t2auuod93t5qfw9h` (`ROLE_ID`),
  KEY `FK_4b3tcd0afi19cpbf14t0llnb2` (`USER_ID`),
  CONSTRAINT `FK_4b3tcd0afi19cpbf14t0llnb2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_qxlog73d0t2auuod93t5qfw9h` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user_role`
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES ('0', '0'), ('a9957e88-9923-4ccb-8db2-35408af124c9', '0'), ('0', 'bugAdmin'), ('5', 'bugAdmin'), ('a9957e88-9923-4ccb-8db2-35408af124c9', 'bugAdmin'), ('0', 'guest'), ('a9957e88-9923-4ccb-8db2-35408af124c9', 'guest'), ('guest', 'guest'), ('0', 'jsAdmin'), ('2', 'jsAdmin'), ('a9957e88-9923-4ccb-8db2-35408af124c9', 'jsAdmin'), ('0', 'sjyAdmin'), ('4', 'sjyAdmin'), ('a9957e88-9923-4ccb-8db2-35408af124c9', 'sjyAdmin'), ('a9957e88-9923-4ccb-8db2-35408af124c9', 'test1'), ('0', 'yhAdmin'), ('3', 'yhAdmin'), ('a9957e88-9923-4ccb-8db2-35408af124c9', 'yhAdmin'), ('0', 'zyAdmin'), ('1', 'zyAdmin'), ('a9957e88-9923-4ccb-8db2-35408af124c9', 'zyAdmin');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
