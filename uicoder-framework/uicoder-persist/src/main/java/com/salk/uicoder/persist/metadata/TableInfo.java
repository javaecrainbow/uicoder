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
    private String tableId;
    private String tableName;
    private String type;
    private List<FieldInfo> fieldInfos=new ArrayList();

}
