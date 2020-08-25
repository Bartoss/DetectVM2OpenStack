package com.ibm.service;

import com.ibm.model.Server;

import java.util.List;

public interface ServerService {

    List<Server> getServerList();

    void addServer(Server server);

    void deleteServer(Server server);
}
