package com.salk.uicoder.persist.injector;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisParameterHandler;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.salk.uicoder.persist.handler.type.DataSourceLinkHandler;
import com.salk.uicoder.persist.metadata.MetaCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author salk
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
@Slf4j
public class StatementHandlerPlugin implements Interceptor {

    private long time;


    //方法拦截
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //通过StatementHandler获取执行的sql
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        String columnScript ="<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" + "id,\n" + "<if test=\"data.order_no != null\">order_no,</if>\n" + "<if test=\"data.prod_name != null\">prod_name,</if>\n"+ "<if test=\"data.ds_cat.ds_cat_id != null\">ds_cat_id,</if>\n"+ "<if test=\"data.ds_cat.ds_cat_name != null\">ds_cat_name,</if>\n"+ "<if test=\"data.price != null\">price,</if>\n" + "</trim>";
        //String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(null),
        //        LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        //String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(null),
        //      LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript="<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" + "#{id},\n" + "<if test=\"data.order_no != null\">#{order_no},</if>\n" + "<if test=\"data.prod_name != null\">#{data.prod_name},</if>\n"  + "<if test=\"data.ds_cat.ds_cat_id != null\">#{data.ds_cat.ds_cat_id},</if>\n" + "<if test=\"data.ds_cat.ds_cat_name != null\">#{data.ds_cat.ds_cat_name},</if>\n"+ "<if test=\"data.price != null\">#{data.price},</if>\n" + "</trim>";

        String sql = String.format(SqlMethod.SAVE.getSql(),"goods_order", columnScript, valuesScript);
        MybatisConfiguration configuration = (MybatisConfiguration)metaObject.getValue("delegate.configuration");
        SqlSource sqlSource = configuration.getDefaultScriptingLanguageInstance().createSqlSource(configuration, sql, Map.class);
        BoundSql boundSql = sqlSource.getBoundSql(statementHandler.getBoundSql().getParameterObject());
        MetaCache.addCachedSqlBound("goods_order",boundSql);
        List<ParameterMapping> value = boundSql.getParameterMappings();
        for(ParameterMapping parameterMapping:value){
            MetaObject metaObject2 = SystemMetaObject.forObject(parameterMapping);
            //metaObject2.setValue("");
            if(metaObject2.getValue("property").equals("data.ds_cat.ds_cat_name")){
                metaObject2.setValue("javaType", DataSourceLinkHandler.class);
                metaObject2.setValue("typeHandler",new DataSourceLinkHandler());
            }

        }
        metaObject.setValue("delegate.boundSql", boundSql);
        String sql2 = boundSql.getSql();

        long start = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        long end = System.currentTimeMillis();
        log.info("本次数据库操作是慢查询，sql是:" + sql2);
        return proceed;
    }

    //获取到拦截的对象，底层也是通过代理实现的，实际上是拿到一个目标代理对象
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    //获取设置的阈值等参数
    @Override
    public void setProperties(Properties properties) {
        this.time = Long.parseLong(properties.getProperty("time"));
    }
}