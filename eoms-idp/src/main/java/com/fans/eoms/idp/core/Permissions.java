package com.fans.eoms.idp.core;

public class Permissions {
    private String menuId;
    private String operatingIds;

    public Permissions() {
    }

    public Permissions(String menuId, String operatingIds) {
        this.menuId = menuId;
        this.operatingIds = operatingIds;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getOperatingIds() {
        return operatingIds;
    }

    public void setOperatingIds(String operatingIds) {
        this.operatingIds = operatingIds;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "menuId=" + menuId +
                ", operatingIds='" + operatingIds + '\'' +
                '}';
    }
}
