package com.sxc.dubbo.mysql;

import com.mysql.jdbc.*;
import com.sxc.dubbo.core.ApplicationBeanHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	TraceStatemengInterceptor
 * Create at:   	2018/4/12
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/4/12    	          ZMM           1.0          1.0 Version
 */
public class TraceStatemengInterceptor implements StatementInterceptorV2 {

    private static Logger logger = LoggerFactory.getLogger(TraceStatemengInterceptor.class);

    Tracer tracer = ApplicationBeanHolder.getBean(Tracer.class);

    @Override
    public ResultSetInternalMethods preProcess(String sql, Statement interceptedStatement, Connection connection) throws SQLException {
        if(sql.equalsIgnoreCase("select 1")){
            return null;
        }
        int spaceIndex = sql.indexOf(' ');
        String spanName = (spaceIndex == -1 ? sql : sql.substring(0, spaceIndex));
        Span span = tracer.createSpan(spanName);
        if (interceptedStatement instanceof PreparedStatement) {
            sql = ((PreparedStatement) interceptedStatement).getPreparedSql();
        }
        span.tag("sql.query", sql);
        return null;
    }


    @Override
    public ResultSetInternalMethods postProcess(String sql, Statement statement, ResultSetInternalMethods resultSetInternalMethods, Connection connection, int i, boolean b, boolean b1, SQLException statementException) throws SQLException {
        if(sql.equalsIgnoreCase("select 1")){
            return null;
        }
        Span span = tracer.getCurrentSpan();
        if (statementException != null) {
            span.tag("error", Integer.toString(statementException.getErrorCode()));
        }
        tracer.close(span);
        return null;
    }

    @Override
    public boolean executeTopLevelOnly() {
        return true;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(Connection connection, Properties properties) throws SQLException {

    }
}
