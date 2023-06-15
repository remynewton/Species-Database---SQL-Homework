package com.laba.solvd.Species.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static final int MAX_POOL_SIZE = Integer.parseInt(Config.POOLSIZE.getValue());
    private final List<Connection> connections;
    private int currentPoolSize;
    private static ConnectionPool instance;

    private ConnectionPool() {
        connections = new ArrayList<>();
        currentPoolSize = 0;
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connections.isEmpty()) {
            if (currentPoolSize < MAX_POOL_SIZE) {
                Connection connection = createConnection();
                if (connection != null) {
                    connections.add(connection);
                    currentPoolSize++;
                }
            } else {
                throw new SQLException("Connection pool is full!");
            }
        }
        return connections.remove(0);
    }

    public synchronized void releaseConnection(Connection connection) throws SQLException {
        if (currentPoolSize < MAX_POOL_SIZE) {
            connections.add(connection);
            currentPoolSize++;
        } else {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new SQLException("Could not release connection");
            }
        }
    }

    private Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Config.DRIVER.getValue());
        return DriverManager.getConnection(Config.URL.getValue(), Config.USERNAME.getValue(), Config.PASSWORD.getValue());
    }
}