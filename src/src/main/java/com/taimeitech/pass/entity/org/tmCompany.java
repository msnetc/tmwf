package com.taimeitech.pass.entity.org;

import java.util.List;

/**
 * Created by yanjie.miao on 2017/9/13.
 */
public class tmCompany {
    private String id;
    private String name;
    private List<tmOrganization> organizations;


    public List<tmOrganization> getOrganizations() {
        return organizations;
    }
    public void setOrganizations(List<tmOrganization> organizations) {
        this.organizations = organizations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
