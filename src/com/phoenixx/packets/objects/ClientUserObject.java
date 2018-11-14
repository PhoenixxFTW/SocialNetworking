package com.phoenixx.packets.objects;

public class ClientUserObject
{
    private String uuid;
    private String username;
    private String fullName;
    private String studentNumber;

    public ClientUserObject()
    {

    }

    public ClientUserObject(String uuid, String studentNumber, String username, String fullName)
    {
        this.uuid = uuid;
        this.username = username;
        this.fullName = fullName;
        this.studentNumber = studentNumber;
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

}
