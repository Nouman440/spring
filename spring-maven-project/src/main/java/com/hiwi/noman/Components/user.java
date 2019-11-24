package com.hiwi.noman.Components;

/**
 * Created by nomi on 12.12.18.
 */
public class user {

    public String getNormaluser() {
        return normaluser;
    }

    public void setNormaluser(String normaluser) {
        this.normaluser = normaluser;
    }


    String normaluser;

    public String getUnkownuser() {
        return unkownuser;
    }

    public void setUnkownuser(String unkownuser) {
        this.unkownuser = unkownuser;
    }

    String unkownuser;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;
    @Override
    public String toString() {
        return "userinfo{" +
                "username=" + username +
                ", password='" + password + '\'' +

                '}';
    }
}

