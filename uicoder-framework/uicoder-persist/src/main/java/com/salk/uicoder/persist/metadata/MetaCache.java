package com.salk.uicoder.persist.metadata;

import org.apache.ibatis.mapping.BoundSql;

import java.util.HashMap;
import java.util.Map;

/**
 * @author salkli
 * @since 2022/10/30
 **/
public class MetaCache {
    private static Map<String, BoundSql> sqls=new HashMap();
    public static BoundSql getCachedSqlBound(String key){
         return sqls.get(key);
    }
    public static void addCachedSqlBound(String key,BoundSql sql){
         sqls.put(key,sql);
    }
}
