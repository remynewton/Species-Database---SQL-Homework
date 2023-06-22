package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Habitat;
import com.laba.solvd.Species.domain.Location;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.HabitatRepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class HabitatRepositoryImpl implements HabitatRepository {
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
    public void create(Habitat habitats) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO habitats (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, habitats.getName());
            ps.executeUpdate();
            ResultSet idResultSet = ps.getGeneratedKeys();
            while (idResultSet.next()) {
                habitats.setId(idResultSet.getInt("id"));
            }
            ps.close();
            List<Location> locations = habitats.getLocations();
            String temp = "INSERT INTO locations (name, latitude, longitude, habitats_id) VALUES (?, ?, ?, ?)";
            for (Location location : locations) {
                try (PreparedStatement tempPs = connection.prepareStatement(temp, Statement.RETURN_GENERATED_KEYS)) {
                    tempPs.setString(1, location.getName());
                    tempPs.setDouble(2, location.getLatitude());
                    tempPs.setDouble(3, location.getLatitude());
                    tempPs.setInt(4, habitats.getId());
                    tempPs.executeUpdate();
                    ResultSet idResultSet2 = tempPs.getGeneratedKeys();
                    while (idResultSet2.next()) {
                        location.setId(idResultSet2.getInt("id"));
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
