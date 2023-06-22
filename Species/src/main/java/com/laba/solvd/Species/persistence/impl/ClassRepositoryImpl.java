package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Class;
import com.laba.solvd.Species.domain.Family;
import com.laba.solvd.Species.persistence.ClassRepository;
import com.laba.solvd.Species.persistence.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class ClassRepositoryImpl implements ClassRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private static final ConnectionPool CONNECTION_POOL;

    static {
        try {
            CONNECTION_POOL = ConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Class classes) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO classes (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, classes.getName());
            ps.executeUpdate();
            ResultSet idResultSet = ps.getGeneratedKeys();
            while (idResultSet.next()) {
                classes.setId(idResultSet.getInt("id"));
            }
            ps.close();
            List<Family> families = classes.getFamilies();
            String temp = "INSERT INTO families (name, class_id) VALUES (?, ?)";
            for (Family family : families) {
                try (PreparedStatement tempPs = connection.prepareStatement(temp, Statement.RETURN_GENERATED_KEYS)) {
                    tempPs.setString(1, family.getName());
                    tempPs.setInt(2, classes.getId());
                    tempPs.executeUpdate();
                    ResultSet idResultSet2 = tempPs.getGeneratedKeys();
                    while (idResultSet2.next()) {
                        family.setId(idResultSet2.getInt("id"));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}

