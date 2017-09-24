/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50540
Source Host           : 127.0.0.1:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-09-24 22:31:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_admin
-- ----------------------------
DROP TABLE IF EXISTS `s_admin`;
CREATE TABLE `s_admin` (
  `adminId` varchar(50) NOT NULL,
  `adminname` varchar(20) DEFAULT NULL,
  `adminpwd` varchar(20) DEFAULT NULL,
  `orderBy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_admin
-- ----------------------------
INSERT INTO `s_admin` VALUES ('1', 'admin', 'admin', null);

-- ----------------------------
-- Table structure for s_book
-- ----------------------------
DROP TABLE IF EXISTS `s_book`;
CREATE TABLE `s_book` (
  `bid` varchar(100) NOT NULL,
  `bname` varchar(200) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `price` double(20,0) DEFAULT NULL,
  `currPrice` double(20,0) DEFAULT NULL,
  `discount` double(20,0) DEFAULT NULL,
  `press` varchar(50) DEFAULT NULL,
  `publishtime` varchar(50) DEFAULT NULL,
  `edition` int(20) DEFAULT NULL,
  `pageNum` int(20) DEFAULT NULL,
  `wordNum` int(20) DEFAULT NULL,
  `printtime` varchar(50) DEFAULT NULL,
  `booksize` int(20) DEFAULT NULL,
  `paper` varchar(50) DEFAULT NULL,
  `cid` varchar(100) DEFAULT NULL,
  `image_w` varchar(100) DEFAULT NULL,
  `image_b` varchar(100) DEFAULT NULL,
  `orderBy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `ForeignKey1` (`cid`),
  CONSTRAINT `ForeignKey1` FOREIGN KEY (`cid`) REFERENCES `s_category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_book
-- ----------------------------
INSERT INTO `s_book` VALUES ('000A18FDB38F470DBE9CD0972BADB23F', 'Java Web整合开发实战--基于Struts 2+Hibernate+Spring（99个应用实例，4个项目开发案例，15.5小时配套教学视频，超值DVD光盘含大量开发资源。一本书搞定SSH框架整合开发！）', '贾蓓', '80', '55', '7', '清华大学出版社', '2013-7-1', '1', '640', '1030000', '2013-7-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23268958-1_w.jpg', 'book_img/23268958-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('0BE0707984014E66BD9F34F534FCE0F7', 'OSGi实战【OSGi规范制定者亲自撰写 汇集Apache项目技术实战经验】', 'Richard', '99', '68', '7', '人民邮电出版社', '2013-1-1', '1', '468', '721000', '2013-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22938396-1_w.jpg', 'book_img/22938396-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('0EE8A0AE69154287A378FB110FF2C780', 'Java核心技术：卷Ⅰ基础知识（原书第8版）', '昊斯特曼', '98', '68', '7', '机械工业出版社', '2008-6-1', '1', '694', '0', '2008-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20285763-1_w.jpg', 'book_img/20285763-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('113D0D1BB9174DD19A7DE7E2B37F677F', 'Java7入门经典（跟编程导师Ivor Horton学Java 预计到货日期9月初）', '霍尔顿', '118', '81', '7', '清华大学出版社', '2012-8-1', '1', '1124', '1918000', '2012-8-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22813026-1_w.jpg', 'book_img/22813026-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('1286B13F0EA54E4CB75434762121486A', 'Java核心技术 卷I：基础知识(第9版·英文版)(上、下册)', '霍斯特曼', '109', '75', '7', '人民邮电出版社', '2013-7-1', '1', '0', '1197000', '2013-7-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23280479-1_w.jpg', 'book_img/23280479-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('13EBF9B1FDE346A683A1C45BD6773ECB', 'Java开发实战1200例（第Ⅱ卷）（史上最全的“编程实例”类图书，代码分析、实例速查、练习巩固的绝好帮手）', '无', '99', '68', '7', '清华大学出版社', '0', '1', '0', '1754000', '2011-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/21110930-1_w_1.jpg', 'book_img/21110930-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('1A15DC5E8A014A58862ED741D579B530', 'Java深入解析——透析Java本质的36个话题', '梁勇', '49', '34', '7', '电子工业出版社', '2013-11-1', '1', '298', '424000', '2013-11-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23363997-1_w_1.jpg', 'book_img/23363997-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('210A3DCA429049C78B623C3986BEB136', 'JavaScript经典教程套装：JavaScript高级程序设计(第3版)+JavaScript DOM编程艺术(第2版)(套装共2册)(超值附赠《码农》光盘1张)', 'Nicholas C. Zakas', '148', '89', '6', '人民邮电出版社', '2013-4-1', '1', '1048', '0', '2013-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23224089-1_w.jpg', 'book_img/23224089-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('22234CECF15F4ECB813F0B433DDA5365', 'JavaScript从入门到精通（附光盘1张）（连续8月JavaScript类全国零售排行前2名，13小时视频，400个经典实例、369项面试真题、138项测试史上最全资源库）', '明日科技', '70', '48', '7', '清华大学出版社', '2012-9-1', '1', '532', '939000', '2012-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22862057-1_w.jpg', 'book_img/22862057-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('230A00EC22BF4A1DBA87C64800B54C8D', 'Struts2技术内幕：深入解析Struts架构设计与实现原理', '陆舟', '69', '48', '7', '机械工业出版社', '2012-1-1', '1', '379', '0', '2012-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22577578-1_w.jpg', 'book_img/22577578-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('260F8A3594F741C1B0EB69616F65045B', 'Tomcat与Java Web开发技术详解（第2版）(含光盘1张)', '孙卫琴', '79', '55', '7', '电子工业出版社', '2009-1-1', '1', '734', '1216000', '2009-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20420983-1_w.jpg', 'book_img/20420983-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('28A03D28BAD449659A77330BE35FCD65', 'JAVA核心技术卷II：高级特性（原书第8版）', '霍斯特曼', '118', '81', '7', '机械工业出版社', '2008-12-1', '1', '852', '0', '2008-12-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20446562-1_w.jpg', 'book_img/20446562-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('2EE1A20A6AF742E387E18619D7E3BB94', 'Java虚拟机并发编程(Java并发编程领域的里程碑之作，资深Java技术专家、并发编程专家、敏捷开发专家和Jolt大奖得主撰写，Amazon五星畅销书)', 'Venkat Subramaniam', '59', '41', '7', '机械工业出版社', '2013-5-1', '1', '215', '0', '2013-5-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23239786-1_w.jpg', 'book_img/23239786-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('33ACF97A9A374352AE9F5E89BB791262', '基于MVC的JavaScript Web富应用开发', '麦卡劳', '59', '41', '7', '电子工业出版社', '2012-5-1', '1', '282', '462000', '2012-5-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22757564-1_w.jpg', 'book_img/22757564-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('37F75BEAE1FE46F2B14674923F1E7987', '数据结构与算法分析Java语言描述 第2版', '韦斯', '55', '38', '7', '机械工业出版社', '2009-1-1', '2', '440', '0', '2009-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20417467-1_w.jpg', 'book_img/20417467-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('39F1D0803E8F4592AE1245CACE683214', 'Java程序员修炼之道', '埃文斯', '89', '61', '7', '人民邮电出版社', '2013-8-1', '1', '395', '658000', '2013-8-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23301847-1_w_1.jpg', 'book_img/23301847-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('3AE5C8B976B6448A9D3A155C1BDE12BC', '深入理解Java虚拟机:JVM高级特性与最佳实践（超级畅销书，6个月5次印刷，从实践角度解析JVM工作原理，Java程序员必备）', '周志明', '69', '48', '7', '机械工业出版社', '0', '1', '0', '0', '2011-7-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/21108671-1_w_1.jpg', 'book_img/21108671-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('3DD187217BF44A99B86DD18A4DC628BA', 'Java核心技术 卷1 基础知识（原书第9版）', '霍斯特曼，科内尔', '119', '82', '7', '机械工业出版社', '2014-1-1', '1', '704', '0', '2014-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23362142-1_w_1.jpg', 'book_img/23362142-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('3E1990E19989422E9DA735978CB1E4CE', 'Effective Java中文版(第2版)', '布洛克', '52', '36', '7', '机械工业出版社', '2009-1-1', '2', '287', '0', '2009-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20459091-1_w.jpg', 'book_img/20459091-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('400D94DE5A0742B3A618FC76DF107183', 'JavaScript宝典（第7版）（配光盘）', '古德曼', '128', '88', '7', '清华大学出版社', '2013-1-1', '1', '1012', '1657000', '2013-1-1', '32', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23169892-1_w.jpg', 'book_img/23169892-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('4491EA4832E04B8B94F334B71E871983', 'Java语言程序设计：进阶篇（原书第8版）', '梁勇', '79', '54', '7', '机械工业出版社', '2011-6-1', '1', '507', '0', '2011-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/21117631-1_w_1.jpg', 'book_img/21117631-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('48BBFBFC07074ADE8CC906A45BE5D9A6', 'JavaScript权威指南（第6版）（淘宝前端团队倾情翻译！经典权威的JavaScript犀牛书！第6版特别涵盖了HTML5和ECMAScript5！）（经典巨著，当当独家首发）', '弗兰纳根', '139', '95', '7', '机械工业出版社', '2012-4-1', '1', '1004', '0', '2012-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22722790-1_w.jpg', 'book_img/22722790-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('49D98E7916B94232862F7DCD1B0BAB66', 'HTML5+JavaScript动画基础', '兰贝塔', '69', '48', '7', '人民邮电出版社', '2013-6-1', '1', '393', '553000', '2013-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23266633-1_w.jpg', 'book_img/23266633-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('4A9574F03A6B40C1B2A437237C17DEEC', 'Spring实战(第3版)（In Action系列中最畅销的Spring图书，近十万读者学习Spring的共同选择）', 'Craig Walls', '59', '41', '7', '人民邮电出版社', '2013-6-1', '1', '374', '487000', '2013-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23254532-1_w.jpg', 'book_img/23254532-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('4BF6D97DD18A4B77B8DED9B057577F8F', 'Java Web从入门到精通（附光盘1张）（连续8月Java类全国零售排行前2名，27小时视频，951个经典实例、369项面试真题、596项测试史上最全资源库）', '明日科技', '70', '48', '7', '清华大学出版社', '2012-9-1', '1', '547', '979000', '2012-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22862056-1_w.jpg', 'book_img/22862056-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('4C3331CAD5A5453787A94B8D7CCEAA29', 'Java Web整合开发王者归来（JSP+Servlet+Struts+Hibernate+Spring）（配光盘', '刘京华', '100', '69', '7', '清华大学出版社', '2010-1-1', '1', '1010', '1368000', '2010-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20756351-1_w_1.jpg', 'book_img/20756351-1_b_1.jpg', null);
INSERT INTO `s_book` VALUES ('4D20D2450B084113A331D909FF4975EB', 'jQuery实战(第2版)(畅销书升级版，掌握Web开发利器必修宝典)', 'Bear Bibeault　Yehuda Katz ', '69', '48', '7', '人民邮电出版社', '2012-3-1', '1', '394', '617000', '2012-3-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22638286-1_w.jpg', 'book_img/22638286-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('4E44405DAFB7413E8A13BBFFBEE73AC7', 'JavaScript经典实例', '鲍尔斯', '78', '54', '7', '中国电力出版社', '2012-3-1', '1', '512', '625000', '2012-3-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22692811-1_w.jpg', 'book_img/22692811-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('504FB999B0444B339907090927FDBE8A', '深入浅出Ext JS(第3版)', '徐会生', '69', '48', '7', '人民邮电出版社', '2013-10-1', '3', '413', '642000', '2013-10-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23351049-1_w_1.jpg', 'book_img/23351049-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('52077C8423B645A9BADA96A5E0B14422', 'Spring源码深度解析', '郝佳', '69', '48', '7', '人民邮电出版社', '2013-9-1', '1', '386', '545000', '2013-8-30', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23329703-1_w_1.jpg', 'book_img/23329703-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('52B0EDFF966E4A058BDA5B18EEC698C4', '亮剑Java Web项目开发案例导航(含DVD光盘1张)', '朱雪琴', '69', '41', '6', '电子工业出版社', '2012-3-1', '1', '526', '875000', '2012-3-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22623766-1_w.jpg', 'book_img/22623766-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('5315DA60D24042889400AD4C93A37501', 'Spring 3.x企业应用开发实战(含CD光盘1张)', '陈雄华', '90', '62', '7', '电子工业出版社', '2012-2-1', '1', '710', '1158000', '2012-2-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22605701-1_w.jpg', 'book_img/22605701-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('56B1B7D8CD8740B098677C7216A673C4', '疯狂 Java 程序员的基本修养（《疯狂Java讲义》最佳拍档，扫清知识死角，夯实基本功）', '李刚', '59', '41', '7', '电子工业出版社', '2013-1-1', '1', '484', '7710000', '2013-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23042420-1_w.jpg', 'book_img/23042420-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('57B6FF1B89C843C38BA39C717FA557D6', '了不起的Node.js: 将JavaScript进行到底（Web开发首选实时 跨多服务器 高并发）', 'Guillermo Rauch', '79', '54', '7', '电子工业出版社', '2014-1-1', '1', '292', '436000', '2014-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23368351-1_w_2.jpg', 'book_img/23368351-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('5C4A6F0F4A3B4672AD8C5F89BF5D37D2', 'Java从入门到精通（第3版）（附光盘1张）（连续8月Java类全国零售排行前2名，32小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '明日科技', '60', '41', '7', '清华大学出版社', '2012-9-1', '3', '564', '1013000', '2012-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22862060-1_w.jpg', 'book_img/22862060-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('5C68141786B84A4CB8929A2415040739', 'JavaScript高级程序设计(第3版)(JavaScript技术名著，国内JavasScript第一书，销量超过8万册)', 'Nicholas C. Zakas', '99', '68', '7', '人民邮电出版社', '2012-3-1', '1', '730', '1092000', '2012-3-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22628333-1_w.jpg', 'book_img/22628333-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('5EDB981339C342ED8DB17D5A198D50DC', 'Java程序性能优化', '葛一鸣', '59', '41', '7', '清华大学出版社', '2012-10-1', '1', '400', '649000', '2012-10-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22881618-1_w.jpg', 'book_img/22881618-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('6398A7BA400D40258796BCBB2B256068', 'JavaScript设计模式', 'Addy Osmani', '49', '34', '7', '人民邮电出版社', '2013-6-1', '1', '241', '301000', '2013-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23266635-1_w.jpg', 'book_img/23266635-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('676B56A612AF4E968CF0F6FFE289269D', 'JavaScript和jQuery实战手册（原书第2版）', '麦克法兰', '99', '68', '7', '机械工业出版社', '2013-3-11', '1', '504', '0', '2013-3-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23201813-1_w.jpg', 'book_img/23201813-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('7917F5B19A0948FD9551932909328E4E', 'Java项目开发案例全程实录（第2版）（配光盘）（软件项目开发全程实录丛书）', '明日科技', '70', '48', '7', '清华大学出版社', '2011-1-1', '2', '605', '1037000', '2011-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20991549-1_w_1.jpg', 'book_img/20991549-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('7C0C785FFBEC4DEC802FA36E8B0BC87E', '深入分析Java Web技术内幕', '许令波', '69', '48', '7', '电子工业出版社', '2012-9-1', '1', '442', '746000', '2012-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22881803-1_w.jpg', 'book_img/22881803-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('7CD79C20258F477AB841518D9312E843', 'Java程序员面试宝典（第三版）', '欧立奇', '49', '34', '7', '电子工业出版社', '2013-9-1', '1', '359', '446400', '2013-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23348683-1_w_1.jpg', 'book_img/23348683-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('7D7FE81293124793BDB2C6FF1F1C943D', '21天学通Java(第6版)（中文版累计销量超30000册）', 'Rogers Cadenhead', '55', '38', '7', '人民邮电出版社', '2013-4-1', '1', '410', '781000', '2013-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23219731-1_w.jpg', 'book_img/23219731-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('7FD7F50B15F74248AA769798909F8653', 'Java网络编程（第3版）——O’Reilly Java系列', '哈诺德', '85', '51', '6', '中国电力出版社', '2005-11-1', '1', '718', '668000', '2005-11-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/9062293-1_w.jpg', 'book_img/9062293-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('819FF56E4423462394E6F83882F78975', '学通Java Web的24堂课（配光盘）（软件开发羊皮卷）', '陈丹丹', '80', '55', '7', '清华大学出版社', '2011-6-1', '1', '718', '1488000', '2011-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/21118835-1_w_1.jpg', 'book_img/21118835-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('81FADA99309342F4978D5C680B0C6E8C', 'Java入门很简单（配光盘）（入门很简单丛书）（打开Java编程之门 Java技术网推荐）', '李世民', '60', '41', '7', '清华大学出版社', '2012-8-1', '1', '459', '745000', '2012-8-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22839309-1_w.jpg', 'book_img/22839309-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('89A57D099EA14026A5C3D10CFC10C22C', 'Java 2实用教程（第4版）（21世纪高等学校计算机基础实用规划教材）', '耿祥义', '39', '32', '8', '清华大学出版社', '2012-8-1', '4', '479', '782000', '2012-8-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22844118-1_w.jpg', 'book_img/22844118-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('8A5B4042D5B14D6B87A34DABF327387F', 'Java核心技术 卷II：高级特性(第9版·英文版)(上、下册)', '霍斯特曼', '119', '82', '7', '人民邮电出版社', '2013-7-1', '1', '1118', '1370000', '2013-7-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23280478-1_w.jpg', 'book_img/23280478-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('8DD0ADF2665B40899E09ED2983DC3F7B', 'jQuery权威指南（被公认的权威的、易学的jQuery实战教程，多次重印，热销中）', '陶国荣', '59', '41', '7', '机械工业出版社', '2011-1-1', '1', '385', '0', '2011-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/21006995-1_w_1.jpg', 'book_img/21006995-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('8E16D59BA4C34374A68029AE877613C4', '轻量级Java EE企业应用实战（第3版）：Struts 2＋Spring 3＋Hibernate整合开发(含CD光盘1张)', '李刚', '99', '68', '7', '电子工业出版社', '2012-4-1', '1', '816', '1440000', '2012-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22685703-1_w.jpg', 'book_img/22685703-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('8F1520F2CED94C679433B9C109E791CB', 'Java从入门到精通（实例版）（附光盘1张）（连续8月Java类全国零售排行前2名，14小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '明日科技', '70', '48', '7', '清华大学出版社', '2012-9-1', '1', '548', '986000', '2012-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22862061-1_w.jpg', 'book_img/22862061-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('90E423DBE56042838806673DB3E86BD3', '《Spring技术内幕（第2版）》（畅销书全新升级，Spring类图书销量桂冠，从宏观和微观两个角度解析Spring架构设计和实现原理）', '计文柯', '69', '48', '7', '机械工业出版社', '2012-2-1', '2', '399', '0', '2012-2-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22606836-1_w.jpg', 'book_img/22606836-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('926B8F31C5D04F61A72F66679A0CCFFD', 'JavaScript编程精解（华章程序员书库）（JavaScript之父高度评价并强力推荐，系统学习JS首选！）', '哈弗贝克', '49', '34', '7', '械工业出版社', '2012-9-1', '1', '162', '0', '2012-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22873894-1_w.jpg', 'book_img/22873894-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('95AACC68D64D4D67B1E33E9EAC22B885', 'Head First Java（中文版）（JAVA经典畅销书 生动有趣 轻松学好JAVA）', '塞若', '79', '47', '6', '中国电力出版社', '2007-2-1', '1', '689', '983000', '2001-7-2', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/9265169-1_w.jpg', 'book_img/9265169-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('97437DAD03FA456AA7D6154614A43B55', 'HTML、CSS、JavaScript网页制作从入门到精通（两万读者的选择，经久不衰的超级畅销书最新升级版！网页制作学习者入门必读经典！）', '刘西杰', '49', '33', '7', '人民邮电出版社', '2012-12-24', '1', '450', '705000', '2012-12-24', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22928649-1_w.jpg', 'book_img/22928649-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('9923901FBF124623BC707920D8936BC8', 'JavaScript DOM编程艺术(第2版)', '基思', '49', '34', '7', '人民邮电出版社', '2011-4-1', '1', '286', '443000', '2011-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/21049601-1_w_1.jpg', 'book_img/21049601-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('99BF63AC12AD48FCB673F1820888964E', 'Java Web开发实战1200例（第Ⅱ卷）（史上最全的“编程实例”类图书，代码分析、实例速查、练习巩固的绝好帮手）', '无', '99', '67', '7', '清华大学出版社', '0', '0', '0', '1746000', '2011-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/21110929-1_w_1.jpg', 'book_img/21110929-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('9D257176A6934CB79427CEC37E69249F', '疯狂Ajax讲义（第3版）--jQuery/Ext JS/Prototype/DWR企业应用前端开发实战(含CD光盘1张)（畅销书升级版，企业应用前端开发实战指南）', '李刚', '79', '54', '7', '电子工业出版社', '2013-2-1', '1', '624', '997000', '2013-2-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23184673-1_w.jpg', 'book_img/23184673-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('9FBD51A7C02D4F5B9862CD2EBBF5CA04', 'Flash ActionScript 3.0全站互动设计', '刘欢 ', '70', '48', '7', '人民邮电出版社', '2012-10-1', '1', '488', '760000', '2012-10-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22886581-1_w.jpg', 'book_img/22886581-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('9FF423101836438F874035A48498CF45', 'Java编程思想（英文版·第4版）', '埃克尔 ', '79', '54', '7', '机械工业出版社', '2007-4-1', '1', '1482', '0', '2007-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/9288920-1_w.jpg', 'book_img/9288920-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('A3D464D1D1344ED5983920B472826730', 'Java Web开发详解：XML+DTD+XML Schema+XSLT+Servlet 3 0+JSP 2 2深入剖析与实例应用(含CD光盘1张)', '孙鑫', '119', '61', '5', '电子工业出版社', '2012-5-1', '1', '889', '1760000', '2012-5-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22788412-1_w.jpg', 'book_img/22788412-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('A46A0F48A4F649AE9008B38EA48FAEBA', 'Java编程全能词典(含DVD光盘2张)', '明日科技', '98', '66', '7', '电子工业出版社', '2010-3-1', '1', '486', '496000', '2010-3-1', '32', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20813806-1_w_1.jpg', 'book_img/20813806-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('A5A6F27DCD174614850B26633A0B4605', 'JavaScript模式', '斯特凡洛夫', '38', '23', '6', '中国电力出版社', '2012-7-1', '1', '208', '253000', '2012-7-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22819430-1_w.jpg', 'book_img/22819430-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('A7220EF174704012830E066FDFAAD4AD', 'Spring 3.0就这么简单（国内原创的Java敏捷开发图书，展现作者Spring原创开源项目ROP开发的全过程，所有项目工程均以Maven组织）', '陈雄华', '59', '41', '7', '人民邮电出版社', '2013-1-1', '1', '380', '530000', '2013-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22938474-1_w.jpg', 'book_img/22938474-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('A7EFD99367C9434682A790635D3C5FDF', 'Java Web技术整合应用与项目实战（JSP+Servlet+Struts2+Hibernate+Spring3）', '张志锋', '98', '68', '7', '清华大学出版社', '2013-6-1', '1', '878', '0', '2013-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23266270-1_w.jpg', 'book_img/23266270-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('A8EF76FD21A645109538614DEA85F3F7', 'Java语言程序设计：基础篇（原书第8版）', '梁勇', '75', '52', '7', '机械工业出版社', '2011-6-1', '1', '586', '0', '2011-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/21122188-1_w_1.jpg', 'book_img/21122188-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('AD6EA79CCB8240AAAF5B292AD7E5DCAA', 'jQuery Mobile权威指南（根据jQuery Mobile最新版本撰写的权威参考书！全面讲解jQuery Mobile的所有功能、特性、使用方法和开发技巧）', '陶国荣', '59', '41', '7', '机械工业出版社', '2012-8-1', '1', '249', '0', '2012-8-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22847009-1_w.jpg', 'book_img/22847009-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('AE0935F13A214436B8599DE285A86220', 'JavaScript基础教程(第8版)(经典JavaScript入门书 涵盖Ajax和jQuery)', 'Tom Negrino　Dori Smith', '69', '48', '7', '人民邮电出版社', '2012-4-1', '1', '392', '694000', '2012-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22717349-1_w.jpg', 'book_img/22717349-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('AF28ED8F692C4288B32CF411CBDBFC23', '经典Java EE企业应用实战——基于WebLogic/JBoss的JSF+EJB 3+JPA整合开发(含CD光盘1张)', '无', '79', '54', '7', '电子工业出版社', '2010-8-1', '1', '0', '0', '2010-8-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20928547-1_w_1.jpg', 'book_img/20928547-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('B329A14DDEF8455F82B3FDF25821D2BB', '名师讲坛——Java Web开发实战经典基础篇（JSP、Servlet、Struts、Ajax）（32小时全真课堂培训，视频超级给力！390项实例及分析，北京魔乐科技培训中心Java Web全部精华）', '李兴华', '70', '48', '7', '清华大学出版社', '2010-8-1', '1', '554', '819000', '2010-8-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20915948-1_w_3.jpg', 'book_img/20915948-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('B7A7DA7A94E54054841EED1F70C3027C', '锋利的jQuery(第2版)(畅销书升级版，增加jQuery Mobile和性能优化)', '单东林', '49', '34', '7', '人民邮电出版社', '2012-7-1', '2', '380', '598000', '2012-7-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22786088-1_w.jpg', 'book_img/22786088-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('BD1CB005E4A04DCA881DA8689E21D4D0', 'jQuery UI开发指南', 'Eric Sarrion', '39', '27', '7', '人民邮电出版社', '2012-12-1', '1', '212', '286000', '2012-12-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22910975-1_w.jpg', 'book_img/22910975-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('C23E6E8A6DB94E27B6E2ABD39DC21AF5', 'JavaScript:The Good Parts(影印版）', '克罗克福特', '28', '19', '7', '东南大学出版社', '2009-1-1', '1', '153', '181000', '2009-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20412979-1_w.jpg', 'book_img/20412979-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('C3CF52B3ED2D4187A16754551488D733', 'Java从入门到精通（附光盘）', '魔乐科技', '59', '35', '6', '人民邮电出版社', '2010-4-1', '1', '519', '884000', '2010-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20810282-1_w_1.jpg', 'book_img/20810282-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('C86D3F6FACB449BEBD940D9307ED4A47', '编写高质量代码：改善Java程序的151个建议(从语法、程序设计和架构、工具和框架、编码风格、编程思想5个方面探讨编写高质量Java代码的技巧、禁忌和最佳实践)', '秦小波', '59', '41', '7', '机械工业出版社', '2012-1-1', '1', '303', '0', '2012-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22579686-1_w.jpg', 'book_img/22579686-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('CB0AB3654945411EA69F368D0EA91A00', 'JavaScript语言精粹（修订版）', '道格拉斯·克罗克福德', '49', '39', '8', '电子工业出版社', '2012-9-1', '1', '155', '258000', '2012-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22872884-1_w.jpg', 'book_img/22872884-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('CD913617EE964D0DBAF20C60076D32FB', '名师讲坛——Java开发实战经典（配光盘）（60小时全真课堂培训，视频超级给力！790项实例及分析，北京魔乐科技培训中心Java全部精华）', '李兴华', '80', '55', '7', '清华大学出版社', '2009-8-1', '1', '831', '1222000', '2012-8-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20637368-1_w_2.jpg', 'book_img/20637368-1_b_2.jpg', null);
INSERT INTO `s_book` VALUES ('CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '布鲁斯.艾克尔', '108', '74', '7', '机械工业出版社', '2007-6-1', '1', '880', '0', '2007-6-1', '0', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/9317290-1_w.jpg', 'book_img/9317290-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('CF5546769F2842DABB2EF7A00D51F255', 'jQuery开发从入门到精通（配套视频327节，中小实例232个，实战案例7个商品详情手册11部，网页模版83类）（附1DVD）', '袁江', '80', '55', '7', '清华大学出版社', '2013-6-1', '1', '619', '1109000', '2013-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23263012-1_w.jpg', 'book_img/23263012-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('D0DA36CEE42549FFB299B7C7129761D5', 'Java应用架构设计：模块化模式与OSGi(全球资深Java技术专家的力作，系统、全面地讲解如何将模块化设计思想引入开发中，涵盖18个有助于实现模块化软件架构的模式)', '克内恩席尔德', '69', '48', '7', '机械工业出版社', '2013-9-1', '1', '251', '0', '2013-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23339643-1_w.jpg', 'book_img/23339643-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('D0E69F85ACAB4C15BB40966E5AA545F1', 'Java并发编程实战（第16届Jolt大奖提名图书，Java并发编程必读佳作', 'Brian Goetz', '69', '48', '7', '机械工业出版社', '2012-2-1', '1', '290', '0', '2012-2-1', '32', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22606835-1_w.jpg', 'book_img/22606835-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('D2ABA8B06C524632846F27C34568F3CE', 'Java 经典实例', '达尔文', '98', '68', '7', '中国电力出版社', '2009-2-1', '1', '784', '805000', '2009-2-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20500255-1_w.jpg', 'book_img/20500255-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('D8723405BA054C13B52357B8F6AEEC24', '深入理解Java虚拟机：JVM高级特性与最佳实践（第2版）', '周志明', '79', '54', '7', '机械工业出版社', '2013-6-1', '2', '433', '0', '2013-6-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23259731-1_w.jpg', 'book_img/23259731-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('DC36FD53A1514312A0A9ADD53A583886', 'JavaScript异步编程：设计快速响应的网络应用【掌握JavaScript异步编程必杀技，让代码更具响应度！ 】', 'Trevor Burnham ', '32', '22', '7', '人民邮电出版社', '2013-6-1', '1', '118', '98000', '2013-5-23', '32', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23252196-1_w.jpg', 'book_img/23252196-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('DCB64DF0084E486EBF173F729A3A630A', 'Java设计模式(第2版)', 'Steven John Metsker', '75', '52', '7', '电子工业出版社', '2012-9-1', '1', '0', '0', '2012-9-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22868759-1_w.jpg', 'book_img/22868759-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('DEE7BDC7E0E343149E3C3601D2658171', '疯狂HTML 5/CSS 3/JavaScript讲义(含CD光盘1张)', '李刚', '69', '48', '7', '电子工业出版社', '2012-5-1', '1', '500', '819000', '2012-5-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22783904-1_w.jpg', 'book_img/22783904-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('DF4E74EEE89B43229BB8212F0B858C38', '精通Hibernate：Java对象持久化技术详解（第2版）(含光盘1张)', '孙卫琴', '75', '52', '7', '电子工业出版社', '2010-2-1', '1', '695', '1148800', '2010-2-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20773347-1_w_1.jpg', 'book_img/20773347-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('E4F184188C8B4C7BB32D4E76603426AC', '疯狂Java讲义（第2版，附光盘）', '李刚', '109', '75', '7', '电子工业出版社', '2012-1-1', '1', '844', '1747000', '2012-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22588603-1_w.jpg', 'book_img/22588603-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('EA695342393C4BE48B831FA5E6B0E5C4', '编写可维护的JavaScript《JavaScript高级程序设计》作者Nicholas Zakas最新力作，构建编码风格手册，帮助开发团队从“游击队”走向“正规军”）', 'Nicholas C. Zakas', '55', '38', '7', '人民邮电出版社', '2013-4-1', '1', '227', '400000', '2013-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23200995-1_w.jpg', 'book_img/23200995-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('F0E34313BF304CCEBF198BD4E05307B8', 'jQuery Cookbook中文版（jQuery之父鼎力推荐，社区数十位专家倾情力作', 'jQuery社区专家组', '69', '48', '7', '人民邮电出版社', '2013-5-1', '1', '425', '573000', '2013-5-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23219358-1_w.jpg', 'book_img/23219358-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('F6162799E913423EA5CB57BEC65AB1E9', 'JUnit实战(第2版)', '塔凯文', '79', '54', '7', '人民邮电出版社', '2012-4-1', '1', '442', '640000', '2012-4-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22633574-1_w.jpg', 'book_img/22633574-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('F693239BC3B3444C8538ABE7411BB38E', 'Java Web典型模块与项目实战大全（配光盘）', '常建功', '99', '69', '7', '清华大学出版社', '2011-1-1', '1', '922', '1473000', '2011-1-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/20988080-1_w_1.jpg', 'book_img/20988080-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('F78C94641DB4475BBA1E72A07DF9B3AE', 'JAVA面向对象编程', '孙卫琴 ', '66', '45', '7', '电子工业出版社', '2006-7-1', '1', '625', '1030400', '2006-7-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/9186890-1_w.jpg', 'book_img/9186890-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('FC232CD9B6E6411BBBB1A5B781D2C3C9', 'Java与模式(含盘)（超多实例和习题，详解设计原则与设计模式）', '阎宏', '88', '61', '7', '电子工业出版社', '2002-10-1', '1', '1024', '16704000', '2002-10-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/696673-1_w.jpg', 'book_img/696673-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('FEC3740CF30E442A94021911A25EF0D7', 'Spring攻略(第2版)(Spring攻略(第2版))', 'Gary Mak　Josh Long　Daniel Rubio', '128', '88', '7', '人民邮电出版社', '2012-3-1', '1', '938', '1322000', '2012-3-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/22623020-1_w.jpg', 'book_img/22623020-1_b.jpg', null);
INSERT INTO `s_book` VALUES ('FFABBED1E5254BC0B2726EC4ED8ACCDA', '深入理解OSGi：Equinox原理、应用与最佳实践（《深入理解Java虚拟机》作者新作！全面解读最新OSGi R5.0规范，深入讲解OSGi原理和服务，以及Equinox框架的用法和原理）', '周志明', '79', '54', '7', '机械工业出版社', '2013-2-1', '1', '414', '0', '2013-2-1', '16', '胶版纸', '8B0406A10F4045368A3296B67A0441C7', 'book_img/23179003-1_w.jpg', 'book_img/23179003-1_b.jpg', null);

-- ----------------------------
-- Table structure for s_cartitem
-- ----------------------------
DROP TABLE IF EXISTS `s_cartitem`;
CREATE TABLE `s_cartitem` (
  `cartItemId` varchar(100) NOT NULL,
  `quantity` int(30) DEFAULT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `bid` varchar(100) DEFAULT NULL,
  `orderBy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cartItemId`),
  KEY `ForeignKey2` (`uid`),
  KEY `ForeignKey3` (`bid`),
  CONSTRAINT `ForeignKey2` FOREIGN KEY (`uid`) REFERENCES `s_user` (`uid`),
  CONSTRAINT `ForeignKey3` FOREIGN KEY (`bid`) REFERENCES `s_book` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_cartitem
-- ----------------------------
INSERT INTO `s_cartitem` VALUES ('4D4B8F2689C0452DB93C9A3D2635EB95', '1', '86A41C61E6984EEEBC10AC17AACBCCD4', '99BF63AC12AD48FCB673F1820888964E', null);
INSERT INTO `s_cartitem` VALUES ('597E4DBDB85A4334BE56576FD5183EF0', '2', '86A41C61E6984EEEBC10AC17AACBCCD4', '000A18FDB38F470DBE9CD0972BADB23F', null);
INSERT INTO `s_cartitem` VALUES ('7B768C8A82F648CBBEE2631E0E9157FF', '1', '86A41C61E6984EEEBC10AC17AACBCCD4', 'B329A14DDEF8455F82B3FDF25821D2BB', null);
INSERT INTO `s_cartitem` VALUES ('F091D750287743D795B0E7DFF9E1E577', '3', '86A41C61E6984EEEBC10AC17AACBCCD4', '0EE8A0AE69154287A378FB110FF2C780', null);

-- ----------------------------
-- Table structure for s_category
-- ----------------------------
DROP TABLE IF EXISTS `s_category`;
CREATE TABLE `s_category` (
  `cid` varchar(100) NOT NULL,
  `cname` varchar(50) DEFAULT NULL,
  `pid` varchar(100) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `orderBy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_category
-- ----------------------------
INSERT INTO `s_category` VALUES ('026B65E734624FE4B2553C80C75FCFC8', '哲学/宗教', '497128CC36E5412DB911590772835FA1', '哲学/宗教', null);
INSERT INTO `s_category` VALUES ('03A7A1BC3FFD45609EFA04DA66F0923A', '文艺', null, '文艺类', null);
INSERT INTO `s_category` VALUES ('0C60181CAAD5430AADDB972E5EB1AE59', '历史', '497128CC36E5412DB911590772835FA1', '历史', null);
INSERT INTO `s_category` VALUES ('15C63C4A411044ABB6BA13529D1E7A98', '励志/成功', null, '励志/成功类', null);
INSERT INTO `s_category` VALUES ('2CFE512ABDCB4C4084586832C3C1F02B', '艺术', '03A7A1BC3FFD45609EFA04DA66F0923A', '艺术', null);
INSERT INTO `s_category` VALUES ('2E22F4DABB574F6DA0BFB315882B7791', '旅游', 'FF8456EE5CB942D5BDDD99176DC4AA9A', '旅游', null);
INSERT INTO `s_category` VALUES ('34AC95430EFA4C988102297FA82211CE', '教育', null, '教育类', null);
INSERT INTO `s_category` VALUES ('39D9F64B150547B5BBD87B22A5B4CEA7', '工具书', '34AC95430EFA4C988102297FA82211CE', '工具书', null);
INSERT INTO `s_category` VALUES ('3DEC502167DB41A8BD9BDAA5D8D407EB', '传记', '03A7A1BC3FFD45609EFA04DA66F0923A', '传记', null);
INSERT INTO `s_category` VALUES ('497128CC36E5412DB911590772835FA1', '人文社科', null, '人文社科类', null);
INSERT INTO `s_category` VALUES ('5FCE29462448445A9D04BC0611507DE7', '中国古典小说', '7F1C6EE8835F446CA52ABAFC2602AF22', '中国古典小说', null);
INSERT INTO `s_category` VALUES ('639B0C544E494759901B4E7C06CC9109', '经管', null, '经管类', null);
INSERT INTO `s_category` VALUES ('6808F7D433874D4CA70F3C02B3EE7C7C', '医学', '8E00C5CCDBA4479689275E261354778D', '医学', null);
INSERT INTO `s_category` VALUES ('70D5678DEAA84160A850D4024B92AA0A', '美食', 'FF8456EE5CB942D5BDDD99176DC4AA9A', '美食', null);
INSERT INTO `s_category` VALUES ('7C7BFF4CA05440A3800D1F2B39AF6781', '心理学', '497128CC36E5412DB911590772835FA1', '心理学', null);
INSERT INTO `s_category` VALUES ('7ECB3DBB652040BAA5322CFF55A87E25', '社会科学', '497128CC36E5412DB911590772835FA1', '社会科学', null);
INSERT INTO `s_category` VALUES ('7F1C6EE8835F446CA52ABAFC2602AF22', '小说', null, '小说类', null);
INSERT INTO `s_category` VALUES ('85D37339567445D3AA1066E9A9185CCA', '文学', '03A7A1BC3FFD45609EFA04DA66F0923A', '文学', null);
INSERT INTO `s_category` VALUES ('89BECBFFAC5A4B00B844BACCE9343767', '科幻', '7F1C6EE8835F446CA52ABAFC2602AF22', '科幻', null);
INSERT INTO `s_category` VALUES ('8B0406A10F4045368A3296B67A0441C7', '计算机', '8E00C5CCDBA4479689275E261354778D', '计算机', null);
INSERT INTO `s_category` VALUES ('8E00C5CCDBA4479689275E261354778D', '科技', null, '科技类', null);
INSERT INTO `s_category` VALUES ('953EE6745A7142B5A756E922B54E9879', '摄影', '03A7A1BC3FFD45609EFA04DA66F0923A', '摄影', null);
INSERT INTO `s_category` VALUES ('9C50664C844447C8A0F24783A7DCE121', '经济', '639B0C544E494759901B4E7C06CC9109', '经济', null);
INSERT INTO `s_category` VALUES ('A8D73738DEF84801A93B50472FC1524E', '科普', '8E00C5CCDBA4479689275E261354778D', '科普', null);
INSERT INTO `s_category` VALUES ('B1D86F3828C94CA8A9A90C15FDA95FE8', '建筑', '8E00C5CCDBA4479689275E261354778D', '建筑', null);
INSERT INTO `s_category` VALUES ('C002B6916B5D43EB94C03EB7F541835F', '教材', '34AC95430EFA4C988102297FA82211CE', '教材', null);
INSERT INTO `s_category` VALUES ('C016A95E96454C7DBE1BA58F271A0E43', '古籍', '497128CC36E5412DB911590772835FA1', '古籍', null);
INSERT INTO `s_category` VALUES ('CB751E246E734C82A162F8DEB312E549', '外语', '34AC95430EFA4C988102297FA82211CE', '外语', null);
INSERT INTO `s_category` VALUES ('D2B425603DB8414EA8DA213020AF611E', '社会', '7F1C6EE8835F446CA52ABAFC2602AF22', '社会', null);
INSERT INTO `s_category` VALUES ('D2C6AEA554C445F9B0DD7B8808F95F8D', '中国近现代小说', '7F1C6EE8835F446CA52ABAFC2602AF22', '中国近现代小说', null);
INSERT INTO `s_category` VALUES ('D9D5CEE6FC7F490CB2869BFC9D8F95A9', '管理', '639B0C544E494759901B4E7C06CC9109', '管理', null);
INSERT INTO `s_category` VALUES ('DBF4FFCB739140B38242FA3856B5208A', '考试', '34AC95430EFA4C988102297FA82211CE', '考试', null);
INSERT INTO `s_category` VALUES ('E99FEDEAC2A34ECA9957656F95846FE9', '武侠', '7F1C6EE8835F446CA52ABAFC2602AF22', '武侠', null);
INSERT INTO `s_category` VALUES ('F4351B4C970B4AECAA0B133D23C4EE4D', '休闲', 'FF8456EE5CB942D5BDDD99176DC4AA9A', '休闲', null);
INSERT INTO `s_category` VALUES ('F445DCFD58894387A220A3D8E127EF52', '中国当代小说', '7F1C6EE8835F446CA52ABAFC2602AF22', '中国当代小说', null);
INSERT INTO `s_category` VALUES ('FF141E9E4FE841B59555D287BF212A60', '投资理财', '639B0C544E494759901B4E7C06CC9109', '投资理财', null);
INSERT INTO `s_category` VALUES ('FF8456EE5CB942D5BDDD99176DC4AA9A', '生活', null, '生活类', null);

-- ----------------------------
-- Table structure for s_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `s_evaluate`;
CREATE TABLE `s_evaluate` (
  `evaluateId` varchar(100) NOT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `bid` varchar(100) DEFAULT NULL,
  `evaluateDesc` varchar(255) DEFAULT NULL,
  `evaluateTime` varchar(100) DEFAULT NULL,
  `orderBy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`evaluateId`),
  KEY `ForeignKey4` (`uid`),
  KEY `ForeignKey5` (`bid`),
  CONSTRAINT `ForeignKey4` FOREIGN KEY (`uid`) REFERENCES `s_user` (`uid`),
  CONSTRAINT `ForeignKey5` FOREIGN KEY (`bid`) REFERENCES `s_book` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of s_evaluate
-- ----------------------------
INSERT INTO `s_evaluate` VALUES ('039449D237994C0AAACB7636277DD5D1', '86A41C61E6984EEEBC10AC17AACBCCD4', '0EE8A0AE69154287A378FB110FF2C780', '挺好的！', '2017-05-31 15:41:59', null);
INSERT INTO `s_evaluate` VALUES ('2C861696B456430C802507828A56ECD1', '86A41C61E6984EEEBC10AC17AACBCCD4', '4A9574F03A6B40C1B2A437237C17DEEC', '可以！', '2017-05-28 13:18:13', null);
INSERT INTO `s_evaluate` VALUES ('393A07941E2344C1A7DBB03666513373', '86A41C61E6984EEEBC10AC17AACBCCD4', '0BE0707984014E66BD9F34F534FCE0F7', 'good！', '2017-05-15 14:48:48', null);
INSERT INTO `s_evaluate` VALUES ('3E437DFD5FC846E3A5C05DD87C844B46', '86A41C61E6984EEEBC10AC17AACBCCD4', '0BE0707984014E66BD9F34F534FCE0F7', '棒棒哒！！！！！', '2017-05-15 13:48:55', null);
INSERT INTO `s_evaluate` VALUES ('792A8B08E0E9492294ACEB9AB63B9827', '86A41C61E6984EEEBC10AC17AACBCCD4', '1286B13F0EA54E4CB75434762121486A', '很喜欢！', '2017-05-26 22:16:47', null);
INSERT INTO `s_evaluate` VALUES ('AE6D994342B84CC18F4FFC30F4435E9D', '86A41C61E6984EEEBC10AC17AACBCCD4', '1A15DC5E8A014A58862ED741D579B530', '很棒', '2017-05-28 19:02:52', null);
INSERT INTO `s_evaluate` VALUES ('F56D9528597A40A6A61FB467B8562E09', '3C3A9FF193A948EF9180FECBB1924FA1', '0BE0707984014E66BD9F34F534FCE0F7', '太棒了！', '2017-05-30 23:11:52', null);

-- ----------------------------
-- Table structure for s_order
-- ----------------------------
DROP TABLE IF EXISTS `s_order`;
CREATE TABLE `s_order` (
  `oid` varchar(100) NOT NULL,
  `ordertime` varchar(30) DEFAULT NULL,
  `total` double(30,0) DEFAULT NULL,
  `status` int(20) DEFAULT NULL,
  `consignee` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `orderBy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `ForeignKey6` (`uid`),
  CONSTRAINT `ForeignKey6` FOREIGN KEY (`uid`) REFERENCES `s_user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_order
-- ----------------------------
INSERT INTO `s_order` VALUES ('169888A3C1924D119970B50C2FC6B1F6', '2017-05-26 22:13:15', '68', '6', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('2D4507358F33429E9D4858AA557B89CC', '2017-05-31 13:07:48', '240', '1', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '3C3A9FF193A948EF9180FECBB1924FA1', null);
INSERT INTO `s_order` VALUES ('362BC19AEFB542029ED338F0BD629D02', '2017-05-26 21:13:18', '225', '4', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('3A9D0ADDB5E74A2BA8623AAB90D57552', '2017-05-26 21:19:45', '81', '6', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('3CB1C5D962BB4021BD9AD4552A7910DA', '2017-05-30 22:58:54', '68', '1', '李某', '13000000002', '湖南省湘潭市雨湖区湖南科技大学南校', '3C3A9FF193A948EF9180FECBB1924FA1', null);
INSERT INTO `s_order` VALUES ('410D776D13644B51ABCE7EE8FFEBE7F6', '2017-05-26 22:08:00', '89', '3', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('44A74DA6AE604344B217BD887B5F78C9', '2017-05-28 13:22:05', '55', '5', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('46A856AE9430467A80F1F1D50CA2AF89', '2017-05-28 13:20:55', '41', '2', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('69A80ECB57A74E87AEA1C2FE9F92C18F', '2017-05-06 15:15:23', '136', '5', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('7B14158DF505487990C060A3A4714C1C', '2017-05-26 22:14:08', '96', '6', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('80C79F22C57A4607BB7CB55F9C99B159', '2017-05-28 13:14:21', '41', '4', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('CBE4A0F52F384260A96AF6A5A3E7C153', '2017-05-16 13:42:13', '75', '4', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('D1DD3CC059304A15A44842C3CFA1A123', '2017-05-31 15:39:50', '68', '6', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('DB1AAF14DFFC467585FF169E8797D967', '2017-05-28 13:21:25', '41', '3', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);
INSERT INTO `s_order` VALUES ('F556B5A315ED478E81B73178CBB51738', '2017-05-06 15:15:58', '123', '4', '李某某', '13000000001', '湖南省湘潭市雨湖区湖南科技大学', '86A41C61E6984EEEBC10AC17AACBCCD4', null);

-- ----------------------------
-- Table structure for s_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `s_orderitem`;
CREATE TABLE `s_orderitem` (
  `orderItemId` varchar(50) NOT NULL,
  `quantity` int(20) DEFAULT NULL,
  `subtotal` double(30,0) DEFAULT NULL,
  `bid` varchar(100) DEFAULT NULL,
  `bname` varchar(255) DEFAULT NULL,
  `currPrice` double(20,0) DEFAULT NULL,
  `image_b` varchar(100) DEFAULT NULL,
  `oid` varchar(100) DEFAULT NULL,
  `orderBy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `ForeignKey7` (`oid`),
  CONSTRAINT `ForeignKey7` FOREIGN KEY (`oid`) REFERENCES `s_order` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_orderitem
-- ----------------------------
INSERT INTO `s_orderitem` VALUES ('08C2605083424713B1FF7A4557BF9A2D', '1', '75', '1286B13F0EA54E4CB75434762121486A', 'Java核心技术 卷I：基础知识(第9版·英文版)(上、下册)', '75', 'book_img/23280479-1_b.jpg', 'CBE4A0F52F384260A96AF6A5A3E7C153', null);
INSERT INTO `s_orderitem` VALUES ('0D77213A2BB64F42869C61D58BAD5A86', '1', '55', '000A18FDB38F470DBE9CD0972BADB23F', 'Java Web整合开发实战--基于Struts 2+Hibernate+Spring（99个应用实例，4个项目开发案例，15.5小时配套教学视频，超值DVD光盘含大量开发资源。一本书搞定SSH框架整合开发！）', '55', 'book_img/23268958-1_b.jpg', 'F556B5A315ED478E81B73178CBB51738', null);
INSERT INTO `s_orderitem` VALUES ('152F49AB683248C2A2697B9E754EBCC6', '1', '41', '33ACF97A9A374352AE9F5E89BB791262', '基于MVC的JavaScript Web富应用开发', '41', 'book_img/22757564-1_b.jpg', '46A856AE9430467A80F1F1D50CA2AF89', null);
INSERT INTO `s_orderitem` VALUES ('1A693D12549D45B5B419AB38407B09C0', '1', '41', 'A7220EF174704012830E066FDFAAD4AD', 'Spring 3.0就这么简单（国内原创的Java敏捷开发图书，展现作者Spring原创开源项目ROP开发的全过程，所有项目工程均以Maven组织）', '41', 'book_img/22938474-1_b.jpg', 'DB1AAF14DFFC467585FF169E8797D967', null);
INSERT INTO `s_orderitem` VALUES ('277B96C8CD084871BA6318DDEBB3E5FD', '1', '55', '260F8A3594F741C1B0EB69616F65045B', 'Tomcat与Java Web开发技术详解（第2版）(含光盘1张)', '55', 'book_img/20420983-1_b.jpg', '362BC19AEFB542029ED338F0BD629D02', null);
INSERT INTO `s_orderitem` VALUES ('2C4902F8809C4A898B7E2F0E103E6B66', '1', '81', '28A03D28BAD449659A77330BE35FCD65', 'JAVA核心技术卷II：高级特性（原书第8版）', '81', 'book_img/20446562-1_b.jpg', '3A9D0ADDB5E74A2BA8623AAB90D57552', null);
INSERT INTO `s_orderitem` VALUES ('3A1CBF878E9240DB8DE118F14D436AC9', '2', '136', '0BE0707984014E66BD9F34F534FCE0F7', 'OSGi实战【OSGi规范制定者亲自撰写 汇集Apache项目技术实战经验】', '68', 'book_img/22938396-1_b.jpg', '362BC19AEFB542029ED338F0BD629D02', null);
INSERT INTO `s_orderitem` VALUES ('3BB3DC9E960944CD9C51E176FE1029D4', '1', '68', '13EBF9B1FDE346A683A1C45BD6773ECB', 'Java开发实战1200例（第Ⅱ卷）（史上最全的“编程实例”类图书，代码分析、实例速查、练习巩固的绝好帮手）', '68', 'book_img/21110930-1_b.jpg', '169888A3C1924D119970B50C2FC6B1F6', null);
INSERT INTO `s_orderitem` VALUES ('3CE6FE52B6DC4F3A8E6380B41B03F917', '2', '136', '0BE0707984014E66BD9F34F534FCE0F7', 'OSGi实战【OSGi规范制定者亲自撰写 汇集Apache项目技术实战经验】', '68', 'book_img/22938396-1_b.jpg', '69A80ECB57A74E87AEA1C2FE9F92C18F', null);
INSERT INTO `s_orderitem` VALUES ('452B7BE3231F4ED381F01549D3F2AC1B', '1', '55', '260F8A3594F741C1B0EB69616F65045B', 'Tomcat与Java Web开发技术详解（第2版）(含光盘1张)', '55', 'book_img/20420983-1_b.jpg', '44A74DA6AE604344B217BD887B5F78C9', null);
INSERT INTO `s_orderitem` VALUES ('531CC695198B43BEB99F691C164C54B4', '1', '89', '210A3DCA429049C78B623C3986BEB136', 'JavaScript经典教程套装：JavaScript高级程序设计(第3版)+JavaScript DOM编程艺术(第2版)(套装共2册)(超值附赠《码农》光盘1张)', '89', 'book_img/23224089-1_b.jpg', '410D776D13644B51ABCE7EE8FFEBE7F6', null);
INSERT INTO `s_orderitem` VALUES ('5CAD00F2B63045D5B4A5F7D8183F9D2D', '1', '68', '0BE0707984014E66BD9F34F534FCE0F7', 'OSGi实战【OSGi规范制定者亲自撰写 汇集Apache项目技术实战经验】', '68', 'book_img/22938396-1_b.jpg', 'F556B5A315ED478E81B73178CBB51738', null);
INSERT INTO `s_orderitem` VALUES ('7DB2FF015EAE4BAE939192D4140F4857', '1', '48', '22234CECF15F4ECB813F0B433DDA5365', 'JavaScript从入门到精通（附光盘1张）（连续8月JavaScript类全国零售排行前2名，13小时视频，400个经典实例、369项面试真题、138项测试史上最全资源库）', '48', 'book_img/22862057-1_b.jpg', '7B14158DF505487990C060A3A4714C1C', null);
INSERT INTO `s_orderitem` VALUES ('8523ADA2C6984DAE93614960F7898972', '5', '240', '22234CECF15F4ECB813F0B433DDA5365', 'JavaScript从入门到精通（附光盘1张）（连续8月JavaScript类全国零售排行前2名，13小时视频，400个经典实例、369项面试真题、138项测试史上最全资源库）', '48', 'book_img/22862057-1_b.jpg', '2D4507358F33429E9D4858AA557B89CC', null);
INSERT INTO `s_orderitem` VALUES ('9F39A47186AF4360AE6785A2F4EE57B8', '1', '34', '1A15DC5E8A014A58862ED741D579B530', 'Java深入解析——透析Java本质的36个话题', '34', 'book_img/23363997-1_b.jpg', '362BC19AEFB542029ED338F0BD629D02', null);
INSERT INTO `s_orderitem` VALUES ('A4FC8D8A82514E9790B8561DDF271C07', '1', '41', '4A9574F03A6B40C1B2A437237C17DEEC', 'Spring实战(第3版)（In Action系列中最畅销的Spring图书，近十万读者学习Spring的共同选择）', '41', 'book_img/23254532-1_b.jpg', '80C79F22C57A4607BB7CB55F9C99B159', null);
INSERT INTO `s_orderitem` VALUES ('B0F5702DD45640EFA57C2047D97A3517', '1', '48', '230A00EC22BF4A1DBA87C64800B54C8D', 'Struts2技术内幕：深入解析Struts架构设计与实现原理', '48', 'book_img/22577578-1_b.jpg', '7B14158DF505487990C060A3A4714C1C', null);
INSERT INTO `s_orderitem` VALUES ('CDDE1F85899A4423AD79977823C65A42', '1', '68', '0EE8A0AE69154287A378FB110FF2C780', 'Java核心技术：卷Ⅰ基础知识（原书第8版）', '68', 'book_img/20285763-1_b.jpg', 'D1DD3CC059304A15A44842C3CFA1A123', null);
INSERT INTO `s_orderitem` VALUES ('EF12F8087ED348E595D0F481B39A27BB', '1', '68', '0BE0707984014E66BD9F34F534FCE0F7', 'OSGi实战【OSGi规范制定者亲自撰写 汇集Apache项目技术实战经验】', '68', 'book_img/22938396-1_b.jpg', '3CB1C5D962BB4021BD9AD4552A7910DA', null);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `uid` varchar(100) NOT NULL,
  `loginname` varchar(30) DEFAULT NULL,
  `loginpass` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `status` int(20) DEFAULT NULL,
  `activationCode` varchar(100) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `reloginpass` varchar(30) DEFAULT NULL,
  `verifyCode` varchar(100) DEFAULT NULL,
  `newpass` varchar(30) DEFAULT NULL,
  `orderBy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('08119DDEBEA241B3AFFB040A6EFA5AE1', 'P123', '123456', 'l939670274@163.com', '1', '080D3FD48E23428AAE410DEB9FD803B3D5F89963220644B2B21AD2DA1F841944', null, null, null, null, null, null);
INSERT INTO `s_user` VALUES ('3C3A9FF193A948EF9180FECBB1924FA1', 'L12345', '12345', '939670274@163.com', '1', '8762ED91232C4E37B92251ADF6E64D65F82177EA74C34DEB9B91E759483C8A94', '男', 'L', null, null, null, null);
INSERT INTO `s_user` VALUES ('86A41C61E6984EEEBC10AC17AACBCCD4', 'LP12345', '123456', '939670274@163.com', '1', '8178D684AB034E4BB816B2403EFB40401482F06951C64ACA9C2FEA27ACFBCCEC', '男', 'L', null, null, null, null);
