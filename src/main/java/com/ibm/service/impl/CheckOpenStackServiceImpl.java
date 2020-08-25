package com.ibm.service.impl;

import com.ibm.model.Property;
import com.ibm.service.CheckOpenStackService;
import org.openstack4j.model.compute.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CheckOpenStackServiceImpl implements CheckOpenStackService {

    @Autowired
    private ConnectorOpenStackServiceImpl connectorServiceImpl = new ConnectorOpenStackServiceImpl();

    @Autowired
    private ServerServiceImpl serverServiceImpl = new ServerServiceImpl();

    @Autowired
    private PropertyServiceImpl propertyServiceImpl = new PropertyServiceImpl();

    public void checkServer(){

        //Połączenie z serwerem OpenStack
        connectorServiceImpl.connect();

        // Zapis do listy w celu operacji na danych Listy serwerów z OpenStack
        List<? extends Server> serversOpenStack = connectorServiceImpl.serversList();

        //Zapis do listy w celu przeprowadzenia operacji listy serwerów z bazy danych
        List<com.ibm.model.Server> serversDB = serverServiceImpl.getServerList();

        //Zapis do listy w celu przeprowadzenia operacji listy właściwości z serwerów
        List<Property> propertyDB = propertyServiceImpl.getPropertyList();

        //Utworzenie opiektu hashMap typu serwer openStack oraz string
        HashMap<String, Server> hasMapServersOpenStack = new HashMap<String, Server>();

        //Utworzenie obiektu hashMap typu serwer z bazy danych oraz string
        Map<String, com.ibm.model.Server> hashMapServersDB = new HashMap<String, com.ibm.model.Server>();

        //Utworzenie obiektu hashMap typu Property z bazy danych oraz Server
        Map<String, Property> hashMapPropertyDB = new HashMap<>();

        //Zapis do hashMap danych z listy
        for(Server se: serversOpenStack){
            hasMapServersOpenStack.put(se.getId(),se);
        }

        for(com.ibm.model.Server se: serversDB){
            hashMapServersDB.put(se.getServerId(),se);
        }

        for(Property property: propertyDB){
            hashMapPropertyDB.put(property.getServer().getServerId(),property);
            System.out.println(property);
        }

        //dodanie serwera do bazy danych jeżeli istnieje w OpenStack
        hasMapServersOpenStack.forEach((k,v) ->{
            if(hashMapServersDB.get(k) == null){

                com.ibm.model.Server server = new com.ibm.model.Server();

                server.setName(v.getName());

                server.setActive(v.getVmState());

                //wyciągnięcie Stringa
                String address = String.valueOf(v.getAddresses());

                //wykorzytsanie RegEx'a do wpisania adresu ip
                server.setAddress(findPattern(address));

                server.setServerId(v.getId());

                serverServiceImpl.addServer(server);
            }
        });

        //usunięcie serwera z bazy danych jezeli nie ma go na OpenStack - chyba działa ????
        hashMapServersDB.forEach((k,v) ->{
            if(hasMapServersOpenStack.get(k) == null){
                System.out.println("This server in not exist : " + k);
                hashMapPropertyDB.forEach((kp,vp) ->{
                    if(v.getId() == vp.getServer().getId()){
                        //propertyServiceImpl.deleteProperty(vp);
                        //serverServiceImpl.deleteServer(v);
                    }
                });
            }
        });
    }

    //metoda odpowiedzialna za wyszukiwanie floating ip z OpenStack
    public String findPattern(String value) {
        Pattern p = Pattern.compile("9[.1234567890]{8,11}");
        Matcher m = p.matcher(value);
        boolean b = m.find();
        if(b){
            return m.group(0);
        }else{
            return "Not Shared";
        }
    }
}