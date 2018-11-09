package com.phoenixx.packets.objects;

public class SignUpObject
{
    private int connectionID;
    private String uuid;
    private String studentNumber;
    private String fullName;
    private String email;
    private String username;
    private String password;

    public SignUpObject()
    {

    }

    public SignUpObject(int connectionID, String uuid, String studentNumber, String fullName, String email, String username, String password)
    {
        this.uuid = uuid;
        this.connectionID = connectionID;
        this.studentNumber = studentNumber;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public int getConnectionID()
    {
        return connectionID;
    }

    public String getUuid() {
        return uuid;
    }

    public String getStudentNumber() {
        return this.studentNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setConnectionID(int connectionID) {
        this.connectionID = connectionID;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
