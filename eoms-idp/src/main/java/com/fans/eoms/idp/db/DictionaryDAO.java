package com.fans.eoms.idp.db;

import com.fans.eoms.idp.api.FunctionDictionary;
import com.fans.eoms.idp.api.FunctionDictionaryMapper;
import com.fans.eoms.idp.api.RankDictionary;
import com.fans.eoms.idp.api.RankDictionaryMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

import java.util.List;

public interface DictionaryDAO {
    @SqlQuery("select * from eoms_rank")
    @RegisterMapper(RankDictionaryMapper.class)
    List<RankDictionary> rankDictionary();

    @SqlQuery("select * from eoms_function")
    @RegisterMapper(FunctionDictionaryMapper.class)
    List<FunctionDictionary> functionDictionary();
}
