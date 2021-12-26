/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : garbage

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 02/11/2020 09:05:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for garbage
-- ----------------------------
use garbage;
DROP TABLE IF EXISTS `garbage`;
CREATE TABLE `garbage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(4) NULL DEFAULT NULL COMMENT '垃圾类型',
  `weight` double NULL DEFAULT NULL COMMENT '垃圾重量（单位：kg)',
  `status` int(2) NULL DEFAULT 0 COMMENT '状态（0为在厂，1为出厂）',
  `origin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '垃圾来源',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `transport_id` int(11) NULL DEFAULT NULL COMMENT '运输id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '垃圾入库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of garbage
-- ----------------------------
INSERT INTO `garbage` VALUES (6, 2, 32, 1, '23', '2020-10-30 14:14:14', NULL);
INSERT INTO `garbage` VALUES (7, 2, 120, 0, '了问题', '2020-11-01 20:57:22', NULL);
INSERT INTO `garbage` VALUES (8, 1, 23, 0, '委托人', '2020-10-30 16:17:26', NULL);
INSERT INTO `garbage` VALUES (9, 1, 2134, 0, '3452', '2020-11-01 20:55:48', NULL);
INSERT INTO `garbage` VALUES (10, 1, 213, 0, 'cz', '2020-11-01 20:57:01', NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `notice_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 'Hello World', '你好啊', '2020-10-26 14:54:05');

-- ----------------------------
-- Table structure for transport
-- ----------------------------
DROP TABLE IF EXISTS `transport`;
CREATE TABLE `transport`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(2) NULL DEFAULT NULL COMMENT '垃圾类型',
  `dest` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目的地',
  `out_time` date NULL DEFAULT NULL COMMENT '运出时间',
  `status` int(2) NULL DEFAULT 0 COMMENT '审核状态',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运输表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transport
-- ----------------------------
INSERT INTO `transport` VALUES (1, 2, 'jia', '2020-10-27', 0, '2020-10-26 15:09:05');
INSERT INTO `transport` VALUES (2, 1, 'jia', '2020-10-27', 0, '2020-10-26 15:17:22');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` int(2) NULL DEFAULT NULL COMMENT '性别（0为女，1为男）',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `type` int(4) NULL DEFAULT 0 COMMENT '用户权限(0为员工，1为管理员，2为高级管理员）',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('5d7363193c5842a69a08914f71bfb72c', '好贵啊', '25f9e794323b453885f5181f1b624d0b', '12', 1, '2020-11-02', '1212@qq.com', '13212341321', '二纬路', NULL, 0, '2020-11-01 20:06:27');
INSERT INTO `user` VALUES ('82c590e22005445cb42113aff666161e', '内发光', '25f9e794323b453885f5181f1b624d0b', '俩手', 1, '2020-10-19', '12@qq.com', '13212341234', '梵蒂冈和', NULL, 0, '2020-10-21 15:43:33');

SET FOREIGN_KEY_CHECKS = 1;
