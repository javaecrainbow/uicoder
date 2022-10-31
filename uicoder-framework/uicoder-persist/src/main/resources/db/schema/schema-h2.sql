DROP TABLE IF EXISTS goods_order;
CREATE TABLE goods_order
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    order_no VARCHAR(30) NULL DEFAULT NULL COMMENT '订单号',
    prod_name VARCHAR(30) NULL DEFAULT NULL COMMENT '商品名称',
    ds_cat_id INT(11) NULL DEFAULT NULL COMMENT '数据源目录id',
    ds_cat_name varchar (30) NULL DEFAULT NULL COMMENT '数据源类型,目录名称',
    price decimal(10,8) NULL DEFAULT NULL COMMENT '价格',
    create_user varchar (30) NULL DEFAULT NULL COMMENT '创建人',
    update_user varchar (30) NULL DEFAULT NULL COMMENT '更新人',
    update_time datetime NULL DEFAULT NULL COMMENT '更新时间',
    create_time datetime NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
);