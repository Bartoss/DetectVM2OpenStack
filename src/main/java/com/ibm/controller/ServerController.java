package com.ibm.controller;

import com.ibm.model.Property;
import com.ibm.model.Server;
import com.ibm.service.impl.CheckOpenStackServiceImpl;
import com.ibm.service.impl.CheckServerServiceImpl;
import com.ibm.service.impl.PropertyServiceImpl;
import com.ibm.service.impl.ServerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ServerController {

    @Autowired
    private CheckOpenStackServiceImpl checkOpenStackServiceImpl = new CheckOpenStackServiceImpl();

    @Autowired
    private CheckServerServiceImpl checkServerServiceImpl = new CheckServerServiceImpl();

    @Autowired
    private ServerServiceImpl serverServiceImpl = new ServerServiceImpl();

    @Autowired
    private PropertyServiceImpl propertyServiceImpl = new PropertyServiceImpl();

    @RequestMapping("/serverList")
    public String serverList(Model model){

        //do wyswietlania serwerow
        List<Server> serversss = serverServiceImpl.getServerList();
        model.addAttribute("servers", serversss);

        //do wyswietlania wlasciwosci serwerow
        List<Property> properties = propertyServiceImpl.getPropertyList();
        model.addAttribute("properties",properties);

        return "serverList";
    }

    @RequestMapping("/serverListRefresh")
    public String serverListRefresh(Model model){

        //Sprawdzenie aktualnej listy serwerów
        checkOpenStackServiceImpl.checkServer();
        List<Server> serversss = serverServiceImpl.getServerList();
        model.addAttribute("servers", serversss);

        //Do połaczenia z serwerami Linuksowymi
        checkServerServiceImpl.checkServer();

        //do wyswietlania wlasciwosci serwerow
        List<Property> properties = propertyServiceImpl.getPropertyList();
        model.addAttribute("properties",properties);

        return "serverList";
    }

}