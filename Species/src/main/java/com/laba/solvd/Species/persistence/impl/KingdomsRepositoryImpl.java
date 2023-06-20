package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Classes;
import com.laba.solvd.Species.domain.Kingdoms;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.KingdomsRepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class KingdomsRepositoryImpl implements KingdomsRepository {
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
    public void create(Kingdoms kingdom) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO kingdoms (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, kingdom.getName());
            ps.executeUpdate();
            ResultSet idResultSet = ps.getGeneratedKeys();
            while (idResultSet.next()) {
                kingdom.setId(idResultSet.getInt("id"));
            }
            ps.close();
            List<Classes> classes = kingdom.getClasses();
            String temp = "INSERT INTO classes (name, kingdom_id) VALUES (?, ?)";
            for (Classes classEntry : classes) {
                try (PreparedStatement tempPs = connection.prepareStatement(temp, Statement.RETURN_GENERATED_KEYS)) {
                    tempPs.setString(1, classEntry.getName());
                    tempPs.setInt(2, kingdom.getId());
                    tempPs.executeUpdate();
                    ResultSet idResultSet2 = tempPs.getGeneratedKeys();
                    while (idResultSet2.next()) {
                        classEntry.setId(idResultSet.getInt("id"));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
