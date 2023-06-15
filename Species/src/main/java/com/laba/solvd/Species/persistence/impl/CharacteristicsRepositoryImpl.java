package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Characteristics;
import com.laba.solvd.Species.persistence.CharacteristicsRepository;
import com.laba.solvd.Species.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CharacteristicsRepositoryImpl implements CharacteristicsRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Characteristics characteristic) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO characteristics (id, name, category) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, characteristic.getId());
        ps.setString(2, characteristic.getName());
        ps.setString(3, characteristic.getCategory());
        ps.executeUpdate();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void update(Characteristics characteristic) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE characteristics SET id = ?, name = ?, category = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, characteristic.getId());
        ps.setString(2, characteristic.getName());
        ps.setString(3, characteristic.getCategory());
        ps.executeUpdate();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public Optional<Characteristics> findByCategory(String category) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        Characteristics characteristic = null;
        String sql = "SELECT id, name, category FROM characteristics WHERE category = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(3, category);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String category1 = rs.getString("category");
            characteristic = new Characteristics(id, name, category1);
        }
        return Optional.ofNullable(characteristic);
    }

    @Override
    public Optional<Characteristics> findByID(int id) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        Characteristics characteristic = null;
        String sql = "SELECT id, name, category FROM characteristics WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int oid = rs.getInt("id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            characteristic = new Characteristics(oid, name, category);
        }
        return Optional.ofNullable(characteristic);
    }

    @Override
    public void deleteByID(int id) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "DELETE FROM characteristics WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);
    }
}
