package server.managers.database;

import server.ServerNetworkMain;

public class DatabaseThread extends Thread {

    private boolean isRunning = true;

    private long lastRan = 0;

    @Override
    public void run() {

        while (isRunning) {

            if (lastRan == 0 || System.currentTimeMillis() >= lastRan) {

                Database.NetworkPreparedStatement statement = ServerNetworkMain.getDatabase().getQueuedStatements().poll();

                if (statement != null) {
                    ServerNetworkMain.getDatabase().sendPreparedStatement(statement.getQuery(), statement.isUpdate());
                    System.out.println("ID: " + statement.getId() + " QUERY: " + statement.getQuery());
                }

                lastRan = System.currentTimeMillis() + 1000;
            }
        }
    }

    public long getLastRan() {
        return lastRan;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
