package com.phoenixx.server.managers.database;

import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;

import java.sql.*;
import java.util.ArrayList;

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
        String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS UserData (uuid varchar(50) NOT NULL, student_number varchar(20), username TEXT, email TEXT, password TEXT, full_name TEXT, PRIMARY KEY (uuid));";
        String CREATE_POSTS_TABLE = "CREATE TABLE IF NOT EXISTS PostData (post_id int NOT NULL PRIMARY KEY AUTO_INCREMENT, owner_uuid varchar(50), date_created TIMESTAMP, tags TEXT, post_title TEXT, post_text MEDIUMTEXT);";
        String CREATE_USERFRIENDS_TABLE = "CREATE TABLE IF NOT EXISTS UserFriends (uuid varchar(45) NOT NULL, friends JSON, pendingFriends JSON, PRIMARY KEY (uuid));";

        this.database.sendPreparedStatement(CREATE_USERS_TABLE, false);
        this.database.sendPreparedStatement(CREATE_POSTS_TABLE, false);
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

                        return new ClientUserObject(uuid, studentNumber, usernameFromDb, fullNameFromDb);
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
        Connection connection = database.getConnection();
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
    public void createNewUserInDB(String uuid, String studentNumber, String username, String email, String password, String fullName)
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

    public ArrayList getLatestPostData()
    {
        int lastAmountOfData = 10;
        ArrayList<PostDataObject> latestPosts = new ArrayList();
        Connection connection = database.getConnection();
        ResultSet results;
        PreparedStatement preparedStatement;

        try {
            if(!connection.isClosed() && connection !=null)
            {
                String selectSQL = "SELECT * from postdata order by post_id desc limit " + lastAmountOfData;

                try {
                    preparedStatement = connection.prepareStatement(selectSQL);

                    results = preparedStatement.executeQuery();

                    while(results.next())
                    {
                        int postID = results.getInt(1);
                        String ownerUUID = results.getString(2);
                        String ownerName = results.getString(3);
                        Timestamp dateCreated = results.getTimestamp(4);
                        String tags = results.getString(5);
                        String postTitle = results.getString(6);
                        //String postText = results.getString(6);
                        String postText = "This is the fake text";

                        System.out.println("===============================");
                        System.out.println("Post ID: " + postID);
                        System.out.println("Owner UUID: " + ownerUUID);
                        System.out.println("Owner Name: " + ownerName);
                        System.out.println("Date Created: " + dateCreated);
                        System.out.println("Tags: " + tags);
                        System.out.println("Post Title: " + postTitle);
                        System.out.println("Post text: " + postText);

                        PostDataObject postDataObject = new PostDataObject(postID, ownerUUID, ownerName, dateCreated.toLocalDateTime().toString(), tags, postTitle, postText);
                        latestPosts.add(postDataObject);
                    }

                    return latestPosts;
                } catch (SQLException e) {
                    System.out.println("FAILED TO GET POST DATA");
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PostDataObject getPostDataFromID(int postID)
    {
        Connection connection = database.getConnection();
        ResultSet results;
        PreparedStatement preparedStatement;

        try {
            if(!connection.isClosed() && connection !=null)
            {
                String selectSQL = "SELECT * from postdata where post_id = ?";

                try {
                    preparedStatement = connection.prepareStatement(selectSQL);
                    preparedStatement.setInt(1, postID);

                    results = preparedStatement.executeQuery();

                    while(results.next())
                    {
                        String ownerUUID = results.getString(2);
                        String ownerName = results.getString(3);
                        Timestamp dateCreated = results.getTimestamp(4);
                        String tags = results.getString(5);
                        String postTitle = results.getString(6);
                        String postText = results.getString(7);

                        return new PostDataObject(postID, ownerUUID, ownerName, dateCreated.toLocalDateTime().toString(), tags, postTitle, postText);
                    }

                } catch (SQLException e) {
                    System.out.println("FAILED TO GET POST DATA");
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new "forum post" to be displayed on the HomeScreen
     * @param ownerUUID UUID of the user who made the post
     * @param ownerName Name of the user who made the post
     * @param tags Tags the user set to the post, so we can filter by them later on
     * @param postTitle Name of the post
     * @param postText Actual text from the post
     */
    public void createNewPost(String ownerUUID, String ownerName, String tags, String postTitle, String postText)
    {
        String createNewUserStatement = "INSERT INTO postdata(owner_uuid,owner_name,date_created,tags,post_title,post_text) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(createNewUserStatement);

            preparedStatement.setString(1, ownerUUID);
            preparedStatement.setString(2, ownerName);
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(4, tags);
            preparedStatement.setString(5, postTitle);
            preparedStatement.setString(6, postText);

            this.database.sendPreparedStatement(preparedStatement, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
