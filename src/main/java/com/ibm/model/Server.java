package com.ibm.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Server implements Serializable{

    private static final long serialVersionUID = -3532377236419382983L;

    //Id used in database
    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  id;

    //Id from OpenStack
    private String serverId;

    private String name;

    private String active;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
