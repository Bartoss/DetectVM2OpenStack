package com.ibm.dao;


import com.ibm.model.Server;

import java.util.List;

public interface ServerDao {

    List<Server> getServerList();

    void addServer (Server server);

    void deleteServer(Server server);
}
