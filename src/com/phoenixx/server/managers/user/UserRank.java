package com.phoenixx.server.managers.user;

public class UserRank
{
    public static UserRank[] ranksList = new UserRank[8];

    public static UserRank STUDENT = new UserRank(0, "Student");
    public static UserRank TEACHER = new UserRank(1, "Teacher");
    public static UserRank ADMIN = new UserRank(2, "Administrator");
    public static UserRank DEVELOPER = new UserRank(3, "Developer");
    public static UserRank OWNER = new UserRank(4, "Owner");

    private int id;
    private String title;

    public UserRank(int par1, String par2) {
        ranksList[par1] = this;
        this.id = par1;
        this.title = par2;
    }

    public static UserRank getNextRank(int par1)
    {
        return UserRank.ranksList[par1 + 1];
    }

    public int getID() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserRank) {
            return ((UserRank) obj).id == this.id;
        }

        return super.equals(obj);
    }
}
