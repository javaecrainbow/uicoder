package com.salk.uicoder.persist.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author salkli
 * @since 2022/10/30
 **/
public class CustomMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createBy = getFieldValByName("create_user", metaObject);
        if (createBy == null) {
            setFieldValByName("create_user", "admin", metaObject);
        }
        Object createTime = getFieldValByName("create_time", metaObject);
        if (createTime == null) {
            setFieldValByName("create_time", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updaterUser = getFieldValByName("updater_user", metaObject);
        if (updaterUser == null) {
            setFieldValByName("update_user", "admin", metaObject);
        }
        Object updateTime = getFieldValByName("update_time", metaObject);
        if (updateTime == null) {
            setFieldValByName("update_time", new Date(), metaObject);
        }
    }
}
