package com.salk.uicoder.persist;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salk.uicoder.persist.metadata.TableInfo;

import java.util.Map;

/**
 * @author salkli
 * @since 2022/10/28
 **/
public interface TableMapper extends BaseMapper<TableInfo> {

    int insert(Map<String,Object> value);

}
