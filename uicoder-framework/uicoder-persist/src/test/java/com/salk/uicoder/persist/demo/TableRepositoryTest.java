package com.salk.uicoder.persist.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;
import com.salk.uicoder.persist.api.TableRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author salkli
 * @since 2022/10/29
 **/
@SpringBootTest
public class TableRepositoryTest {
    @Autowired
    private TableRepository tableRepository;

    /**
     * 测试插入
     */
    @Test
    public void testInsert(){
        //List<UserPo> users = userMapper.selectList(null);
        int insert = tableRepository.save(mockData());
        System.out.println("记录数=========="+insert);
    }

    private Map<String,Object> mockData(){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("tableId", "order");
        param.put("appId", "app_order");
        Map<String,Object> datasource=new HashMap();
        datasource.put("ds_cat_name", "日用品");
        datasource.put("ds_cat_id", 2);
        OrderDto orderDto=new OrderDto(3, "ts_005", "洗发乳", datasource, new BigDecimal("32.444"));
        param.put("data",BeanUtil.beanToMap(orderDto));
        param.put("order_no", "212");
        param.put("id", 4);
        return param;
    }
}
