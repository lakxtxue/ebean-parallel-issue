package com.ebean.test;


import io.ebean.annotation.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "Device")
@Table(name = "DEVICE")
@Cache
public class Device implements Serializable {

    @Id
    @Column(name="ID",nullable=false,length=32)
    private String id;

    @Column(name="ENABLED",nullable=false,length=1)
    private String enabled;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
