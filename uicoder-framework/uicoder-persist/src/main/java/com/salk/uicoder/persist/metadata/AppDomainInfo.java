package com.salk.uicoder.persist.metadata;

import lombok.Data;

import java.util.List;

/**
 * @author salkli
 * @since 2022/10/28
 **/
@Data
public class AppDomainInfo {
    private String appId;
    private String appName;
    private List<TableInfo> tableInfos;
}
