package com.ibm.service;

import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.Server;

import java.util.List;

public interface ConnectorOpenStackService {

    public void connect();

    public List<? extends Image> imagesList();

    public List<? extends Server> serversList();
}
