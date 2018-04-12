package com.sxc.dubbo.mysql;

import com.mysql.jdbc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class TraceStatemengInterceptor implements StatementInterceptorV2{

    private static Logger logger = LoggerFactory.getLogger(TraceStatemengInterceptor.class);

    @Override
    public ResultSetInternalMethods preProcess(String sql, Statement interceptedStatement, Connection connection) throws SQLException {
        if (interceptedStatement instanceof PreparedStatement) {
            sql = ((PreparedStatement) interceptedStatement).getPreparedSql();
        }
        logger.info(sql);
        return null;
    }



    @Override
    public ResultSetInternalMethods postProcess(String sql, Statement statement, ResultSetInternalMethods resultSetInternalMethods, Connection connection, int i, boolean b, boolean b1, SQLException e) throws SQLException {
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
