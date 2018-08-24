package com.fans.eoms.hub.db;

import com.fans.eoms.hub.core.Organization;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

import java.util.List;
import java.util.Optional;

public interface OrganizationDAO {
    @SqlQuery("select * from eoms_organization order by id")
    @MapResultAsBean
    List<Organization> list();

    @SqlQuery("select count(id) from eoms_organization where parent_id = :id")
    int findChilrenCount(@Bind("id") int id);

    @SqlQuery("select * from eoms_organization where id = :id")
    @SingleValueResult(Organization.class)
    @MapResultAsBean
    Optional<Organization> get(@Bind("id") int id);

    @SqlUpdate("insert into eoms_organization (" +
            "root_id, parent_id, name, province, city, district, address, owner, phone, gmt_create) " +
            "values (" +
            ":root_id, :parent_id, :name, :province, :city, :district, :address, :owner, :phone, LOCALTIMESTAMP)")
    void insert(
            @Bind("root_id") int root_id,
            @Bind("parent_id") int parent_id,
            @Bind("name") String name,
            @Bind("province") String province,
            @Bind("city") String city,
            @Bind("district") String district,
            @Bind("address") String address,
            @Bind("owner") String owner,
            @Bind("phone") String phone);

    @SqlUpdate("update eoms_organization set " +
            "name = :name," +
            "province = :province," +
            "city = :city," +
            "district = :district," +
            "address = :address," +
            "owner = :owner," +
            "phone = :phone," +
            "gmt_modified = LOCALTIMESTAMP where id = :id")
    void update(
            @Bind("id") int id,
            @Bind("name") String name,
            @Bind("province") String province,
            @Bind("city") String city,
            @Bind("district") String district,
            @Bind("address") String address,
            @Bind("owner") String owner,
            @Bind("phone") String phone);

    @SqlUpdate("delete from eoms_organization where id = :id")
    void delete(@Bind("id") int id);
}
