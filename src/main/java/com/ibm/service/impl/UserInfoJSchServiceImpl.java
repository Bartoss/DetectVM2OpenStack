package com.ibm.service.impl;

import com.ibm.service.UserInfoJSchService;
import com.jcraft.jsch.UserInfo;

public class UserInfoJSchServiceImpl implements UserInfoJSchService, UserInfo {

    public String getPassword(){ return null; }
    public boolean promptYesNo(String str){
        int foo=0;
        return foo==0;
    }

    String passphrase;

    public String getPassphrase(){ return passphrase; }
    public boolean promptPassphrase(String message){
        int result= 1;
        if(result==1){
            return true;
        } else{ return false; }
    }
    public boolean promptPassword(String message){ return true; }
    public void showMessage(String message){
        System.out.println(message);
    }
    public String[] promptKeyboardInteractive(String destination,
                                              String name,
                                              String instruction,
                                              String[] prompt,
                                              boolean[] echo){
        String[] response = {"y","e","s"};
        return response;
    }
}
