package com.fans.eoms.idp.core;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu {
    private String id;
    private boolean isRoot;
    private String rootId;
    private String parentId;
    private String name;
    private boolean isPage;
    private String operatingIds;

    public Menu() {
    }

    public Menu(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("id");
        this.isRoot = resultSet.getBoolean("is_root");
        this.rootId = resultSet.getString("root_id");
        this.parentId = resultSet.getString("parent_id");
        this.name = resultSet.getString("name");
        this.isPage = resultSet.getBoolean("is_page");
        this.operatingIds = resultSet.getString("operating_ids");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPage() {
        return isPage;
    }

    public void setPage(boolean page) {
        isPage = page;
    }

    public String getOperatingIds() {
        return operatingIds;
    }

    public void setOperatingIds(String operatingIds) {
        this.operatingIds = operatingIds;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", isRoot=" + isRoot +
                ", rootId='" + rootId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", isPage=" + isPage +
                ", operatingIds='" + operatingIds + '\'' +
                '}';
    }
}
