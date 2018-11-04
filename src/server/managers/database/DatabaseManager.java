package server.managers.database;

import server.ServerNetworkMain;

public class DatabaseManager
{
    private final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS UserData (uuid varchar(50) NOT NULL, student_number varchar(20), username TEXT, email TEXT, password TEXT);";
    private final String CREATE_USERPROFILE_TABLE = "CREATE TABLE IF NOT EXISTS UserProfile (uuid varchar(45) NOT NULL, status TEXT, mood TEXT, clanTag varchar(4), profileShowcase JSON, PRIMARY KEY (uuid));";
    private final String CREATE_USERFRIENDS_TABLE = "CREATE TABLE IF NOT EXISTS UserFriends (uuid varchar(45) NOT NULL, friends JSON, pendingFriends JSON, PRIMARY KEY (uuid));";

    public void createDefaultTables() {

        Database database = ServerNetworkMain.getDatabase();

        database.sendPreparedStatement(CREATE_USERS_TABLE, false);
        //database.sendPreparedStatement(CREATE_USERPROFILE_TABLE, false);
        //database.sendPreparedStatement(CREATE_USERFRIENDS_TABLE, false);
    }
}
