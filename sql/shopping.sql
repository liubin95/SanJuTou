/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : shopping

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2019-06-01 11:57:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for addinfo
-- ----------------------------
DROP TABLE IF EXISTS `addinfo`;
CREATE TABLE `addinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `address` varchar(200) NOT NULL DEFAULT '' COMMENT '详细地址信息',
  `mobile` varchar(20) NOT NULL DEFAULT '0' COMMENT '电话',
  `defaultadd` smallint(2) NOT NULL DEFAULT '0' COMMENT '是否默认地址。0代表不是，1代表是',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '收件人姓名',
  `province` varchar(200) DEFAULT NULL COMMENT '省级',
  `city` varchar(200) DEFAULT NULL COMMENT '市级',
  `county` varchar(200) DEFAULT NULL COMMENT '区级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentid` int(11) DEFAULT NULL COMMENT '父级id',
  `categoryname` varchar(200) NOT NULL DEFAULT '' COMMENT '类别名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for collectioninfo
-- ----------------------------
DROP TABLE IF EXISTS `collectioninfo`;
CREATE TABLE `collectioninfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `typeid` int(11) NOT NULL DEFAULT '0' COMMENT '0代表店铺，1代表商品',
  `sidorpid` varchar(200) NOT NULL DEFAULT '0' COMMENT '店铺或商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for oderinfo
-- ----------------------------
DROP TABLE IF EXISTS `oderinfo`;
CREATE TABLE `oderinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `typeid` int(11) NOT NULL DEFAULT '0' COMMENT '0代表待付款，1代表已付款，2已收货',
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `proid` varchar(200) NOT NULL DEFAULT '0' COMMENT '商品id',
  `counts` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `cratedate` datetime DEFAULT NULL COMMENT '创建时间',
  `paydate` datetime DEFAULT NULL COMMENT '付款时间',
  `donedate` datetime DEFAULT NULL COMMENT '收货时间',
  `addressid` int(11) DEFAULT '0' COMMENT '收货地址id',
  `totalprice` decimal(10,2) NOT NULL COMMENT '总价',
  `note` varchar(200) DEFAULT '' COMMENT '买家留言',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `proname` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `oldprice` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `newprice` decimal(10,2) NOT NULL COMMENT '现价',
  `cateid` int(11) NOT NULL DEFAULT '0' COMMENT '类别id，对应类别表id',
  `storeid` int(11) NOT NULL DEFAULT '0' COMMENT '商铺id，对应商铺表id',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '商品状态，0未上架，1已经上架',
  `cover` varchar(200) NOT NULL DEFAULT '' COMMENT '商品图片路径',
  `stock` int(10) NOT NULL DEFAULT '0' COMMENT '库存',
  `parentid` varchar(200) DEFAULT NULL COMMENT '父级商品id',
  `saletime` datetime DEFAULT NULL COMMENT '月销售量统计时间',
  `sales` int(255) DEFAULT '0' COMMENT '月销售量',
  `info` varchar(255) DEFAULT NULL COMMENT '一句话简介',
  `note` varchar(255) DEFAULT NULL COMMENT '子级商品备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cateid` int(11) NOT NULL COMMENT '类别主键',
  `proname` varchar(200) NOT NULL DEFAULT '' COMMENT '属性名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES ('1', '1', '品牌');
INSERT INTO `property` VALUES ('2', '1', '版型');
INSERT INTO `property` VALUES ('3', '1', '季节');
INSERT INTO `property` VALUES ('4', '1', '面料');
INSERT INTO `property` VALUES ('5', '1', '工艺处理');
INSERT INTO `property` VALUES ('6', '1', '适用对象');
INSERT INTO `property` VALUES ('7', '7', '品牌');
INSERT INTO `property` VALUES ('8', '7', '型号');
INSERT INTO `property` VALUES ('9', '7', '分辨率');
INSERT INTO `property` VALUES ('10', '7', '核心数');
INSERT INTO `property` VALUES ('11', '7', '运行内存');
INSERT INTO `property` VALUES ('12', '7', '操作系统');
INSERT INTO `property` VALUES ('13', '13', '品牌');
INSERT INTO `property` VALUES ('14', '13', '版型');
INSERT INTO `property` VALUES ('15', '13', '季节');
INSERT INTO `property` VALUES ('16', '13', '面料');
INSERT INTO `property` VALUES ('17', '13', '工艺处理');
INSERT INTO `property` VALUES ('18', '13', '适用对象');
INSERT INTO `property` VALUES ('19', '19', '品牌');
INSERT INTO `property` VALUES ('20', '19', '型号');
INSERT INTO `property` VALUES ('21', '19', '能效等级');
INSERT INTO `property` VALUES ('22', '19', '款式');
INSERT INTO `property` VALUES ('23', '19', '功率');
INSERT INTO `property` VALUES ('24', '19', '保修期');
INSERT INTO `property` VALUES ('26', '26', '生产日期');
INSERT INTO `property` VALUES ('27', '26', '品牌');
INSERT INTO `property` VALUES ('28', '26', '包装方式');
INSERT INTO `property` VALUES ('29', '26', '产地');
INSERT INTO `property` VALUES ('30', '26', '净含量');
INSERT INTO `property` VALUES ('31', '26', '保质期');
INSERT INTO `property` VALUES ('32', '32', '品牌');
INSERT INTO `property` VALUES ('33', '32', '产地');
INSERT INTO `property` VALUES ('34', '32', '型号');
INSERT INTO `property` VALUES ('35', '32', '适用对象');
INSERT INTO `property` VALUES ('36', '32', '保质期');
INSERT INTO `property` VALUES ('37', '32', '是否为特殊用途化妆品');
INSERT INTO `property` VALUES ('38', '38', '品牌');
INSERT INTO `property` VALUES ('39', '38', '型号');
INSERT INTO `property` VALUES ('40', '38', '材质');
INSERT INTO `property` VALUES ('41', '38', '风格');
INSERT INTO `property` VALUES ('42', '38', '毛重');
INSERT INTO `property` VALUES ('43', '38', '是否可定制');
INSERT INTO `property` VALUES ('44', '44', '品牌');
INSERT INTO `property` VALUES ('45', '44', '质地');
INSERT INTO `property` VALUES ('46', '44', '风格');
INSERT INTO `property` VALUES ('47', '44', '性别');
INSERT INTO `property` VALUES ('48', '44', '上市时间');
INSERT INTO `property` VALUES ('49', '44', '大小');
INSERT INTO `property` VALUES ('50', '51', '品牌');
INSERT INTO `property` VALUES ('51', '51', '材质');
INSERT INTO `property` VALUES ('52', '51', '适用性别');
INSERT INTO `property` VALUES ('53', '51', '风格');
INSERT INTO `property` VALUES ('54', '51', '镶嵌材质');
INSERT INTO `property` VALUES ('55', '51', '上市时间');
INSERT INTO `property` VALUES ('56', '0', '品种');
INSERT INTO `property` VALUES ('57', '0', '原产地');
INSERT INTO `property` VALUES ('58', '0', '最大体重');
INSERT INTO `property` VALUES ('59', '0', '出生日期');
INSERT INTO `property` VALUES ('60', '0', '性别');
INSERT INTO `property` VALUES ('61', '0', '免疫驱虫');

-- ----------------------------
-- Table structure for propertyvalue
-- ----------------------------
DROP TABLE IF EXISTS `propertyvalue`;
CREATE TABLE `propertyvalue` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `proid` varchar(200) NOT NULL DEFAULT '0' COMMENT '商品主键',
  `valueid` int(11) NOT NULL DEFAULT '0' COMMENT '属性id',
  `provalue` varchar(200) NOT NULL DEFAULT '' COMMENT '属性值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `storename` varchar(200) DEFAULT '' COMMENT '店铺名字',
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '店主id',
  `info` varchar(500) NOT NULL DEFAULT '' COMMENT '店铺信息',
  `storecover` varchar(200) NOT NULL DEFAULT '' COMMENT '店铺封面',
  `buildtime` datetime DEFAULT NULL COMMENT '创建时间',
  `typeid` int(11) NOT NULL DEFAULT '0' COMMENT '0代表开店，1代表已关店',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) DEFAULT '' COMMENT '用户名',
  `password` varchar(500) DEFAULT '' COMMENT '密码',
  `regtime` datetime DEFAULT NULL COMMENT '注册时间',
  `openid` varchar(50) DEFAULT '' COMMENT '微信openid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
