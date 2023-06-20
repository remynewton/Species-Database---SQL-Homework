package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Images;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.ImagesRepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class ImagesRepositoryImpl implements ImagesRepository {
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
    public void create(Images image) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO images (url, format) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, image.getUrl());
            ps.setString(2, image.getFormat());
            ps.executeUpdate();
            ResultSet idResultSet = ps.getGeneratedKeys();
            while (idResultSet.next()) {
                image.setId(idResultSet.getInt("id"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Images image) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE images SET id = ?, url = ?, format = ? WHERE id = ?");
            ps.setInt(1, image.getId());
            ps.setString(2, image.getUrl());
            ps.setString(3, image.getFormat());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Images> findByID(int id) {
        Images image = null;
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, url, format FROM images WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                image = new Images();
                image.setId(rs.getInt("id"));
                image.setUrl(rs.getString("url"));
                image.setFormat(rs.getString("format"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        assert image != null;
        return Optional.of(image);
    }
}