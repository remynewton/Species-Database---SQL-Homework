package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Habitats;
import com.laba.solvd.Species.domain.Locations;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.HabitatsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO habitats (id, name) VALUES (?, ?)");
        ) {
            ps.setInt(1, habitats.getId());
            ps.setString(2, habitats.getName());
            ps.executeUpdate();
            ArrayList<Locations> locations = habitats.getLocations();
            String temp = "INSERT INTO locations (id, name, latitude, longitude, habitats_id) VALUES (?, ?, ?, ?, ?)";
            for (Locations location : locations) {
                try (PreparedStatement tempPs = connection.prepareStatement(temp)) {
                    tempPs.setInt(1, location.getId());
                    tempPs.setString(2, location.getName());
                    tempPs.setDouble(3, location.getCoordinates()[0]);
                    tempPs.setDouble(4, location.getCoordinates()[1]);
                    tempPs.setInt(5, habitats.getId());
                    tempPs.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
