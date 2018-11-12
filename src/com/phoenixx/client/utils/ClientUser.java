package com.phoenixx.client.utils;

public class ClientUser
{
    private String uuid;
    private String username;
    private String fullName;
    private String studentNumber;

    private ClientUser instance;

    public ClientUser()
    {
        instance = this;
    }

    public void setUuid(String uuid) {
        uuid = uuid;
    }

    public void setUsername(String username) {
        username = username;
    }

    public void setFullName(String fullName) {
        fullName = fullName;
    }

    public void setStudentNumber(String studentNumber) {
        studentNumber = studentNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public ClientUser getInstance() {
        return instance;
    }
}
