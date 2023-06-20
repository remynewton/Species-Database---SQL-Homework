package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Characteristics;
import com.laba.solvd.Species.persistence.CharacteristicsRepository;
import com.laba.solvd.Species.persistence.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class CharacteristicsRepositoryImpl implements CharacteristicsRepository {
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
    public void create(Characteristics characteristic) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO characteristics (name, category) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, characteristic.getName());
            ps.setString(2, characteristic.getCategory());
            ps.executeUpdate();
            ResultSet idResultSet = ps.getGeneratedKeys();
            while (idResultSet.next()) {
                characteristic.setId(idResultSet.getInt("id"));
            }
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Characteristics characteristic) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE characteristics SET name = ?, category = ? WHERE id = ?");
            ps.setString(1, characteristic.getName());
            ps.setString(2, characteristic.getCategory());
            ps.setInt(3, characteristic.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Characteristics> findByCategory(String category) {
        Characteristics characteristic = null;
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, category FROM characteristics WHERE category = ?");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                characteristic = new Characteristics();
                characteristic.setId(rs.getInt("id"));
                characteristic.setName(rs.getString("name"));
                characteristic.setCategory(rs.getString("category"));
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        assert characteristic != null;
        return Optional.of(characteristic);
    }

    @Override
    public Optional<Characteristics> findByID(int id) {
        Characteristics characteristic = null;
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, category FROM characteristics WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                characteristic = new Characteristics();
                characteristic.setId(rs.getInt("id"));
                characteristic.setName(rs.getString("name"));
                characteristic.setCategory(rs.getString("category"));
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        assert characteristic != null;
        return Optional.of(characteristic);
    }

    @Override
    public void deleteByID(int id) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM characteristics WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
