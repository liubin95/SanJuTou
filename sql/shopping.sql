/*
 Navicat MySQL Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 20/11/2019 10:28:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_info
-- ----------------------------
DROP TABLE IF EXISTS `address_info`;
CREATE TABLE `address_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户id',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '详细地址信息',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '电话',
  `is_default` smallint(2) NOT NULL DEFAULT 0 COMMENT '是否默认地址。0代表不是，1代表是',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收件人姓名',
  `province` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省级',
  `city` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市级',
  `county` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地址信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级id',
  `category_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类别名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for collection_info
-- ----------------------------
DROP TABLE IF EXISTS `collection_info`;
CREATE TABLE `collection_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户id',
  `type_id` int(11) NOT NULL DEFAULT 0 COMMENT '0代表店铺，1代表商品',
  `sidorpid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '店铺或商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for oder_info
-- ----------------------------
DROP TABLE IF EXISTS `oder_info`;
CREATE TABLE `oder_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` int(11) NOT NULL DEFAULT 0 COMMENT '0代表待付款，1代表已付款，2已收货',
  `customer_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户id',
  `sku_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '商品id',
  `counts` int(11) NOT NULL DEFAULT 0 COMMENT '商品数量',
  `crate_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `pay_date` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `done_date` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `address_id` int(11) NULL DEFAULT 0 COMMENT '收货地址id',
  `total_price` decimal(10, 2) NOT NULL COMMENT '总价',
  `note` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '买家留言',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cate_id` int(11) NOT NULL COMMENT '分类主键',
  `property_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '属性名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '属性表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for property_option
-- ----------------------------
DROP TABLE IF EXISTS `property_option`;
CREATE TABLE `property_option`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `property_id` int(11) NOT NULL COMMENT '属性id',
  `option` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '选项值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '属性选项表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `old_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '原价',
  `new_price` decimal(10, 2) NOT NULL COMMENT '现价',
  `stock` int(10) NOT NULL DEFAULT 0 COMMENT '库存',
  `cover` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品图片路径',
  `sales` int(255) NULL DEFAULT 0 COMMENT '月销售量',
  `spu_id` int(11) NOT NULL COMMENT 'spu主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品sku表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for specification
-- ----------------------------
DROP TABLE IF EXISTS `specification`;
CREATE TABLE `specification`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `specification_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '规格名称',
  `group_id` int(11) NOT NULL COMMENT '规格组主键',
  `cate_id` int(11) NOT NULL COMMENT '类别主键',
  `order` int(3) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规格表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for specification_group
-- ----------------------------
DROP TABLE IF EXISTS `specification_group`;
CREATE TABLE `specification_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `specification_group_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '规格组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规格组表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for specification_option
-- ----------------------------
DROP TABLE IF EXISTS `specification_option`;
CREATE TABLE `specification_option`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `option_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '规格值',
  `specification_id` int(11) NOT NULL COMMENT '规格主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规格选项表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for specification_option_sku
-- ----------------------------
DROP TABLE IF EXISTS `specification_option_sku`;
CREATE TABLE `specification_option_sku`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `option_id` int(11) NOT NULL COMMENT '规格选项主键',
  `sku_id` int(11) NOT NULL COMMENT 'sku主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sku-规格选项表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for spu
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pro_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `cate_id` int(11) NOT NULL DEFAULT 0 COMMENT '类别id，对应类别表id',
  `store_id` int(11) NOT NULL DEFAULT 0 COMMENT '商铺id，对应商铺表id',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '商品状态，0未上架，1已经上架',
  `cover` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品图片路径',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品spu表' ROW_FORMAT = Compact;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer-name` varchar(200) DEFAULT '' COMMENT '用户名',
  `login-name` varchar(200) DEFAULT '' COMMENT '登陆名',
  `password` varchar(500) DEFAULT '' COMMENT '密码',
  `regtime` datetime DEFAULT NULL COMMENT '注册时间',
  `openid` varchar(50) DEFAULT '' COMMENT '微信openid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS = 1;
