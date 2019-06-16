CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `code` varchar(10) NOT NULL COMMENT '编码',
  `manager_code` varchar(10) DEFAULT NULL COMMENT '负责人工号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';


CREATE TABLE `t_asset` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `code` varchar(10) NOT NULL COMMENT '编码',
  `emp_code` varchar(10) DEFAULT NULL COMMENT '员工工号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资产表';


CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `code` varchar(10) NOT NULL COMMENT '工号',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `gender` int(2) DEFAULT NULL COMMENT '性别，1-男，2-女',
  `dept_code` varchar(10) DEFAULT NULL COMMENT '所属部门编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工表';