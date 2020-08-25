package com.ibm.service.impl;

import com.ibm.model.Server;
import com.ibm.service.CheckServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServerServiceImpl implements CheckServerService{

    @Autowired
    private ConnectorJSchServiceImpl connectorJSchServiceImpl = new ConnectorJSchServiceImpl();

    @Autowired
    private ServerServiceImpl serverServiceImpl = new ServerServiceImpl();

    @Override
    public void checkServer() {

        List<Server> serversDb = serverServiceImpl.getServerList();

        Map<String, Server> hashMapServersDB = new HashMap<String, Server>();

        for(com.ibm.model.Server se: serversDb){
            hashMapServersDB.put(se.getName(),se);
        }

        //Połączenie z serwerem
        hashMapServersDB.forEach((k,v) ->{
            String prompt = v.getAddress();
            if(!(prompt.equals("Not Shared"))) {
                connectorJSchServiceImpl.connect("cloudusr", v.getAddress(), v);
            }
        });
    }
}
