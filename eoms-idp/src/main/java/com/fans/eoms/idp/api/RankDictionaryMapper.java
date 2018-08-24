package com.fans.eoms.idp.api;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RankDictionaryMapper implements ResultSetMapper<RankDictionary> {
    public RankDictionary map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        return new RankDictionary(resultSet);
    }
}
