package com.salk.uicoder.persist.metadata;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * 表信息
 *
 * @author salkli
 * @date 2022/10/28
 */
@Data
public class TableInfo {
    /**
     * 表id
     */
    private String tableId;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 类型
     */
    private String type;
    /**
     * 字段信息
     */
    private List<FieldInfo> fieldInfos=new ArrayList();

}
