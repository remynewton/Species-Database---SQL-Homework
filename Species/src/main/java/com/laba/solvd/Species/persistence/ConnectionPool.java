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

    private ConnectionPool() throws SQLException, ClassNotFoundException {
        connections = new ArrayList<>();
        initializePool();
    }

    public static synchronized ConnectionPool getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connections.isEmpty()) {
            if (currentPoolSize <= MAX_POOL_SIZE) {
                Connection connection = createConnection();
                if (connection != null) {
                    currentPoolSize++;
                    return connection;
                }
            } else {
                throw new SQLException("Connection pool is full!");
            }
        }
        return connections.remove(0);
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        connections.notifyAll();
    }

    private Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Config.DRIVER.getValue());
        return DriverManager.getConnection(Config.URL.getValue(), Config.USERNAME.getValue(), Config.PASSWORD.getValue());
    }

    private void initializePool() throws SQLException, ClassNotFoundException {
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            connections.add(createConnection());
            currentPoolSize++;
        }
    }
}
