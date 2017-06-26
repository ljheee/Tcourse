/*
Navicat MySQL Data Transfer

Source Server         : abc
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : courseschedule

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-04-22 10:10:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------一张表表示一周。同一个方格，实验室只能出现一次。
-- Table structure for week1
-- ----------------------------
DROP TABLE IF EXISTS `week1`;
CREATE TABLE `week1` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `day1` varchar(15) DEFAULT NULL,
  `day2` varchar(15) DEFAULT NULL,
  `day3` varchar(15) DEFAULT NULL,
  `day4` varchar(15) DEFAULT NULL,
  `day5` varchar(15) DEFAULT NULL,
  `day6` varchar(15) DEFAULT NULL,
  `day7` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of week1
-- ----------------------------
INSERT INTO `week1` VALUES ('1', null, null, null, null, null, null, null);
INSERT INTO `week1` VALUES ('2', null, null, null, null, null, null, null);
INSERT INTO `week1` VALUES ('3', null, null, null, null, null, null, null);
INSERT INTO `week1` VALUES ('4', null, null, null, null, null, null, null);
INSERT INTO `week1` VALUES ('5', null, null, null, null, null, null, null);
