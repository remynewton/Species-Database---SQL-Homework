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
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Habitats habitats) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO habitats (id, name) VALUES (?, ?)";
        String temp = "INSERT INTO locations (id, name, latitude, longitude, habitats_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, habitats.getId());
        ps.setString(2, habitats.getName());
        ps.executeUpdate();
        ps.close();
        ArrayList<Locations> locations = habitats.getLocations();
        locations.forEach(location -> {
            try (PreparedStatement tempPs = connection.prepareStatement(temp)) {
                tempPs.setInt(1, location.getId());
                tempPs.setString(2, location.getName());
                tempPs.setDouble(3, location.getCoordinates()[0]);
                tempPs.setDouble(4, location.getCoordinates()[1]);
                tempPs.setInt(5, habitats.getId());
                tempPs.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        });
        CONNECTION_POOL.releaseConnection(connection);
    }
}
