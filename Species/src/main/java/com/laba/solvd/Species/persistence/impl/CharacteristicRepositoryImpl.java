package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.persistence.CharacteristicRepository;
import com.laba.solvd.Species.persistence.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CharacteristicRepositoryImpl implements CharacteristicRepository {
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
    public void create(Characteristic characteristic) {
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
    public void update(Characteristic characteristic) {
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
    public List<Characteristic> findByCategory(String category) {
        List<Characteristic> characteristics = new ArrayList<>();
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, category FROM characteristics WHERE category = ?");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Characteristic characteristic = new Characteristic();
                characteristic.setId(rs.getInt("id"));
                characteristic.setName(rs.getString("name"));
                characteristic.setCategory(rs.getString("category"));
                characteristics.add(characteristic);
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return characteristics;
    }

    @Override
    public Optional<Characteristic> findByID(int id) {
        Characteristic characteristic = null;
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, category FROM characteristics WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                characteristic = new Characteristic();
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

    public static List<Characteristic> mapRow(ResultSet rs) throws SQLException {
        List<Characteristic> characteristics = new ArrayList<>();
        while (rs.next()) {
            Characteristic characteristic = new Characteristic();
            characteristic.setId(rs.getInt("id"));
            characteristic.setName(rs.getString("name"));
            characteristic.setCategory(rs.getString("category"));
            characteristics.add(characteristic);
        }
        return characteristics;
    }
}
