package com.phoenixx.server.managers.database;

import com.phoenixx.packets.objects.ClientUserObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager
{
    private Database database;

    public DatabaseManager(Database database)
    {
        this.database = database;
    }


    public void createDefaultTables()
    {
        //TODO Add dateJoined
        String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS UserData (uuid varchar(50) NOT NULL, student_number varchar(20), username TEXT, email TEXT, password TEXT, PRIMARY KEY (uuid));";
        String CREATE_USERPROFILE_TABLE = "CREATE TABLE IF NOT EXISTS UserProfile (uuid varchar(45) NOT NULL, status TEXT, mood TEXT, clanTag varchar(4), profileShowcase JSON, PRIMARY KEY (uuid));";
        String CREATE_USERFRIENDS_TABLE = "CREATE TABLE IF NOT EXISTS UserFriends (uuid varchar(45) NOT NULL, friends JSON, pendingFriends JSON, PRIMARY KEY (uuid));";

        this.database.sendPreparedStatement(CREATE_USERS_TABLE, false);
        //database.sendPreparedStatement(CREATE_USERPROFILE_TABLE, false);
        //database.sendPreparedStatement(CREATE_USERFRIENDS_TABLE, false);
    }

    public ClientUserObject getUserData(String username, String password)
    {
        Connection connection = database.getConnection();
        ResultSet results;
        PreparedStatement preparedStatement;

        try {
            if(!connection.isClosed() && connection !=null)
            {
                String selectSQL = "SELECT * from userdata where username = ? and password = ?";

                try {
                    preparedStatement = connection.prepareStatement(selectSQL);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    results = preparedStatement.executeQuery();

                    while(results.next())
                    {
                        String uuid = results.getString(1);
                        String studentNumber = results.getString(2);
                        String usernameFromDb = results.getString(3);
                        String fullNameFromDb = results.getString(6);

                        ClientUserObject clientUserObject = new ClientUserObject(uuid, studentNumber, usernameFromDb, fullNameFromDb);

                        return clientUserObject;
                    }

                } catch (SQLException e) {
                    System.out.println("FAILED TO CHECK IF USER EXISTS");
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Checks if a user exists, also checks the users student number just so there are not more then 2
     * @param username users username given
     * @param password users password
     * @param studentNumber uses student number
     * @return
     */
    public boolean doesUserExist(String username, String password, String studentNumber)
    {
        java.sql.Connection connection = database.getConnection();
        ResultSet results;
        PreparedStatement preparedStatement;

        try {
            if(!connection.isClosed() && connection !=null)
            {
                String selectSQL = "SELECT * from userdata where username = ? and password = ?";

                if(!studentNumber.equalsIgnoreCase("null"))
                {
                    selectSQL += " and student_number = ?";
                }

                try {
                    preparedStatement = connection.prepareStatement(selectSQL);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    if(!studentNumber.equalsIgnoreCase("null"))
                    {
                        preparedStatement.setString(3, studentNumber);
                    }

                    results = preparedStatement.executeQuery();

                    int count=0;

                    while(results.next())
                    {
                        count=count+1;
                    }

                    if(count>=1)
                    {
                        return true;
                    } else {
                        System.out.println("USER DOES NOT EXIST!!!!");
                        return false;
                    }


            } catch (SQLException e)
                {
                    System.out.println("FAILED TO CHECK IF USER EXISTS");
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Creates a new user in the database
     * @param uuid randomly generated UUID
     * @param studentNumber student number given
     * @param username username user had chosen when signing up
     * @param email email given
     * @param password password given
     * @param fullName users full name
     */
    public void createNewUserinDB(String uuid, String studentNumber, String username, String email, String password, String fullName)
    {
        String createNewUserStatement = "INSERT INTO userdata(uuid,student_number,username,email,password,full_name) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(createNewUserStatement);

            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, studentNumber);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, fullName);

            this.database.sendPreparedStatement(preparedStatement, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
