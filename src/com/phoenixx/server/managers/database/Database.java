package com.phoenixx.server.managers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Database {

    private Connection connection;

    private Queue<NetworkPreparedStatement> queuedStatements = new LinkedList<>();

    private String host, database, username, password;

    private ArrayList<String> test = new ArrayList<>();
    private int port;

    public Database() {}

    /**
     * Construct Database connection information.
     * @param host - the host ip address.
     * @param port - the port to connect.
     * @param database - the database.
     * @param username - the connection authorised username.
     * @param password - the connection authorised password.
     */
    public Database(String host, int port, String database, String username, String password) {

        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;

        this.connect();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not connect to the database!");
        }
    }

    public boolean disconnect() {

        try {

            if (connection == null || connection.isClosed()) {
                System.out.println("Connection is already closed!");
                return false;
            }

            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not close connection to database!");
            return false;
        }
    }

    public void queuePreparedStatement(String query, boolean isUpdate) {
        queuedStatements.add(new NetworkPreparedStatement(queuedStatements.size() + 1, query, isUpdate));
        System.out.println("Queued new query! '" + query + "' TYPE: " + (isUpdate ? "UPDATE" : "NON-UPDATE"));
    }

    public PreparedStatement sendPreparedStatement(String query, boolean isUpdate) {
        PreparedStatement statement = null;
        try {

            if (connection == null || connection.isClosed()) {
                connect();
            }

            statement = connection.prepareStatement(query);

            if (isUpdate) {
                statement.executeUpdate();
            } else {
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not run query! Reason: '" + e.getMessage() + "'");
        }
        return statement;
    }

    public void sendPreparedStatement(PreparedStatement preparedStatement, boolean isUpdate) {
        try {

            if (connection == null || connection.isClosed()) {
                connect();
            }

            if (isUpdate) {
                preparedStatement.executeUpdate();
            } else {
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not run query! Reason: '" + e.getMessage() + "'");
        }
    }

    public Queue<NetworkPreparedStatement> getQueuedStatements() {
        return queuedStatements;
    }

    public boolean isConnected() {
        try {
            return (connection == null ? false : (connection.isClosed() ? false : true));
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    protected class NetworkPreparedStatement {

        private int id;

        private String query;
        private boolean isUpdate;

        public NetworkPreparedStatement(int id, String query, boolean isUpdate) {
            this.id = id;
            this.query = query;
            this.isUpdate = isUpdate;
        }

        public int getId() {
            return id;
        }

        public String getQuery() {
            return query;
        }

        public boolean isUpdate() {
            return isUpdate;
        }
    }
}
