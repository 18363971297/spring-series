package com.lsl.core.mybatis.session;

import com.lsl.core.mybatis.type.TypeAliasesRegister;

import java.util.Properties;

/**
 * @Author: liushoulong
 * @Date: 2019/11/15 10:34
 */
public class Configuration {

    // 实例化是就进行对象初始化,properties是一个hashtable集合
    protected Properties properties = new Properties();

    /***settings标签下对应到的属性名**/
    protected boolean safeRowBoundsEnabled;
    protected boolean safeResultHandlerEnabled = true;
    protected boolean mapUnderscoreToCamelCase;
    protected boolean aggressiveLazyLoading;
    protected boolean multipleResultSetsEnabled = true;
    protected boolean useGeneratedKeys;
    protected boolean useColumnLabel = true;
    protected boolean cacheEnabled = true;
    protected boolean callSettersOnNulls;
    protected boolean useActualParamName = true;
    protected boolean returnInstanceForEmptyRow;

    /**
     * 别名注册器，即定义的实体别名
     * <typeAliases>
     *     <typeAlias alias="Author" type="org.apache.ibatis.domain.blog.Author"/>
     * </typeAliases>
     */
    // 需要定义个别名注册器
    protected TypeAliasesRegister typeAliasesRegister = new TypeAliasesRegister();






}
