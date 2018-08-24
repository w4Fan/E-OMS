package com.fans.eoms.hub.api;

import com.fans.eoms.hub.core.Organization;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrganizationDictionary {
    private int id;
    private boolean is_root;
    private int root_id;
    private int parent_id;
    private String name;

    public OrganizationDictionary() {
    }

    public OrganizationDictionary(Organization organization) {
        this.id = organization.getId();
        this.is_root = organization.isIs_root();
        this.root_id = organization.getRoot_id();
        this.parent_id = organization.getParent_id();
        this.name = organization.getName();
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
    public boolean isIs_root() {
        return is_root;
    }

    @JsonProperty
    public void setIs_root(boolean is_root) {
        this.is_root = is_root;
    }

    @JsonProperty
    public int getRoot_id() {
        return root_id;
    }

    @JsonProperty
    public void setRoot_id(int root_id) {
        this.root_id = root_id;
    }

    @JsonProperty
    public int getParent_id() {
        return parent_id;
    }

    @JsonProperty
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrganizationDictionary{" +
                "id='" + id + '\'' +
                ", is_root=" + is_root +
                ", root_id='" + root_id + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
