# Host: 127.0.0.1  (Version: 5.5.15)
# Date: 2019-05-24 16:14:59
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "area"
#

DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "area"
#

INSERT INTO `area` VALUES (1,'默认'),(2,'宁波大学'),(3,'甬江公寓');

#
# Structure for table "category"
#

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "category"
#

INSERT INTO `category` VALUES (1,'纸'),(2,'金属'),(4,'纺织品'),(8,'塑料');

#
# Structure for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `startNumber` float DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `qRemarks` varchar(255) DEFAULT NULL,
  `tRemarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category` (`cid`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

#
# Data for table "product"
#

INSERT INTO `product` VALUES (1,'旧报纸',2.75,'kg',1,1,'2019-05-15 10:31:11','有回收价值','当面点清,概不退货'),(10,'PET绿透瓶片',6,'kg',1,8,'2019-05-15 10:27:56','有回收价值','当面点清,概不退货'),(11,'PET白透瓶片',7,'kg',1,8,'2019-05-15 10:28:27','有回收价值','当面点清,概不退货'),(12,'饮料瓶',4.5,'kg',1,8,'2019-05-15 10:29:04','有回收价值','当面点清,概不退货'),(13,'钢筋压块',2.2,'kg',1,2,'2019-05-15 10:29:49','有回收价值','当面点清,概不退货'),(14,'厚马蹄铁',2.4,'kg',1,2,'2019-05-15 10:30:29','有回收价值','当面点清,概不退货'),(15,'黄纸箱压块',2,'kg',1,1,'2019-05-15 10:31:47','有回收价值','当面点清,概不退货'),(16,'双胶纸',2.5,'kg',1,1,'2019-05-15 10:32:19','有回收价值','当面点清,概不退货'),(17,'貉子毛领',16,'条',1,4,'2019-05-15 10:32:59','有回收价值','当面点清,概不退货'),(18,'狐狸毛条',4,'条',1,4,'2019-05-15 10:33:41','有回收价值','当面点清,概不退货');

#
# Structure for table "productimage"
#

DROP TABLE IF EXISTS `productimage`;
CREATE TABLE `productimage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_productimage_product` (`pid`),
  CONSTRAINT `fk_productimage_product` FOREIGN KEY (`pid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

#
# Data for table "productimage"
#

INSERT INTO `productimage` VALUES (20,10),(21,11),(22,12),(23,13),(24,14),(25,1),(26,15),(27,16),(28,17),(29,18);

#
# Structure for table "recycler"
#

DROP TABLE IF EXISTS `recycler`;
CREATE TABLE `recycler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `alipay` varchar(255) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_recycler_area` (`aid`),
  CONSTRAINT `fk_recycler_area` FOREIGN KEY (`aid`) REFERENCES `area` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1204 DEFAULT CHARSET=utf8;

#
# Data for table "recycler"
#

INSERT INTO `recycler` VALUES (1,'123','356a192b7913b04c54574d18c28d46e6395428ab','000','大师傅似的',NULL,2),(2,'死神','40bd001563085fc35165329ea1ff5c5ecbdbbeef','666','夜神月',NULL,1),(3,'121','7b52009b64fd0a2a49e6d8a939753077792b0554','12','12',NULL,1),(4,'1234','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','1234','1234',NULL,1),(1202,'1','356a192b7913b04c54574d18c28d46e6395428ab','1','1',NULL,1),(1203,'顶顶顶顶','356a192b7913b04c54574d18c28d46e6395428ab','314','撒打算',NULL,1);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `alipay` varchar(255) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_area` (`aid`),
  CONSTRAINT `fk_user_area` FOREIGN KEY (`aid`) REFERENCES `area` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=647 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (2,'admin','dc76e9f0c0006e8f919e0c515c66dbba3982f785',NULL,NULL,NULL,0,1),(3,'1','356a192b7913b04c54574d18c28d46e6395428ab','21344444','哈哈哈','123424166347',2278,2),(4,'liwenhao','7ce8224c13b459bdb86380db060ad839e8490b6d','123','阿瓦达','333',-7,2),(5,'abc','40bd001563085fc35165329ea1ff5c5ecbdbbeef',NULL,NULL,NULL,0,1),(646,'阿斯顿','7b52009b64fd0a2a49e6d8a939753077792b0554',NULL,NULL,NULL,0,1);

#
# Structure for table "order_"
#

DROP TABLE IF EXISTS `order_`;
CREATE TABLE `order_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderCode` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `userMessage` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `payDate` datetime DEFAULT NULL,
  `deliveryDate` datetime DEFAULT NULL,
  `confirmDate` datetime DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `number` float DEFAULT NULL,
  `sumprice` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `reservation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_user` (`uid`),
  KEY `fk_order_recycler` (`rid`),
  KEY `fk_order_area` (`aid`),
  KEY `fk_order_product` (`pid`),
  CONSTRAINT `fk_order_area` FOREIGN KEY (`aid`) REFERENCES `area` (`id`),
  CONSTRAINT `fk_order_product` FOREIGN KEY (`pid`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_order_recycler` FOREIGN KEY (`rid`) REFERENCES `recycler` (`id`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "order_"
#

INSERT INTO `order_` VALUES (1,'201905221414257778118','12','21','21','21','2019-05-22 14:14:25',NULL,NULL,NULL,3,12,1,1202,10,45,'waitDelivery','213'),(2,'201905221427015446574','撒旦','盛大的','213','','2019-05-22 14:27:01',NULL,'2019-05-24 11:38:18',NULL,3,17,2,1202,12,192,'waitConfirm','9'),(3,'20190522151008499525','12','3','1232131','3','2019-05-22 15:10:08',NULL,'2019-05-24 15:18:10','2019-05-24 15:18:24',3,10,2,1202,213,1278,'finish','21'),(4,'201905241517218495046','21','12','12','','2019-05-24 15:17:21',NULL,NULL,NULL,3,12,2,NULL,12,54,'waitReceive','12');

#
# Structure for table "orderimage"
#

DROP TABLE IF EXISTS `orderimage`;
CREATE TABLE `orderimage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orderimage_order` (`oid`),
  CONSTRAINT `fk_orderimage_order` FOREIGN KEY (`oid`) REFERENCES `order_` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "orderimage"
#


#
# Structure for table "withdraw"
#

DROP TABLE IF EXISTS `withdraw`;
CREATE TABLE `withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `payDate` datetime DEFAULT NULL,
  `val` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_withdraw_user` (`uid`),
  CONSTRAINT `fk_withdraw_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "withdraw"
#

INSERT INTO `withdraw` VALUES (1,3,'2019-05-13 11:29:07','2019-05-13 11:30:40',1000,'finish'),(2,3,'2019-05-13 12:57:44','2019-05-13 16:28:18',1,'finish'),(3,3,'2019-05-13 12:57:51','2019-05-13 14:12:44',0.5,'finish'),(4,3,'2019-05-13 15:05:17','2019-05-13 15:36:09',0.5,'finish'),(5,3,'2019-05-13 17:41:45','2019-05-15 10:03:55',123,'finish'),(6,3,'2019-05-24 11:37:17',NULL,5,'waitPay');
