package com.salk.uicoder.persist.metadata;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author salkli
 * @since 2022/10/28
 **/
@Data
public class FieldInfo {
    private String fieldId;
    private String fileName;
    private String fieldType;
    private List<String> relatedField=new ArrayList();
}
