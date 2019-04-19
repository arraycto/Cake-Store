CREATE DATABASE IF NOT EXISTS cakes default charset utf8 COLLATE utf8_general_ci;

#
create table customer(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '名字',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `phone` char(30) NOT NULL COMMENT '电话',
  `birth` date DEFAULT NULL COMMENT '出生年月日',
  `gender` int(3) DEFAULT '0' COMMENT '0-保密,1-男,2-女',
  `cancel_status` int(3) NOT NULL DEFAULT '1' COMMENT '注销状态,0注销,1未注销',
  `address` varchar(60) DEFAULT NULL COMMENT '地址',
   PRIMARY KEY (`id`),
   UNIQUE KEY `name` (`name`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
CREATE TABLE `angel_cake` (
  `acid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cakename` varchar(50) NOT NULL,
  `caketype` varchar(20) NOT NULL,
  `price` int(30) NOT NULL COMMENT '价格',
  `supplier` varchar(92) DEFAULT NULL COMMENT '供应商',
  `amount` int(22) DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`acid`),
  UNIQUE KEY `cakename` (`cakename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
 CREATE TABLE `consul` (
  `csid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(20) NOT NULL COMMENT '账号',
  `cspassword` varchar(32) NOT NULL COMMENT '密码',
  PRIMARY KEY (`csid`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
CREATE TABLE `cake_cart` (
  `ccid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ccname` varchar(50) NOT NULL,
  `cctype` varchar(20) NOT NULL,
  `ccprice` int(30) NOT NULL COMMENT '价格',
  `ccsupplier` varchar(80) NOT NULL,
  `cuid` int(11) NOT NULL,
  PRIMARY KEY (`ccid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
