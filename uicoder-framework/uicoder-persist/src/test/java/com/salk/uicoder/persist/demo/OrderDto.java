package com.salk.uicoder.persist.demo;

import java.math.BigDecimal;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author salkli
 * @since 2022/10/29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private String  order_no;
    private String  prod_name;
    private Map<String,Object> ds_cat;
    private BigDecimal price ;
}