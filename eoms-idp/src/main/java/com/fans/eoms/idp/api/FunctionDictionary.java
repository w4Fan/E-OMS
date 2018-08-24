package com.fans.eoms.idp.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FunctionDictionary {
    private int id;
    private String name;
    private String menuRootIds;
    private Boolean isAdmin;

    public FunctionDictionary() {
    }

    FunctionDictionary(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.menuRootIds = resultSet.getString("menu_root_ids");
        this.isAdmin = resultSet.getBoolean("is_admin");
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getMenuRootIds() {
        return menuRootIds;
    }

    @JsonProperty
    public void setMenuRootIds(String menuRootIds) {
        this.menuRootIds = menuRootIds;
    }

    @JsonProperty
    public Boolean getAdmin() {
        return isAdmin;
    }

    @JsonProperty
    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "FunctionDictionary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menuRootIds='" + menuRootIds + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
