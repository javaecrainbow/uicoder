package com.salk.uicoder.persist.executor;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author salkli
 * @since 2022/10/30
 **/
public class UiCoderDatapoolResultSetHandler implements ResultSetHandler {
    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        return null;
    }

    @Override
    public <E> Cursor<E> handleCursorResultSets(Statement stmt) throws SQLException {
        return null;
    }

    @Override
    public void handleOutputParameters(CallableStatement cs) throws SQLException {

    }
}
