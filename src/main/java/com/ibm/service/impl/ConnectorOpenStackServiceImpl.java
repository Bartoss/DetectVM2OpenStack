package com.ibm.service.impl;


import com.ibm.service.ConnectorOpenStackService;
import org.openstack4j.api.OSClient;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.UntrustedSSL;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectorOpenStackServiceImpl implements ConnectorOpenStackService {

    private Config config = Config.DEFAULT.withSSLContext(UntrustedSSL.getSSLContext());

    private String domainName = "";

    private String projectId = "";

    private OSClient.OSClientV3 os;

    public void connect() {

        os = OSFactory.builderV3()
                .endpoint("")
                .credentials("", "", Identifier.byName(domainName))
                .scopeToProject(Identifier.byId(projectId))
                .withConfig(config)
                .authenticate();

    }

    public List<? extends Image> imagesList() {

        List<? extends Image> images = os.compute().images().list();

        return images;
    }

    public List<? extends Server> serversList(){

        List<? extends Server> servers = os.compute().servers().list();

        return servers;
    }

}
