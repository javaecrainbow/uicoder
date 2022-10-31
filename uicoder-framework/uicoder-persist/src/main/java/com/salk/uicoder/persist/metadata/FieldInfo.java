package com.salk.uicoder.persist.metadata;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * 字段信息
 *
 * @author salk
 * @date 2022/10/29
 */
@Data
public class FieldInfo {
    /**
     * 字段id
     */
    private String fieldId;
    private String fileName;
    private String fieldType;
}
