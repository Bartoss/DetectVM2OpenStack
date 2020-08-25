package com.ibm.service;

import com.ibm.model.Property;
import com.ibm.model.Server;

public interface ConnectorJSchService {

    public void connect(String username,String hostname, Server serverId);

    public void add(Server serverId, String value, Integer commandId);

    public String check(int serverId, String value, Integer commandId);

    public String command(String command, Integer key);

    public void update(Server serverId, String value, Integer commandId, Property property);
}
