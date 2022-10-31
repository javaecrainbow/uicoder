package com.salk.uicoder.persist.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author salkli
 * @since 2022/10/29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPo {
    private Integer id;
    private String  order_no;
    private String  prod_name;
    private Integer  ds_cat_id;
    private String  ds_cat_name;
    private BigDecimal price ;
}