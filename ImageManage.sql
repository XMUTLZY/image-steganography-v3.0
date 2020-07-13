/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 106.15.207.108:3306
 Source Schema         : ImageManage

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 13/07/2020 17:40:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `encrypt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `role_id` tinyint(4) NOT NULL DEFAULT 3 COMMENT '管理员角色',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `portrait` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '高级的Jake', '18888800000', '2c7471ab4793dc4d0ab2eb09d22fc863', 'JpMzfQFi3gGkiVgbMMjjvQ==', '林哈哈', 1, 1, '2410267884@qq.com', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7506.JPG', '2019-11-13 17:24:22', '2019-11-13 17:24:25');
INSERT INTO `admin` VALUES (2, '中级的Jake', '18888800001', '4796696fdbe7142bab23348765b53f96', 'GTpkLkZsXY8F7Lxx4GeOWQ==', '林嘻嘻', 2, 1, '1411447305@qq.com', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-13 17:26:37', '2019-11-13 17:26:37');
INSERT INTO `admin` VALUES (3, '低级的Jake', '18888800002', 'a9c47d6f126edacd14589bb139d14ab3', '3JN3Pdzrp5P93/fZ2GNnqw==', '林呜呜', 3, 1, '1220283182@qq.com', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-13 17:27:59', '2019-11-13 17:27:59');
INSERT INTO `admin` VALUES (18, 'jake0', '18965000000', '70c197f9fc065033fdbaaad763ab9745', 'GwNhkmjTGkE3Z67CQ4ZNSA==', NULL, 3, 0, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:45:09', '2019-11-15 17:45:09');
INSERT INTO `admin` VALUES (19, 'jake1', '18965000001', '8d2bf873d7fb86cf0a7bcade90f4ffca', 'Aadf3yidos5inEoHfPomVw==', NULL, 3, 0, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:45:43', '2019-11-15 17:45:43');
INSERT INTO `admin` VALUES (20, 'jake2', '18965000002', '55fc44f4081ec8c657890664f862d9a8', 'DbXcYRQODcSuaejJW9HToA==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:45:48', '2019-11-15 17:45:48');
INSERT INTO `admin` VALUES (21, 'jake3', '18965000003', '77e199fc37fdbb66e8bb59407e863bcf', 't/vvsIFbmPo/milEPGPZRw==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:45:53', '2019-11-15 17:45:53');
INSERT INTO `admin` VALUES (22, 'jake4', '18965000004', '3168a20ea7c0360ac276d8af22e21293', 'cHAdSR5+WWmc2FGA4h4mCA==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:45:56', '2019-11-15 17:45:56');
INSERT INTO `admin` VALUES (23, 'jake5', '18965000005', '4301eb8dea740ce4cba9d324612ca355', 'XpNnkGJQHB5vE+c8xx79kg==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:46:00', '2019-11-15 17:46:00');
INSERT INTO `admin` VALUES (24, 'jake6', '18965000006', '5b0f49543aac6506fcfbffd8b3cf2315', 'aRNn+XIcdGYEUGOzxQdoEg==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:46:03', '2019-11-15 17:46:03');
INSERT INTO `admin` VALUES (25, 'jake7', '18965000007', 'e49ce225847a227ed82bde67bd51ca61', '+VzsgPCFtaYwxayuTK2i+w==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:46:08', '2019-11-15 17:46:08');
INSERT INTO `admin` VALUES (26, 'jake8', '18965000008', '048cbc6720e67157f5670281daa2d3b8', 'L7klQ6yPTWdWWlhn5Mewjg==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:46:12', '2019-11-15 17:46:12');
INSERT INTO `admin` VALUES (28, 'jake9', '18965000009', '1491b99132aa4811c4f3ab02807e411d', '2wXL8xR76gn3cycyOsJzQA==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:46:35', '2019-11-15 17:46:35');
INSERT INTO `admin` VALUES (29, 'jake10', '18965000010', '2ae1858e722678f0265a9fb50d2a54c1', 'zmiG3b2qPi1+kiSQ71jzlA==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:48:51', '2019-11-15 17:48:51');
INSERT INTO `admin` VALUES (30, 'jake11', '18965000011', '309a4b7da32fd1ba96e2024b36f20a65', 'r4EYZWPQlwOAfMNZ0181Dg==', NULL, 3, 1, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:48:55', '2019-11-15 17:48:55');
INSERT INTO `admin` VALUES (31, 'jake12', '18965000012', '4027641b1b705452f9c3945320fc4e6b', '/HA1n6itpI/jOPjPJQ0vdA==', NULL, 3, 0, NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 17:48:58', '2019-11-15 17:48:58');

-- ----------------------------
-- Table structure for admin_privilege
-- ----------------------------
DROP TABLE IF EXISTS `admin_privilege`;
CREATE TABLE `admin_privilege`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `privilege_url` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限url',
  `privilege` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_privilege
-- ----------------------------
INSERT INTO `admin_privilege` VALUES (1, '/admin/addAdmin', '添加管理员', '2019-11-15 09:41:02', '2019-11-15 09:41:02');
INSERT INTO `admin_privilege` VALUES (2, '/admin/updateAdmin', '编辑管理员', '2019-11-15 09:41:02', '2019-11-15 09:41:02');
INSERT INTO `admin_privilege` VALUES (3, '/admin/deleteAdmin', '删除管理员', '2019-11-15 09:41:02', '2019-11-15 09:41:02');
INSERT INTO `admin_privilege` VALUES (4, '/admin/user/addUser', '添加用户', '2019-11-15 09:41:02', '2019-11-15 09:41:02');
INSERT INTO `admin_privilege` VALUES (5, '/admin/user/updateUser', '编辑用户', '2019-11-15 09:41:02', '2019-11-15 09:41:02');
INSERT INTO `admin_privilege` VALUES (6, '/admin/user/deleteUser', '删除用户', '2019-11-15 09:41:02', '2019-11-15 09:41:02');
INSERT INTO `admin_privilege` VALUES (7, '/admin/order/updateOrder', '编辑订单', '2019-11-15 09:41:02', '2019-11-15 09:41:02');
INSERT INTO `admin_privilege` VALUES (8, '/admin/order/deleteOrder', '删除订单', '2019-11-15 09:41:02', '2019-11-15 09:41:02');

-- ----------------------------
-- Table structure for admin_privilege_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_privilege_role`;
CREATE TABLE `admin_privilege_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `privilege_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_privilege_role
-- ----------------------------
INSERT INTO `admin_privilege_role` VALUES (1, 1, 1);
INSERT INTO `admin_privilege_role` VALUES (2, 1, 2);
INSERT INTO `admin_privilege_role` VALUES (3, 1, 3);
INSERT INTO `admin_privilege_role` VALUES (4, 1, 4);
INSERT INTO `admin_privilege_role` VALUES (5, 1, 5);
INSERT INTO `admin_privilege_role` VALUES (6, 1, 6);
INSERT INTO `admin_privilege_role` VALUES (7, 1, 7);
INSERT INTO `admin_privilege_role` VALUES (8, 1, 8);
INSERT INTO `admin_privilege_role` VALUES (9, 2, 4);
INSERT INTO `admin_privilege_role` VALUES (10, 2, 5);
INSERT INTO `admin_privilege_role` VALUES (11, 2, 6);
INSERT INTO `admin_privilege_role` VALUES (12, 2, 7);
INSERT INTO `admin_privilege_role` VALUES (13, 2, 8);
INSERT INTO `admin_privilege_role` VALUES (14, 3, 4);
INSERT INTO `admin_privilege_role` VALUES (15, 3, 5);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, '高级管理员', '拥有网站全部功能', '2019-11-13 17:30:43', '2019-11-13 17:30:45');
INSERT INTO `admin_role` VALUES (2, '中级管理员', '拥有部分功能', '2019-11-13 17:30:47', '2019-11-13 17:30:50');
INSERT INTO `admin_role` VALUES (3, '低级管理员', '极少功能', '2019-11-13 17:30:52', '2019-11-13 17:30:55');

-- ----------------------------
-- Table structure for api_alipay
-- ----------------------------
DROP TABLE IF EXISTS `api_alipay`;
CREATE TABLE `api_alipay`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号',
  `encrypt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mechart_private_key` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商户私钥，您的PKCS8格式RSA2私钥(加密后)',
  `alipay_public_key` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.html对应APPID下的支付宝公钥。(加密后)',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_alipay
-- ----------------------------
INSERT INTO `api_alipay` VALUES (1, '2016092900620870', '+jJ6SpHecovxxDD07ZrcHA==', '77336f6d810d641482545381a731db5be7bba8aafc738eca86107b2f7fc981fb66569b3e7805efb31881913388a804cf02b6907be4ab00f802ed00cc6f8678cf621923f64c46f3bf57d72a55b617b8da2b3a672cf0837c897c3003e568c4f4a2ba30b69dc631d796ffcd0f0816bbc75764d06169b4dc901dc7365c960db768b00b237536674fd5f37660e52cbe9567a751b1fb104fe06674f39d4bdb0515a4ae1d1798d15ee49a61e0a1491fdb5c485514ce87e15e73d4bf1d7bb3a07a5ca18cb9a9125d8873a489936973fb959bca53d651e3550606adf801d0db894799c6d1681b37ab6b3929fe5257fb08b94998470d711d281511e3d31c27ac1ea8d4260a42ab6a216e8039039a497b850bc64ff93956286e007c7e6f17a604a79f41404248577e7ae26d4a7ae6c4d9369d6cf49f0117f71f7bc428eb10ca8e58d524bb04ce06947a80b98a08db1232a8ae7c26326e3350c89854210e05feba902962b339be2e26a2876de0cd2b4c7acd51c199842275bf94639a8d8fd549cbccc590ce79d29e2db10f78655a55fbcddc3964fb7907c527f5277c6f5a28c96848fdcebd6a75ed076c73f1702212f4cc6be58a7f7b3f2a298353cab7b7bf3e132074087d0fd9a8918d2d0cf2fc6a4aa26c214cb5733e1ed04b8bff6dcd72bf8642cde38dc7d5f3e0325ed984aeff6910f4ed73ab91e9ddb29e3715abd0ac8ebfed87fc4f829e045e9364598937a9add66d342cf75e244b3553fc428899464100e90ffc310a8d82b9f002e75399937fb3cde7a5ffd41cd285fa9831375fbf6932067a20d11bc4bc7be71369a0c6952aab47064b795219c8a4aee3e5955d0e0a0d851f568b750ba73285c2a6258d4b36a9e1a8c0423ea1f5e2261d31eca541466207afa51d76b82f49f2227696f3a885ad11f3567c05923a9dd171af4d6456cd1b249e171b648ee0d986835082410fd5595dde35e7b9b5661211645510dcd353b317e7536441cc964c622a74eac47d5f1f520d671fe900eda1654f4720917eb305ba6a1684f1cfbd072b030f61e1393bca7c651645678a1f51937186d7ae2ba02dcae7fdac902f8f2b935a6d6fd30d585b98950f4b35a5d0b807bee0e18aa829c68a57bfd90dcfc2bc6fd7f95ea52168d86ac913caaa24157339f961fff26e839c722cbbb475e85e2dc61ee3aeb5ba4f57ac777372644a39b489825d9c3cfac5da66e3874548d4ca861a2fd7d5e2d582057dd5b5202b95a938a70c0e7634bcd0dfd2376c929234ae529c9e6593afa7ea1b4664df5bbdcd734b60ecb6e1f8fd4c337c1717edd8fa852c79d580a18eeb6c5acf09d3207aaa674f2e2673be4b710cf1e5cad5c130ae9e487e9100138670102a427d588e3a2d12b16ac42bee4111d8fb93f1b652825e99253b98cd4aaf4926c08d8a1cd73dfc9dc74e2ac38bcb0a4d59cb74c9be17eba42ba1530698d46ba6e3bfa50c8ba2b6cc37deb6b6c1270d772141e5b72d5d86fac0b9639507e8e34f736dd112ba7dc1100c0d36c63a3e954fed456d1dd14e1b9d1dd2babf19bc8cf9c36a077f0faad020286b1b5b456c449c34f1f7790516e90b77a24e470f1531f5bf229cc661bf5dc3b19091ec2024c71834a2a5c2a02719395adaebfceacb52556c03545200d7f1b78901fe8bb19b6b9eb6387d9ab255672d764e6e3fc564162d92cc3b4f6da41d5860ac20fee7b0c5d918dfbda5dc66887980a20f19bfa7bd9f04a71acf7af2d41ca93fd2dd1e331fbe8b7f3a30e7bcde42b4c24c37b58a1b32374a1c6bf8afe366e8560556bae3ef9f2706843a10d9ece58fb2827e2cc65d45ca51022c477441e01827e1d1f1c12abfc9d80a8bb524c631d275afc361d9510cff6ce32d25883bcdf6f53ca9735fcd1d3034461fa5684ebf9c3647bbc47d469e46f5e45cb80835b0952b85e09bc7e7a7464f9599de0a7831f07ded4e52f54bca75d1d0103319d27ca3349eb0e2455121d02f580f7483da3c82bec4307bb2a98fa9206b48ec5797f01c67f15743f8287240601f39365389993f3cc202bfa53513d1896c1752496f30a0f88a0f1d620341b6c3b89d9a5693f716ae644e5224ad4617b96c7edbf1b224bae684e7d97737e95c54c0e1de13d2221b62ea9d61705d368b05b1ee7b1f67e37f028a18104d520c88314edbf21f5a34a5e0380cab3cad22f03635971b570967f64c117c53007c49d7ee97aeb2258845efd65500cae6b8b9056b492441db4f46c0b9b4fcd94c4cf89dfd91c225e8b119b3244a1dec2d44897d545c22a8d83d880fce587cc0a2addb03042e43c1d5948363ee4d75e8d7176757472da15c8769f56735414ebd94adf22e2e5c139b001e835e4a8404ddef64b6d8805cb36a50b288e6a738259d543044f49cb76c1ab1ce1dd94ea4bff2e8b67ba8bbc8d708afb3c958cbffc5e2a0c0e4afeebdcca6137fa670dd7e6f3992d7661ff76068df6fda9e91ae8eba2520550855ac7893a485f38e37d5e802990178314b0fbf0f473a4564677a604eb6cb297249229f7ccdedf1c102692de9fc66e8915c80b9442e389a494a01539346f4c3edd615f386c8c4c24beb300f26cb21f9b99fc4ff24a1bc6440bdaac565ee36f09af7a99184731e', '5a2e60e86bb0acdcc776e3d90eead06d040aa640e803fd297c6c970a89c8934c6f936d4f0aec12df117df4536c16516ce35412cf55592c9f59b6a2c36365fe4cfa7e306a830aadc6a3761a7643197b7f8a27e50375709ef7609434f88317c7d913830affd11590daa9d4f6673c0dbe150cc8c5ddfa98d0ae9c2979e40ad329d36a36a30c288762069c28ffb1ee27cf73d1c37c41707fd46f5965e75686a5317aa8a1db837819c086b4c1736dd90906f444ff62ac23eed0e5831613c8cbfc5f9a5313b1670e30812989452714419b757d12370c16f5e776896c6e16a4faab391439f7cfe68cc84abc30fab6ecab77f15deb851e6f04a715590095ed196019e9f0793c7ac823ec2466057dea33da3d6481acf24c01650de30328a7fc30afca8f7a40f85ce05aaced484a68d92c9eee2e470299bab06288a81bf1210abdb3af5ba2c4145ea358c56df67587cbfc8114c4e7492b44073d5d0940dd3f338769bfe2e01b2165a80250875edc9779f7ebe7b00fc667aa23d307cd4568256d5347f74091846e53c6ac5379397b4e945c294dfdb85cdd8bf23b5d54dcad4d01d8805baafc55b3d54585e815c2ff0c3d9a16a85e7002d8d5af6fdf0d8e81ad10ac31080f8bb530c702dde93e04aca4ef4343cec4f9', '2019-12-30 17:40:32');

-- ----------------------------
-- Table structure for api_oss
-- ----------------------------
DROP TABLE IF EXISTS `api_oss`;
CREATE TABLE `api_oss`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_point` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问域名',
  `encrypt` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问秘钥',
  `access_secret` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_oss
-- ----------------------------
INSERT INTO `api_oss` VALUES (1, 'oss-cn-hangzhou.aliyuncs.com', '+jJ6SpHecovxxDD07ZrcHA==', 'LTAI4FdCoeFahvo8UoYCTjdw', 'c0a7bc716a7a67a1a7c2a678a495e7f3e1e108aa985cc6c7238debea218d1a0bf97f1411ee3b3bd98cc29c0afab2e2d8', '2019-12-30 14:12:07');

-- ----------------------------
-- Table structure for api_sms
-- ----------------------------
DROP TABLE IF EXISTS `api_sms`;
CREATE TABLE `api_sms`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sms_content_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求类型',
  `sms_uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密后的uid',
  `sms_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密后的key',
  `sms_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'text',
  `encrypt` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐(AES算法)',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `encryption_times` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'SMS平台数据信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_sms
-- ----------------------------
INSERT INTO `api_sms` VALUES (1, 'application/x-www-form-urlencoded;charset=utf-8', '894240d72072368475e95b467cfc57e7dd5830f4a346a8427c883ee15cd14c6f04878e13f90a0a9a9656d27f793afd6f', 'd0650410896c4f825ca71b030b6ee0ac17bcc7aed3a5e38eda054da483f0bade4b5643328ab0407fb96d1b775d335f80', '（图像隐写在线服务平台-用户注册）您收到的手机验证码为', '+jJ6SpHecovxxDD07ZrcHA==', '2019-12-26 14:56:23', NULL);

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (30);
INSERT INTO `hibernate_sequence` VALUES (30);
INSERT INTO `hibernate_sequence` VALUES (30);
INSERT INTO `hibernate_sequence` VALUES (30);
INSERT INTO `hibernate_sequence` VALUES (30);
INSERT INTO `hibernate_sequence` VALUES (30);
INSERT INTO `hibernate_sequence` VALUES (30);
INSERT INTO `hibernate_sequence` VALUES (30);
INSERT INTO `hibernate_sequence` VALUES (30);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `encrypt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `real_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '用户状态',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `company` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  `career` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` timestamp(6) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(6) NULL DEFAULT NULL COMMENT '更新时间',
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  PRIMARY KEY (`id`, `mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '林哈哈', '18965805130', 'edf8c66fe0d5d37e4a94eae6c097e1bf', 'mrANGHU+/qvv64Gf/U9EEg==', '林圳源', 1, '2410267884@qq.com', '上海齐屹信息科技有限公司', '程序员', 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/a6e1f951-c1b9-4a68-aae2-6a959e20260b.bmp', '2019-11-15 18:02:44.000000', '2020-03-21 02:14:17.372000', '厦门123');
INSERT INTO `user` VALUES (2, '风之子', '18965000000', '70c197f9fc065033fdbaaad763ab9745', 'GwNhkmjTGkE3Z67CQ4ZNSA==', '陈顺发', 1, '1411447884@qq.com', '杭州阿里巴巴集团', '产品', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7294.JPG', '2019-11-15 18:02:44.000000', '2019-11-15 18:02:44.000000', '杭州');
INSERT INTO `user` VALUES (3, '阿萨姆', '18965000001', '8d2bf873d7fb86cf0a7bcade90f4ffca', 'Aadf3yidos5inEoHfPomVw==', '刘东', 1, '9854096241@qq.com', '上海席梦思集团', '运营', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2019-11-15 18:02:44.000000', '2019-11-15 18:02:44.000000', '北京');
INSERT INTO `user` VALUES (4, '咦小姨', '18965000002', '55fc44f4081ec8c657890664f862d9a8', 'DbXcYRQODcSuaejJW9HToA==', '苏静', 1, '244217284@qq.com', '北京字节跳动有限公司', 'UI设计师', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7503.JPG', '2019-11-15 18:02:44.000000', '2019-11-15 18:02:44.000000', '厦门');
INSERT INTO `user` VALUES (5, '天天打游戏', '18965000003', '77e199fc37fdbb66e8bb59407e863bcf', 't/vvsIFbmPo/milEPGPZRw==', '杨智雄', 1, '7410267884@qq.com', '厦门用友烟草软件有限公司', '程序员', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7506.JPG', '2019-11-15 18:02:44.000000', '2019-11-15 18:02:44.000000', '厦门');
INSERT INTO `user` VALUES (6, '好累哦', '18965000004', '3168a20ea7c0360ac276d8af22e21293', 'cHAdSR5+WWmc2FGA4h4mCA==', '冯国兴', 1, '9410267884@qq.com', '厦门美图有限公司', '新媒体运营', 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/a02b7c56-11fe-402c-b5cc-511877f5dc07.JPG', '2019-11-15 18:02:44.000000', '2020-01-17 14:24:03.940000', '厦门');
INSERT INTO `user` VALUES (7, '毕设', '18965000005', '4301eb8dea740ce4cba9d324612ca355', 'XpNnkGJQHB5vE+c8xx79kg==', '阮配配', 1, '2450267884@qq.com', '深圳腾讯科技集团', '游戏策划', 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/0e526f41-c528-4726-90b3-c96ba482e840.JPG', '2019-11-15 18:02:44.000000', '2020-01-17 14:24:16.108000', '深圳');
INSERT INTO `user` VALUES (8, '造数据', '18965000006', '5b0f49543aac6506fcfbffd8b3cf2315', 'aRNn+XIcdGYEUGOzxQdoEg==', '林家', 1, '347267884@qq.com', '福州新大陆软件公司', '文案', 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/fdcb31c4-85bd-4b62-90da-b812a2cf9c4d.JPG', '2019-11-15 18:02:44.000000', '2020-01-17 14:27:54.466000', '福州');
INSERT INTO `user` VALUES (9, '酷酷的腾', '18965000007', 'e49ce225847a227ed82bde67bd51ca61', '+VzsgPCFtaYwxayuTK2i+w==', '陈希', 1, '10267884@qq.com', '厦门齐家网有限公司', '程序员', 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/a0477465-ae39-4ca8-85e5-7f0264499767.JPG', '2019-11-15 18:02:44.000000', '2019-11-15 18:02:44.000000', '厦门');
INSERT INTO `user` VALUES (10, '腿哥的小伙伴', '18965000008', '048cbc6720e67157f5670281daa2d3b8', 'L7klQ6yPTWdWWlhn5Mewjg==', '慕容', 1, '67540267884@qq.com', '泉州万达集团', '经理', 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/c0da57d1-f72c-4954-a630-c668a7fee47a.JPG', '2019-11-15 18:02:44.000000', '2019-11-15 18:02:44.000000', '泉州');
INSERT INTO `user` VALUES (11, '阮阮哟', '18965000009', '1491b99132aa4811c4f3ab02807e411d', '2wXL8xR76gn3cycyOsJzQA==', '陈振农', 1, '877878884@qq.com', '厦门美团公司', '外卖配送', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7506.JPG', '2019-11-15 18:02:44.000000', '2019-11-15 18:02:44.000000', '厦门');
INSERT INTO `user` VALUES (12, '大家好呀', '18965000010', '2ae1858e722678f0265a9fb50d2a54c1', 'zmiG3b2qPi1+kiSQ71jzlA==', '刘新', 1, '212467884@qq.com', '北京百度集团', '人工智能', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7506.JPG', '2019-11-15 18:02:44.000000', '2019-11-15 18:02:44.000000', '厦门');
INSERT INTO `user` VALUES (13, '嘻嘻', '18965000011', '309a4b7da32fd1ba96e2024b36f20a65', 'r4EYZWPQlwOAfMNZ0181Dg==', '金心', 1, '640264884@qq.com', '厦门吉比特有限公司', '程序员', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7506.JPG', '2019-11-15 18:02:44.000000', '2019-11-15 18:02:44.000000', '厦门');
INSERT INTO `user` VALUES (14, '阿狸', '18965000012', '4027641b1b705452f9c3945320fc4e6b', '/HA1n6itpI/jOPjPJQ0vdA==', '陈婷婷', 0, '7777267884@qq.com', '泉州安踏集团', '销售', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7506.JPG', '2019-11-15 18:02:44.000000', '2020-03-20 18:47:39.083000', '厦门');

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `orginal_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始图片',
  `hidden_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '藏入的数据',
  `payment_amount` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款金额',
  `result_image1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结果图1',
  `result_image2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结果图2',
  `download_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '下载状态',
  `payment_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '付款状态',
  `order_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '订单状态',
  `order_time` timestamp(0) NULL DEFAULT NULL COMMENT '订单生成时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES (1, '2020319131433485', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/d2942fee-97ba-4ae8-b0df-157c4552e9c1.bmp', '美少女战士玛丽亚', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/d2942fee-97ba-4ae8-b0df-157c4552e9c1One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/d2942fee-97ba-4ae8-b0df-157c4552e9c1Two.bmp', 0, 1, 1, '2020-03-19 13:02:20');
INSERT INTO `user_order` VALUES (2, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/0e5af5d1-25f0-4edb-9e2b-29cc3e5bcea4.bmp', '房子', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/0e5af5d1-25f0-4edb-9e2b-29cc3e5bcea4One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/0e5af5d1-25f0-4edb-9e2b-29cc3e5bcea4Two.bmp', 0, 0, 0, '2020-03-19 13:40:37');
INSERT INTO `user_order` VALUES (3, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/ec029e90-c3ee-466b-a4ec-bb7d70c0798f.bmp', '美女', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec029e90-c3ee-466b-a4ec-bb7d70c0798fOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec029e90-c3ee-466b-a4ec-bb7d70c0798fTwo.bmp', 0, 0, 0, '2020-03-19 23:08:26');
INSERT INTO `user_order` VALUES (4, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/62c941f5-bd22-4d98-b0d8-17abbc2c8ef2.bmp', '船', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/62c941f5-bd22-4d98-b0d8-17abbc2c8ef2One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/62c941f5-bd22-4d98-b0d8-17abbc2c8ef2Two.bmp', 0, 0, 0, '2020-03-19 23:11:05');
INSERT INTO `user_order` VALUES (5, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/62c941f5-bd22-4d98-b0d8-17abbc2c8ef2.bmp', '船', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/62c941f5-bd22-4d98-b0d8-17abbc2c8ef2One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/62c941f5-bd22-4d98-b0d8-17abbc2c8ef2Two.bmp', 0, 0, 0, '2020-03-19 23:12:11');
INSERT INTO `user_order` VALUES (6, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/37e87c2d-57c6-4f76-a710-4e54d6ece5ee.bmp', '房子', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/37e87c2d-57c6-4f76-a710-4e54d6ece5eeOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/37e87c2d-57c6-4f76-a710-4e54d6ece5eeTwo.bmp', 0, 0, 0, '2020-03-19 23:15:07');
INSERT INTO `user_order` VALUES (7, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/37e87c2d-57c6-4f76-a710-4e54d6ece5ee.bmp', '房子', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/37e87c2d-57c6-4f76-a710-4e54d6ece5eeOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/37e87c2d-57c6-4f76-a710-4e54d6ece5eeTwo.bmp', 0, 0, 0, '2020-03-19 23:15:59');
INSERT INTO `user_order` VALUES (8, '2020319231743437', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/32f84e1b-121e-4997-bc18-2dc86d78f1f0.bmp', '123', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/32f84e1b-121e-4997-bc18-2dc86d78f1f0One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/32f84e1b-121e-4997-bc18-2dc86d78f1f0Two.bmp', 0, 1, 1, '2020-03-19 23:17:34');
INSERT INTO `user_order` VALUES (9, '202032018374327', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/fa092b91-cba2-4ac7-8318-f36c8b4c83ce.jpg', '飞机', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/fa092b91-cba2-4ac7-8318-f36c8b4c83ceOne.jpg', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/fa092b91-cba2-4ac7-8318-f36c8b4c83ceTwo.jpg', 1, 1, 1, '2020-03-20 18:36:35');
INSERT INTO `user_order` VALUES (10, '202032018468919', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/b1815d93-8552-42e7-877f-4a3cfed5a0b1.bmp', '猩猩', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/b1815d93-8552-42e7-877f-4a3cfed5a0b1One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/b1815d93-8552-42e7-877f-4a3cfed5a0b1Two.bmp', 1, 1, 1, '2020-03-20 18:46:05');
INSERT INTO `user_order` VALUES (11, '202042715853907', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/599a5591-4cb0-44b7-9fba-3fda4eeb7a08.jpg', '啊啊打啊啊的', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/599a5591-4cb0-44b7-9fba-3fda4eeb7a08One.jpg', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/599a5591-4cb0-44b7-9fba-3fda4eeb7a08Two.jpg', 0, 0, 1, '2020-03-21 02:06:57');
INSERT INTO `user_order` VALUES (12, '202032121234586', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/d11bce3a-7618-44e9-abc0-0b2a26820b36.bmp', '刘海', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/d11bce3a-7618-44e9-abc0-0b2a26820b36One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/d11bce3a-7618-44e9-abc0-0b2a26820b36Two.bmp', 1, 1, 1, '2020-03-21 02:12:29');
INSERT INTO `user_order` VALUES (13, '2020451279625', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/1969e197-b9af-4764-8273-e2bc3a0379c7.bmp', '船长', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/1969e197-b9af-4764-8273-e2bc3a0379c7One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/1969e197-b9af-4764-8273-e2bc3a0379c7Two.bmp', 1, 1, 1, '2020-04-05 01:27:05');
INSERT INTO `user_order` VALUES (14, '20204523653824', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/6ca46525-0c3e-4551-b4a8-7e58a0ec670d.bmp', '大猩猩秘密数据 测试哦', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/6ca46525-0c3e-4551-b4a8-7e58a0ec670dOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/6ca46525-0c3e-4551-b4a8-7e58a0ec670dTwo.bmp', 0, 1, 1, '2020-04-05 02:36:43');
INSERT INTO `user_order` VALUES (15, '20204271594832', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/6e75f1d7-10f6-46de-b262-d905f6e89ede.bmp', '一家人', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/6e75f1d7-10f6-46de-b262-d905f6e89edeOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/6e75f1d7-10f6-46de-b262-d905f6e89edeTwo.bmp', 0, 0, 1, '2020-04-05 02:56:41');
INSERT INTO `user_order` VALUES (18, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/4733f4fe-eda4-4e07-be77-61e61a2807c2.bmp', '美女哦', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/4733f4fe-eda4-4e07-be77-61e61a2807c2One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/4733f4fe-eda4-4e07-be77-61e61a2807c2Two.bmp', 0, 0, 1, '2020-04-05 23:11:20');
INSERT INTO `user_order` VALUES (19, '2020528212245147', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/c9d083dd-85c7-40b8-8697-a919473e6a0d.bmp', '美少女战士', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/c9d083dd-85c7-40b8-8697-a919473e6a0dOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/c9d083dd-85c7-40b8-8697-a919473e6a0dTwo.bmp', 0, 0, 1, '2020-04-27 14:55:15');
INSERT INTO `user_order` VALUES (20, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/04e2e7dd-0315-45b0-a9b7-a82547c67d14.bmp', '秘密数据', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/04e2e7dd-0315-45b0-a9b7-a82547c67d14One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/04e2e7dd-0315-45b0-a9b7-a82547c67d14Two.bmp', 0, 0, 1, '2020-05-28 13:53:48');
INSERT INTO `user_order` VALUES (21, '2020528212523677', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/04e2e7dd-0315-45b0-a9b7-a82547c67d14.bmp', '秘密数据', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/04e2e7dd-0315-45b0-a9b7-a82547c67d14One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/04e2e7dd-0315-45b0-a9b7-a82547c67d14Two.bmp', 0, 0, 1, '2020-05-28 13:54:12');
INSERT INTO `user_order` VALUES (22, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/4cf73c32-3351-4760-9808-baa701c325d1.jpg', '飞机', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/4cf73c32-3351-4760-9808-baa701c325d1One.jpg', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/4cf73c32-3351-4760-9808-baa701c325d1Two.jpg', 0, 0, 1, '2020-05-28 21:18:27');
INSERT INTO `user_order` VALUES (23, '20205309327196', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/cfe52303-04cd-48b9-bef4-13672fa534bc.jpg', '飞机', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/cfe52303-04cd-48b9-bef4-13672fa534bcOne.jpg', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/cfe52303-04cd-48b9-bef4-13672fa534bcTwo.jpg', 1, 1, 1, '2020-05-30 09:31:45');
INSERT INTO `user_order` VALUES (24, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/1b3d7bb4-f29f-44f6-be39-5b9c1100b930.bmp', '1', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/1b3d7bb4-f29f-44f6-be39-5b9c1100b930One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/1b3d7bb4-f29f-44f6-be39-5b9c1100b930Two.bmp', 0, 0, 1, '2020-05-30 09:34:59');
INSERT INTO `user_order` VALUES (25, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/9ee9ffe7-7c28-4495-bec5-1aa3438ba33e.bmp', '12312', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/9ee9ffe7-7c28-4495-bec5-1aa3438ba33eOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/9ee9ffe7-7c28-4495-bec5-1aa3438ba33eTwo.bmp', 0, 0, 1, '2020-05-30 09:39:11');
INSERT INTO `user_order` VALUES (26, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/b3efb8ec-1563-4ae2-a700-bbf388ff9b9e.jpg', '飞机', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/b3efb8ec-1563-4ae2-a700-bbf388ff9b9eOne.jpg', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/b3efb8ec-1563-4ae2-a700-bbf388ff9b9eTwo.jpg', 0, 0, 1, '2020-05-30 09:53:39');
INSERT INTO `user_order` VALUES (27, '2020661574859', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/b59b7cfa-4ebe-434c-a867-dfad14c148e6.bmp', '林哈哈', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/b59b7cfa-4ebe-434c-a867-dfad14c148e6One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/b59b7cfa-4ebe-434c-a867-dfad14c148e6Two.bmp', 1, 1, 1, '2020-06-06 01:56:48');
INSERT INTO `user_order` VALUES (28, '202061013010586', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/0792480c-8a4f-44b6-b0d4-2dcefaffc582.bmp', '美女', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/0792480c-8a4f-44b6-b0d4-2dcefaffc582One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/0792480c-8a4f-44b6-b0d4-2dcefaffc582Two.bmp', 1, 1, 1, '2020-06-10 12:57:58');
INSERT INTO `user_order` VALUES (29, '202061015179731', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/7b4ea433-b175-4f2d-a05c-a1b6165718c5.bmp', '美女', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/7b4ea433-b175-4f2d-a05c-a1b6165718c5One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/7b4ea433-b175-4f2d-a05c-a1b6165718c5Two.bmp', 0, 0, 1, '2020-06-10 15:15:32');
INSERT INTO `user_order` VALUES (32, '20191115222530432', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/1ac0ce9e-58a2-40fd-b186-d03c8bb1a869.bmp', '波音747 秘密图片，注意隐藏！', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/1ac0ce9e-58a2-40fd-b186-d03c8bb1a869One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/1ac0ce9e-58a2-40fd-b186-d03c8bb1a869Two.bmp', 1, 1, 0, '2019-11-15 22:24:38');
INSERT INTO `user_order` VALUES (35, '20191115223030322', 2, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/41114cec-bc03-42be-ab0f-6f4b875515c7.bmp', '辽宁舰哦', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/41114cec-bc03-42be-ab0f-6f4b875515c7One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/41114cec-bc03-42be-ab0f-6f4b875515c7Two.bmp', 1, 1, 1, '2019-11-15 22:37:21');
INSERT INTO `user_order` VALUES (36, '20191115224336148', 3, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/ac1ceed9-9973-49a9-8815-4fb6a97221b2.bmp', '注意隐藏信息', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ac1ceed9-9973-49a9-8815-4fb6a97221b2One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ac1ceed9-9973-49a9-8815-4fb6a97221b2Two.bmp', 1, 1, 1, '2019-11-15 22:43:17');
INSERT INTO `user_order` VALUES (37, '20191115224536148', 3, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/728705db-9aa5-4038-9cb6-cea9c8aa72d2.bmp', '美女图片', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/728705db-9aa5-4038-9cb6-cea9c8aa72d2One.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/728705db-9aa5-4038-9cb6-cea9c8aa72d2Two.bmp', 0, 1, 1, '2019-11-15 23:11:51');
INSERT INTO `user_order` VALUES (54, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/15fdd8f7-bc11-493d-98cf-558c7e644ed5.bmp', 'test', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 0, 0, 0, '2019-12-30 16:31:42');
INSERT INTO `user_order` VALUES (55, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/03118deb-e1ea-4237-b801-87fb058accb5.bmp', 'test', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 0, 0, 0, '2019-12-30 16:32:24');
INSERT INTO `user_order` VALUES (56, '202012211650679', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/e21c24b7-2b3d-4e6a-84e6-ccd001cc478b.bmp', '测试', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 1, 1, 1, '2019-12-30 18:04:31');
INSERT INTO `user_order` VALUES (57, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/764b3513-ea11-42b2-9d90-875351123a46.bmp', '藏入 ', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 0, 0, 0, '2019-12-30 18:34:32');
INSERT INTO `user_order` VALUES (58, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/2221407c-07b1-4c90-ac2c-6f2e4518d7b9.bmp', 'test', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 0, 0, 0, '2019-12-30 18:41:43');
INSERT INTO `user_order` VALUES (59, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/8ebf0e2f-1166-466c-b748-2e2673392dc2.bmp', '测试', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 0, 0, 0, '2020-01-02 10:12:00');
INSERT INTO `user_order` VALUES (60, NULL, 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/8ebf0e2f-1166-466c-b748-2e2673392dc2.bmp', '测试', NULL, 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 0, 0, 0, '2020-01-02 10:17:57');
INSERT INTO `user_order` VALUES (61, '202012131459954', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/dc2d0b71-2f7c-42dc-9107-1bfcd3fbd140.bmp', '测试', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 1, 1, 1, '2020-01-02 10:22:47');
INSERT INTO `user_order` VALUES (62, '2020310215640863', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/cba20214-92cb-41c8-aa50-f7bd7c861052.bmp', '测试', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 0, 1, 1, '2020-01-02 13:14:16');
INSERT INTO `user_order` VALUES (63, '2020223222828480', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/19dbc89a-1799-4b62-ab4c-571566dc8c37.bmp', 'test', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/ec0d0516-0480-46ca-bf91-10c4b95019ffTwo.bmp', 1, 1, 1, '2020-01-02 10:42:39');
INSERT INTO `user_order` VALUES (70, '202012212322907', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/16860f0f-46ef-4ecc-a936-2feeea53951c.bmp', '测试藏入', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/16860f0f-46ef-4ecc-a936-2feeea53951cOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/16860f0f-46ef-4ecc-a936-2feeea53951cTwo.bmp', 0, 1, 0, '2020-01-02 21:23:16');
INSERT INTO `user_order` VALUES (72, '202012213335109', 1, 'http://image-steganography.oss-cn-hangzhou.aliyuncs.com/image/1661cf5e-4270-4357-9a37-2c3ea0da88aa.bmp', '秘密信息', '5.00', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/1661cf5e-4270-4357-9a37-2c3ea0da88aaOne.bmp', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/resultImage/1661cf5e-4270-4357-9a37-2c3ea0da88aaTwo.bmp', 1, 1, 1, '2020-01-02 21:33:26');

SET FOREIGN_KEY_CHECKS = 1;
