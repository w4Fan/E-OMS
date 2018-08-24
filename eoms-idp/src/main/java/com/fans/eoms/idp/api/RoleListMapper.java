package com.fans.eoms.idp.api;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleListMapper implements ResultSetMapper<RoleList> {
    public RoleList map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        return new RoleList(resultSet);
    }
}
