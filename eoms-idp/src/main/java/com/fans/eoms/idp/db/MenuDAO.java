package com.fans.eoms.idp.db;

import com.fans.eoms.idp.core.Menu;
import com.fans.eoms.idp.core.MenuMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

public interface MenuDAO {
    @SqlQuery("select * from eoms_menu where id = :id or root_id = :id")
    @RegisterMapper(MenuMapper.class)
    List<Menu> findMenuByRootId(@Bind("id") String id);
}
