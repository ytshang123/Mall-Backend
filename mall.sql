/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80014
Source Host           : localhost:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 80014
File Encoding         : 65001

Date: 2019-05-25 10:57:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminid` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `passwd` varchar(32) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `state` tinyint(1) NOT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456', '1', '0');
INSERT INTO `admin` VALUES ('2', 'test1', '123456', '3', '0');
INSERT INTO `admin` VALUES ('3', 'admin1', '123456', '1', '0');
INSERT INTO `admin` VALUES ('4', 'test2', '123', '3', '1');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `userid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `createdt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`,`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for load_address
-- ----------------------------
DROP TABLE IF EXISTS `load_address`;
CREATE TABLE `load_address` (
  `addressid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalcode` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of load_address
-- ----------------------------
INSERT INTO `load_address` VALUES ('3', 'adb', '四川省', '自贡市', '大安区', '15151987537', '301100', '8', 'adbbb');

-- ----------------------------
-- Table structure for order_main
-- ----------------------------
DROP TABLE IF EXISTS `order_main`;
CREATE TABLE `order_main` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `deliver` varchar(255) DEFAULT NULL,
  `deName` varchar(255) DEFAULT NULL,
  `dePhone` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_main
-- ----------------------------
INSERT INTO `order_main` VALUES ('1', 'zhejianghangzhouxs', 'test', '1222', '340.40', '2019-04-21 09:49:12', '8', '1');
INSERT INTO `order_main` VALUES ('2', 'zhejianghangzhouxs', 'test', '1222', '173.00', '2019-04-21 09:52:37', '8', '1');
INSERT INTO `order_main` VALUES ('3', 'zhejianghangzhouxs', 'test', '1222', '275.40', '2019-04-21 09:53:47', '8', '1');
INSERT INTO `order_main` VALUES ('4', 'zhejianghangzhouxs', 'test', '1222', '27.00', '2019-04-21 09:56:25', '8', '1');
INSERT INTO `order_main` VALUES ('5', 'zhejianghangzhouxs', 'test', '1222', '27.00', '2019-04-21 09:56:29', '8', '1');
INSERT INTO `order_main` VALUES ('6', 'zhejianghangzhouxs', 'test', '1222', '34.50', '2019-04-21 09:57:45', '8', '1');

-- ----------------------------
-- Table structure for order_product
-- ----------------------------
DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product` (
  `orderid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderid`,`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_product
-- ----------------------------
INSERT INTO `order_product` VALUES ('1', '1', '6', '45.90', '荔枝蜜纯天然蜂蜜 精美玻璃瓶液态蜜', 'static/img/goodsList/item-as-img-1.jpg');
INSERT INTO `order_product` VALUES ('1', '3', '1', '65.00', '2019明前茶茶叶绿茶春茶雀舌龙井茶毛尖茶香茶罐装散装100g', 'static/img/goodsList/item-as-img-3.jpg');
INSERT INTO `order_product` VALUES ('2', '2', '4', '27.00', '山东红富士苹果2.5kg 个头稍小 果味浓郁 甜脆回甘', 'static/img/goodsList/item-as-img-2.jpg');
INSERT INTO `order_product` VALUES ('2', '3', '1', '65.00', '2019明前茶茶叶绿茶春茶雀舌龙井茶毛尖茶香茶罐装散装100g', 'static/img/goodsList/item-as-img-3.jpg');
INSERT INTO `order_product` VALUES ('3', '1', '6', '45.90', '荔枝蜜纯天然蜂蜜 精美玻璃瓶液态蜜', 'static/img/goodsList/item-as-img-1.jpg');
INSERT INTO `order_product` VALUES ('4', '2', '1', '27.00', '山东红富士苹果2.5kg 个头稍小 果味浓郁 甜脆回甘', 'static/img/goodsList/item-as-img-2.jpg');
INSERT INTO `order_product` VALUES ('5', '2', '1', '27.00', '山东红富士苹果2.5kg 个头稍小 果味浓郁 甜脆回甘', 'static/img/goodsList/item-as-img-2.jpg');
INSERT INTO `order_product` VALUES ('6', '2', '1', '27.00', '山东红富士苹果2.5kg 个头稍小 果味浓郁 甜脆回甘', 'static/img/goodsList/item-as-img-2.jpg');
INSERT INTO `order_product` VALUES ('6', '5', '1', '7.50', '百年树 芒果干60g办公室零食', 'static/img/goodsList/item-as-img-5.jpg');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `productid` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tag` varchar(32) DEFAULT NULL,
  `img` varchar(255) NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(32,2) NOT NULL,
  `amount` int(32) NOT NULL,
  `off` tinyint(255) DEFAULT '0',
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '荔枝蜜纯天然蜂蜜 精美玻璃瓶液态蜜', '首单包邮|48小时发货', 'item-as-img-1.jpg', '水果蔬菜', '45.90', '100', '0');
INSERT INTO `product` VALUES ('2', '山东红富士苹果2.5kg 个头稍小 果味浓郁 甜脆回甘', '首单包邮', 'item-as-img-2.jpg', '水果蔬菜', '27.00', '50', '0');
INSERT INTO `product` VALUES ('3', '2019明前茶茶叶绿茶春茶雀舌龙井茶毛尖茶香茶罐装散装100g', '48小时发货', 'item-as-img-3.jpg', '茶叶冲饮', '65.00', '77', '0');
INSERT INTO `product` VALUES ('4', '胎菊王 500g', '48小时发货|7天包换', 'item-as-img-4.jpg', '茶叶冲饮', '39.90', '78', '0');
INSERT INTO `product` VALUES ('5', '百年树 芒果干60g办公室零食', '7天包换', 'item-as-img-5.jpg', '蜜饯果干', '7.50', '231', '0');
INSERT INTO `product` VALUES ('6', '罐装 大兴安岭野生蓝莓干 野生蓝莓 蓝莓果干 罐装', '48小时发货|7天包换', 'item-as-img-6.jpg', '蜜饯果干', '21.00', '210', '0');
INSERT INTO `product` VALUES ('7', '小金针菇菌菇 500g', '首单包邮|46小时发货', 'item-as-img-7.jpg', '滋补养生', '4.90', '311', '0');
INSERT INTO `product` VALUES ('8', '海鲜菇新鲜 现采现货 菌菇蘑菇500g', '48小时发货', 'item-as-img-8.jpg', '滋补养生', '7.00', '219', '0');
INSERT INTO `product` VALUES ('9', '绍兴黄酒 古越龙山 清醇三年黄酒（3年） 500ML*12 一箱价', '48小时发货', 'item-as-img-9.jpg', '酒水饮料', '95.00', '100', '0');
INSERT INTO `product` VALUES ('10', '蜀南竹海土特产竹之未竹筒酒客家酿造五年珍藏糯米黄酒竹根清泉', '首单包邮', 'item-as-img-10.jpg', '酒水饮料', '48.00', '100', '0');
INSERT INTO `product` VALUES ('11', '娃哈哈   爽歪歪营养酸奶  牛奶饮品', '首单包邮|7天包换', 'item-as-img-11.jpg', '茶叶冲饮', '2.40', '100', '0');
INSERT INTO `product` VALUES ('12', '完达山酸牛奶大果粒酸奶黄桃+芒果180g', '48小时发货', 'item-as-img-12.jpg', '茶叶冲饮', '3.90', '100', '0');
INSERT INTO `product` VALUES ('13', '新鲜水果黄心猕猴桃营养新鲜直达8个装', '首单包邮', 'item-as-img-13.jpg', '水果蔬菜', '32.90', '100', '0');
INSERT INTO `product` VALUES ('14', '红心火龙果5斤热带新鲜水果 蜜宝红肉火龙果', '首单包邮|48小时发货', 'item-as-img-14.jpg', '水果蔬菜', '69.00', '100', '0');
INSERT INTO `product` VALUES ('15', '新鲜小白菜300g  质地柔嫩', '首单包邮', 'item-as-img-15.jpg', '水果蔬菜', '2.50', '100', '0');
INSERT INTO `product` VALUES ('16', '俞家小铺 特级淡干贝 可即食极鲜元扇贝丁 渔民自晒海鲜干货年', '首单包邮|7天包换', 'item-as-img-16.jpg', '滋补养生', '70.40', '120', '0');
INSERT INTO `product` VALUES ('17', '新疆红枸杞 枸杞子 滋补保健品 100克', '48小时发货|7天包换', 'item-as-img-17.jpg', '滋补养生', '9.00', '120', '0');
INSERT INTO `product` VALUES ('18', '天然野生土蜂蜜 冬蜜蜂糖结晶百花蜜原蜜', '首单包邮|48小时发货', 'item-as-img-18.jpg', '滋补养生', '58.00', '70', '0');
INSERT INTO `product` VALUES ('19', '鲜嫩小白菜350g', '首单包邮|48小时发货', 'item-as-img-19.jpg', '水果蔬菜', '3.50', '110', '0');
INSERT INTO `product` VALUES ('20', '油麦菜3颗 350g 四季油麦菜', '48小时发货', '1555916943012.jpg', '水果蔬菜', '3.00', '70', '0');
INSERT INTO `product` VALUES ('21', '新鲜蔬菜 红苋菜约300  新鲜苋菜', '首单包邮', 'item-as-img-21.jpg', '水果蔬菜', '12.00', '87', '0');
INSERT INTO `product` VALUES ('22', '1号鲜客 黄心苦叶500g', '48小时发货', 'item-as-img-22.jpg', '水果蔬菜', '18.90', '123', '0');
INSERT INTO `product` VALUES ('23', '大米 当季新米 鄱阳湖大米5斤装', '首单包邮|7天包换', 'item-as-img-23.jpg', '米面粮油', '18.90', '111', '1');
INSERT INTO `product` VALUES ('24', '白胖子东北大米盐丰米5kg', '首单包邮|48小时发货', 'item-as-img-24.jpg', '米面粮油', '62.00', '233', '1');
INSERT INTO `product` VALUES ('25', '金龙鱼特香花生油非转压榨 5L', '首单包邮|7天包换', 'item-as-img-25.jpg', '米面粮油', '117.80', '210', '0');
INSERT INTO `product` VALUES ('26', '啦啦啦', '首单包邮', 'item-as-img-1.jpg', '米面粮油', '111.00', '111', '0');
INSERT INTO `product` VALUES ('28', 'test2', 'wwe', '1555917712890.jpg', '蔬菜水果', '1.23', '200', '1');

-- ----------------------------
-- Table structure for shop_cart
-- ----------------------------
DROP TABLE IF EXISTS `shop_cart`;
CREATE TABLE `shop_cart` (
  `userid` int(32) NOT NULL,
  `productid` int(32) NOT NULL,
  `count` int(32) NOT NULL,
  `createdt` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`productid`,`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of shop_cart
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` tinyint(1) NOT NULL,
  `contact` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(32) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '卖报小行家', '123456', '0', '15967171231', '2370718210@qq.com');
INSERT INTO `user` VALUES ('2', 'syt', '123123', '0', '15967171232', '2370718211@qq.com');
INSERT INTO `user` VALUES ('3', '苏大强', '111111', '0', '15967171233', '2370718212@qq.com');
INSERT INTO `user` VALUES ('4', '苏明哲', '222222', '0', '15967171234', '237071821');
INSERT INTO `user` VALUES ('5', '苏明成', '333333', '0', '15967171235', '2370718215@qq.com');
INSERT INTO `user` VALUES ('6', '苏明玉', '444444', '0', '15967171236', '2370718216@qq.com');
INSERT INTO `user` VALUES ('7', 'asd', '123456', '0', '15158036478', 'asd@asd.com');
INSERT INTO `user` VALUES ('8', 'test1', '123456', '0', '15967171231', '2370718210@qq.com');
