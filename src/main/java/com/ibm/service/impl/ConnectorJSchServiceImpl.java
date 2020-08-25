package com.ibm.service.impl;

import com.ibm.model.Property;
import com.ibm.model.Server;
import com.ibm.service.ConnectorJSchService;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

@Service
public class ConnectorJSchServiceImpl implements ConnectorJSchService {

    @Autowired
    private PropertyServiceImpl propertyService = new PropertyServiceImpl();

    HashMap<Integer, Property> hashMapProperty = new HashMap<>();

    HashMap<Integer, String> commands = new HashMap<Integer,String>();

    ChannelExec channel = null;

    Session session = null;

    @Override
    public void connect(String username, String hostname, Server serverId) {
        // Command HashMap
        commands.put(1, "whoami");
        commands.put(2, "sudo su - db2inst1 -c db2level | sed -n '/tokens/ s/^.*\\\"D[bB]2 v\\([^\"]*\\)\\\".*$/\\1/p'");
        commands.put(3, "sudo head /opt/IBM/InformationServer/Version.xml | sed -n '/InstallType/ s/^.*currentVersion=\\\"\\([^\"]*\\)\\\".*$/\\1/p'");
        commands.put(4, "uname -sr ");
        commands.put(5, "sudo /opt/mqm/bin/dspmqver | sed -n 's/Version:\\s*\\(.*\\)$/\\1/p'");
        commands.put(6, "sudo /usr/local/nz/bin/nzsql -V | sed -n 's/^.*Release\\s\\(.*\\)$/\\1/p'");
        commands.put(7, "OSNAME=`cat /etc/os-release | sed -n '/^NAME=/ s/^.*\\\"\\(.*\\)\\\"$/\\1/p'`;OSVER=`cat /etc/os-release | sed -n '/^VERSION=/ s/^.*\\\"\\(.*\\)\\\"$/\\1/p'`; echo $OSNAME $OSVER");

        //Lista właściwości z bazy
        List<Property> propertyList = propertyService.getPropertyList();

        //Pobranie wartosci z bazy danych
        for(Property prop: propertyList){
            hashMapProperty.put(prop.getServer().getId(), prop);
        }
        java.util.Properties config = new java.util.Properties();

        config.put("StrictHostKeyChecking", "no");

        JSch jsch=new JSch();

        try{
            jsch.addIdentity("/home/bartlomiej/Desktop/certyfikaty/id_rsa_common/");

            session = jsch.getSession(username, hostname, 22);
            session.setConfig(config);

            // username and passphrase will be given via UserInfo interface.
            UserInfo ui = new UserInfoJSchServiceImpl();
            session.setUserInfo(ui);
            session.connect();

            // Komendy
            commands.forEach((k,v) ->{
                String finalText =  command(v, k);

                //Sprawdzanie tekstu w bazie
                boolean value = hashMapProperty.containsKey(serverId.getId());

                //Sprawdzanie czy istnieje takie property z klucza, jezeli nie istnieje to dodaje do bazy danych
                if(!value) add(serverId,finalText,k);

                //Sprawdzenie czy istnieje takie property po właściwości
                System.out.println("Final Text : " + finalText);

                //Pobranie wartości Property z hashMapy
                final Property[] propertyToChangeValue = {null};
                hashMapProperty.forEach((ka,va) ->{
                    if(va.getServer().getId() == serverId.getId()){
                        //System.out.println("Property Server id : " + va.getServer().getId() + " Value : " + serverId.getId());
                        if(k == va.getCommandId()){
                            //System.out.println("Command number from HashMap : " + k + " Command number from Property  : " + va.getCommandId() );
                            propertyToChangeValue[0] = va;
                        }
                    }
                });

                // Sprawdzanie czy zmieniła się wartość w danej komendzie np. zmniejszyła sie ilosć wolnego miejsca na dysku
                if(check(serverId.getId(),finalText,k) != null){
                    update(serverId,finalText,k, propertyToChangeValue[0]);
                }
            });
        }catch (JSchException e) {
            e.printStackTrace(System.out);
            //System.out.println("JSchEXCEPTION Hostname : " + hostname + " ServerId : " + serverId.getName());
        } catch (Exception e){
            e.printStackTrace(System.out);
            //System.out.println("EXCEPTION Hostname : " + hostname + " ServerId : " + serverId.getName());
        } finally {
            session.disconnect();
        }
    }

    public void add(Server serverId, String value, Integer commandId){
        Property property = new Property();
        property.setServer(serverId);
        property.setValue(value);
        property.setCommandId(commandId);
        propertyService.addProperty(property);
    }

    public String check(int serverId, String value, Integer commandId) {
        String[] finalText = {null};
        //sprawdzenie wartości z bazy danych jeżeli jest to nie dodaje a jeżeli nie jest do dodaje
        hashMapProperty.forEach((k,v)->{
                if(v.getServer().getId() == serverId) {
                    //System.out.println("In check method : " + v.getServer().getId() + " Server Id " + serverId);
                    Integer value2 = v.getCommandId();
                    if(value2.equals(commandId)){
                        //System.out.println("Command Id  from hashMapProperty: " + v.getCommandId() + " server commandId : " + commandId);
                        if(!(v.getValue().equals(value))){
                            //System.out.println("Value from hashMapProperty : " + v.getValue() + " server value : " + value);
                            finalText[0] = value;
                        }
                    }
                }
        });
        return finalText[0];
    }

    @Override
    public String command(String command, Integer key) {
        String text = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setInputStream(null);
            channel.setErrStream(System.err);
            channel.setCommand(command);
            channel.connect();
            InputStream commandOutput = channel.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(commandOutput));
            String line = null;
            while ((line = br.readLine()) != null){
                text = line;
            }
            channel.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public void update(Server serverId, String value, Integer commandId, Property property) {
        property.setServer(serverId);
        property.setValue(value);
        property.setCommandId(commandId);
        propertyService.update(property);
    }

}