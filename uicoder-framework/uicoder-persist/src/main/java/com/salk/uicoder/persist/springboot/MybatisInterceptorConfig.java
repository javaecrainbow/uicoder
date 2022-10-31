package com.salk.uicoder.persist.springboot;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.google.common.collect.Lists;
import com.salk.uicoder.persist.handler.CustomMetaObjectHandler;
import com.salk.uicoder.persist.handler.UiCoderDataPoolParameterHandler;
import com.salk.uicoder.persist.handler.type.DataSourceLinkHandler;
import com.salk.uicoder.persist.injector.StatementHandlerPlugin;
import com.salk.uicoder.persist.injector.UiCoderSqlInjector;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Collection;
import java.util.List;

@Configuration
public class MybatisInterceptorConfig {

    @Inject
    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void addInterceptor() {
        this.sqlSessionFactory.getConfiguration().addInterceptor(new UiCoderDataPoolParameterHandler());
        this.sqlSessionFactory.getConfiguration().addInterceptor(new StatementHandlerPlugin());
        //sqlSessionFactory.getConfiguration().addMappedStatement(new MappedStatement());
    }

    @Bean
    public SqlSessionFactory mySqlSessionFactory(@Autowired DataSource myDataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(myDataSource);
        // 全局配置
        bean.setGlobalConfig(globalConfig());
        // 配置打印sql语句
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setLogImpl(StdOutImpl.class);
        bean.setTypeHandlers(new DataSourceLinkHandler());
        return bean.getObject();
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig config = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        //dbConfig.setKeyGenerators(Lists.newArrayList(new NoKeyGenerator()));
        dbConfig.setIdType(IdType.values()[1]);
        config.setDbConfig(dbConfig);
        config.setSqlInjector(new UiCoderSqlInjector());
        config.setBanner(false);
        // 全局自动填充配置
        config.setMetaObjectHandler(new CustomMetaObjectHandler());
        return config;
    }
}
