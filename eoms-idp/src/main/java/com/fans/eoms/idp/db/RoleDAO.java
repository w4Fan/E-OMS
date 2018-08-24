package com.fans.eoms.idp.db;

import com.fans.eoms.idp.api.RoleList;
import com.fans.eoms.idp.api.RoleListMapper;
import com.fans.eoms.idp.core.Permissions;
import com.fans.eoms.idp.core.PermissionsMapper;
import com.fans.eoms.idp.core.Role;
import com.fans.eoms.idp.core.RoleMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class RoleDAO {
    public List<RoleList> list() {
        return findAll();
    }

    public List<Permissions> permissionsByRoleId(String id) {
        return findPermissionsByRoleId(id);
    }

    public Optional<Role> get(String id){
        return findById(id);
    }

//    public Role get(String id) {
//        return findById(id);
//    }

    @Transaction
    public void insert(
            int organizationId,
            String name,
            boolean isAdmin,
            String functionIds,
            int rankId,
            String description,
            List<String> menuIds,
            List<String> operatingIds) {
        String id = UUID.randomUUID().toString();

        insertRole(id, organizationId, name, isAdmin, functionIds, rankId, description);
        insertRolePermission(id, menuIds, operatingIds);
    }


    @SqlQuery("select " +
            "eoms_rank.id rank_id," +
            "eoms_rank.name rank_name," +
            "eoms_role.id," +
            "eoms_role.name," +
            "eoms_role.is_admin," +
            "eoms_role.organization_id," +
            "eoms_role.function_ids," +
            "eoms_role.description " +
            "from eoms_rank " +
            "inner join eoms_role ON eoms_role.rank_id = eoms_rank.id")
    @RegisterMapper(RoleListMapper.class)
    abstract List<RoleList> findAll();

    @SqlQuery("select menu_id, operating_ids from eoms_role_permission where role_id = :id")
    @RegisterMapper(PermissionsMapper.class)
    abstract List<Permissions> findPermissionsByRoleId(@Bind("id") String id);

    @SqlQuery("select " +
            "eoms_role.*, " +
            "eoms_organization.id organization_id," +
            "eoms_organization.name organization_name " +
            "from eoms_role " +
            "inner join eoms_organization on eoms_organization.id = eoms_role.organization_id " +
            "where eoms_role.id = :id")
//    @RegisterMapper(RoleMapper.class)
//    abstract Role findById(@Bind("id") String id);
    @SingleValueResult(RoleMapper.class)
    @RegisterMapper(RoleMapper.class)
    abstract Optional<Role> findById(@Bind("id") String id);


    @SqlUpdate("update eoms_role set " +
            "organization_id = :organization_id," +
            "name = :name," +
            "is_admin = :is_admin," +
            "function_ids = :function_ids," +
            "rank_id = :rank_id," +
            "description = :description," +
            "gmt_modified = LOCALTIMESTAMP")
    abstract void update(
            @Bind("organization_id") int organizationId,
            @Bind("name") String name,
            @Bind("is_admin") boolean isAdmin,
            @Bind("function_ids") String functionIds,
            @Bind("rank_id") String rankId,
            @Bind("description") String description);

    @SqlUpdate("insert into eoms_role (" +
            "id, organization_id, name, is_admin, function_ids, rank_id, description, gmt_create) " +
            "values (" +
            ":id, :organization_id, :name, :is_admin, :function_ids, :rank_id, :description, LOCALTIMESTAMP)")
    abstract void insertRole(
            @Bind("id") String id,
            @Bind("organization_id") int organizationId,
            @Bind("name") String name,
            @Bind("is_admin") boolean isAdmin,
            @Bind("function_ids") String functionIds,
            @Bind("rank_id") int rankId,
            @Bind("description") String description);

    @SqlBatch("insert into eoms_role_permission (" +
            "role_id, menu_id, operating_ids, gmt_create) " +
            "values (" +
            ":role_id, :menu_id, :operating_ids, LOCALTIMESTAMP)")
    abstract void insertRolePermission(
            @Bind("role_id") String roleId,
            @Bind("menu_id") List<String> menuIds,
            @Bind("operating_ids") List<String> operatingIds
    );
}
