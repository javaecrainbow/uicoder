package com.salk.uicoder.persist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author salkli
 * @since 2022/10/29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableData<T> {
    private String entId;
    private String appId;
    private String tableId;
    private String tableName;
    private T data;

}
