package com.salk.uicoder.persist.injector;/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;


@SuppressWarnings("serial")
public class Save extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = NoKeyGenerator.INSTANCE;
        SqlMethod sqlMethod=SqlMethod.SAVE;
        String columnScript ="<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" + "id,\n" + "<if test=\"data.order_no != null\">order_no,</if>\n" + "<if test=\"data.prod_name != null\">prod_name,</if>\n"+ "<if test=\"data.ds_cat.ds_cat_id != null\">ds_cat_id,</if>\n"+ "<if test=\"data.ds_cat.ds_cat_name != null\">ds_cat_name,</if>\n"+ "<if test=\"data.price != null\">price,</if>\n" + "</trim>";
        //String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(null),
        //        LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        //String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(null),
          //      LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript="<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" + "#{id},\n" + "<if test=\"data.order_no != null\">#{order_no},</if>\n" + "<if test=\"data.prod_name != null\">#{data.prod_name},</if>\n"  + "<if test=\"data.ds_cat.ds_cat_id != null\">#{data.ds_cat.ds_cat_id},</if>\n" + "<if test=\"data.ds_cat.ds_cat_name != null\">#{data.ds_cat.ds_cat_name},</if>\n"+ "<if test=\"data.price != null\">#{data.price},</if>\n" + "</trim>";

       String sql = String.format(sqlMethod.getSql(),"", null, null);
       SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, getMethod(sqlMethod), sqlSource, keyGenerator, null, null);
    }
}
