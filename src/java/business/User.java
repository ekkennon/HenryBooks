/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author raefo
 */
public class User {
    private int userid;
    private int storeid;
    private int password;
    private int pwAttempt;
    private String username;
    private String adminLevel;
    
    public User() {
        setUserid(0);
        setStoreid(0);
        setPassword(0);
        setPwAttempt(-1);
        setUsername("");
        setAdminLevel("");
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the storeid
     */
    public int getStoreid() {
        return storeid;
    }

    /**
     * @param storeid the storeid to set
     */
    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    /**
     * @return the password
     */
    public int getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(int password) {
        this.password = password;
    }

    /**
     * @return the pwAttempt
     */
    public int getPwAttempt() {
        return pwAttempt;
    }

    /**
     * @param pwAttempt the pwAttempt to set
     */
    public void setPwAttempt(int pwAttempt) {
        this.pwAttempt = pwAttempt;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the adminLevel
     */
    public String getAdminLevel() {
        return adminLevel;
    }

    /**
     * @param adminLevel the adminLevel to set
     */
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
    
    public boolean isAuthenticated() {
        if (this.password > 0) {
            return this.password == this.pwAttempt;
        }
        return false;
        //return true;
    }
}
