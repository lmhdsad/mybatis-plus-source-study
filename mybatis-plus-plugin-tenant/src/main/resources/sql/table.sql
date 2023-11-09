-- 工厂表，每个工厂的负责人，负责维护工厂的信息
CREATE TABLE `factory` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `factory_name` varchar(255) DEFAULT NULL COMMENT '工厂名称',
  `user_info` varchar(255) DEFAULT NULL COMMENT '用户信息',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;