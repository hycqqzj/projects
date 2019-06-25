/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : test1

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 25/06/2019 11:09:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_201906
-- ----------------------------
DROP TABLE IF EXISTS `t_user_201906`;
CREATE TABLE `t_user_201906`  (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `gender` int(2) DEFAULT NULL COMMENT '性别：1-男；2-女',
  `join_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_201906
-- ----------------------------
INSERT INTO `t_user_201906` VALUES (593034515866189824, '19130808', 'wxm', 30, 2, '2019-06-25 11:07:27');

-- ----------------------------
-- Table structure for t_user_201907
-- ----------------------------
DROP TABLE IF EXISTS `t_user_201907`;
CREATE TABLE `t_user_201907`  (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `gender` int(2) DEFAULT NULL COMMENT '性别：1-男；2-女',
  `join_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_201907
-- ----------------------------
INSERT INTO `t_user_201907` VALUES (593034838299115520, 'U0002', 'cxh', 27, 2, '2019-07-01 12:30:00');

-- ----------------------------
-- Table structure for t_user_201908
-- ----------------------------
DROP TABLE IF EXISTS `t_user_201908`;
CREATE TABLE `t_user_201908`  (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `gender` int(2) DEFAULT NULL COMMENT '性别：1-男；2-女',
  `join_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
