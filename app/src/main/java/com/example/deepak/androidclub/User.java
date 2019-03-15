package com.example.deepak.androidclub;

public class User {

    String name,email,mobnum,regnum,authid;

    public User() {
    }

    public User(String name, String email, String mobnum, String regnum,String authid) {
        this.name = name;
        this.email = email;
        this.mobnum = mobnum;
        this.regnum = regnum;
        this.authid = authid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobnum() {
        return mobnum;
    }

    public void setMobnum(String mobnum) {
        this.mobnum = mobnum;
    }

    public String getRegnum() {
        return regnum;
    }

    public void setRegnum(String regnum) {
        this.regnum = regnum;
    }

    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }
}
