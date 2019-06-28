DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` bigint(32) NOT NULL COMMENT '主键',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `user_id` bigint(32) NOT NULL COMMENT '用户id',
  `order_amount` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '订单总额',
  `order_status` int(4) NOT NULL DEFAULT 1 COMMENT '订单状态，1-进行中，2-已完成，3-已取消',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '留言',
  PRIMARY KEY (`order_id`),
  INDEX `idx_order_user_id`(`user_id`),
  INDEX `idx_order_order_no`(`order_no`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表';


DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
  `order_item_id` bigint(32) NOT NULL COMMENT '主键',
  `order_id` bigint(32) NOT NULL COMMENT '订单id',
  `product_id` bigint(32) NOT NULL COMMENT '商品id',
  `item_price` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '单价',
  `total_num` int(4) NOT NULL DEFAULT 1 COMMENT '数量',
  `total_price` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '订单总额',
  `order_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `user_id` bigint(32) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`order_item_id`),
  INDEX `idx_order_item_order_id`(`order_id`),
  INDEX `idx_order_item_user_id`(`user_id`),
  INDEX `idx_order_item_product_id`(`product_id`),
  INDEX `idx_order_item_order_time`(`order_time`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单明细表';


DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `product_id` bigint(32) NOT NULL COMMENT '主键',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品描述',
  PRIMARY KEY (`product_id`),
  INDEX `idx_user_product_code`(`code`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表';


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` bigint(32) NOT NULL COMMENT '主键',
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `gender` int(2) DEFAULT NULL COMMENT '性别：1-男；2-女',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  PRIMARY KEY (`user_id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表';


DROP TABLE IF EXISTS `t_user_address`;
CREATE TABLE `t_user_address`  (
  `address_id` bigint(32) NOT NULL COMMENT '主键',
  `user_id` bigint(32) NOT NULL COMMENT '用户id',
  `province` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '省',
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '市',
  `district` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '区',
  `detail` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '详细地址',
  `sort` int(4) DEFAULT 1 COMMENT '排序',
  `gender` int(2) DEFAULT 1 COMMENT '性别：1-男；2-女',
  PRIMARY KEY (`address_id`),
  INDEX `idx_user_address_user_id`(`user_id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '地址表';