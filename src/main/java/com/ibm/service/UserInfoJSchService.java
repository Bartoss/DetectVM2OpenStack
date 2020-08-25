package com.ibm.service;

public interface UserInfoJSchService {

    public String getPassword();
    public boolean promptYesNo(String str);
    public String getPassphrase();
    public boolean promptPassphrase(String message);
    public boolean promptPassword(String message);
    public void showMessage(String message);
    public String[] promptKeyboardInteractive(String arg0, String arg1, String arg2, String[] arg3, boolean[] arg4);

}
