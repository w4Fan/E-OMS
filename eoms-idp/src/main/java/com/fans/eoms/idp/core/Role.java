package com.fans.eoms.idp.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Role {
    private String id;
    private int organizationId;
    private String organizationName;
    private String name;
    private Boolean isAdmin;
    private int rankId;
    private String functionIds;
    private String description;

    public Role() {
    }

    Role(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("id");
        this.organizationId = resultSet.getInt("organization_id");
        this.organizationName = resultSet.getString("organization_name");
        this.name = resultSet.getString("name");
        this.isAdmin = resultSet.getBoolean("is_admin");
        this.rankId = resultSet.getInt("rank_id");
        this.functionIds = resultSet.getString("function_ids");
        this.description = resultSet.getString("description");
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty
    public int getOrganizationId() {
        return organizationId;
    }

    @JsonProperty
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @JsonProperty
    public String getOrganizationName() {
        return organizationName;
    }

    @JsonProperty
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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
    public Boolean getAdmin() {
        return isAdmin;
    }

    @JsonProperty
    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @JsonProperty
    public int getRankId() {
        return rankId;
    }

    @JsonProperty
    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public String getFunctionIds() {
        return functionIds;
    }

    @JsonProperty
    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", organizationId=" + organizationId +
                ", organizationName='" + organizationName + '\'' +
                ", name='" + name + '\'' +
                ", isAdmin=" + isAdmin +
                ", rankId=" + rankId +
                ", functionIds='" + functionIds + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
