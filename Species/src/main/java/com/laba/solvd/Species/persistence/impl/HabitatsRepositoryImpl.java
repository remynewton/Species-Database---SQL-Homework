package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Habitats;
import com.laba.solvd.Species.domain.Locations;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.HabitatsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HabitatsRepositoryImpl implements HabitatsRepository {
    private static final ConnectionPool CONNECTION_POOL;

    static {
        try {
            CONNECTION_POOL = ConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Habitats habitats) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO habitats (id, name) VALUES (?, ?)");
            ps.setInt(1, habitats.getId());
            ps.setString(2, habitats.getName());
            ps.executeUpdate();
            ps.close();
            List<Locations> locations = habitats.getLocations();
            String temp = "INSERT INTO locations (id, name, latitude, longitude, habitats_id) VALUES (?, ?, ?, ?, ?)";
            for (Locations location : locations) {
                try (PreparedStatement tempPs = connection.prepareStatement(temp)) {
                    tempPs.setInt(1, location.getId());
                    tempPs.setString(2, location.getName());
                    tempPs.setDouble(3, location.getLatitude());
                    tempPs.setDouble(4, location.getLatitude());
                    tempPs.setInt(5, habitats.getId());
                    tempPs.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
