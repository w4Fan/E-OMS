package com.fans.eoms.idp.core;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements ResultSetMapper<Role> {
    public Role map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        return new Role(resultSet);
    }
}
