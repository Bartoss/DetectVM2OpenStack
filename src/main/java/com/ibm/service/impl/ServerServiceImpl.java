package com.ibm.service.impl;

import com.ibm.dao.ServerDao;
import com.ibm.model.Server;
import com.ibm.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerServiceImpl implements ServerService{

    @Autowired
    ServerDao serverDao;

    public List<Server> getServerList(){return serverDao.getServerList();}

    public void addServer(Server server) {
        serverDao.addServer(server);
    }

    public void deleteServer(Server server) {
        serverDao.deleteServer(server);
    }


}
