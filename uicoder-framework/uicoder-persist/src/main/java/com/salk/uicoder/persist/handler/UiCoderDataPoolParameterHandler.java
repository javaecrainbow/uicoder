package com.salk.uicoder.persist.handler;

import java.sql.PreparedStatement;
import java.util.Properties;

import com.baomidou.mybatisplus.core.MybatisParameterHandler;
import com.salk.uicoder.persist.metadata.MetaCache;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * @author salkli
 * @since 2022/10/29
 **/
@Intercepts({@Signature(type = ParameterHandler.class, method = "getParameterObject", args = {}),
             @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})})
@Slf4j
public class UiCoderDataPoolParameterHandler implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getArgs()!=null && invocation.getArgs().length==1){
            BoundSql goods_order = MetaCache.getCachedSqlBound("goods_order");
            MybatisParameterHandler target = (MybatisParameterHandler) invocation.getTarget();
            MetaObject metaObject = SystemMetaObject.forObject(target);
            metaObject.setValue("boundSql",goods_order);
        }
        System.out.println(invocation);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // TODO Auto-generated method stub
        log.info("UiCoderDataPoolParameterHandler plugin>>>>>>>{}", target);
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialect = properties.getProperty("dialect");
        properties.setProperty("entId", "salk_test");
        properties.setProperty("appId", "2134");
        properties.setProperty("tableId", "order");
        log.info("mybatis intercept dialect:>>>>>>>{}", dialect);
    }
}
