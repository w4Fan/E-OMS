package com.fans.eoms.idp.core;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionsMapper implements ResultSetMapper<Permissions> {
    public Permissions map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        return new Permissions(resultSet.getString("menu_id"), resultSet.getString("operating_ids"));
    }
}
