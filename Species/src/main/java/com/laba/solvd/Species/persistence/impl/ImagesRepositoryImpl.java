package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Images;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.ImagesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ImagesRepositoryImpl implements ImagesRepository {
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
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO images (id, url, format) VALUES (?, ?, ?)");
        ) {
            ps.setInt(1, image.getId());
            ps.setString(2, image.getUrl());
            ps.setString(3, image.getFormat());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Images image) {
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE images SET id = ?, url = ?, format = ? WHERE id = ?");
        ) {
            ps.setInt(1, image.getId());
            ps.setString(2, image.getUrl());
            ps.setString(3, image.getFormat());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Images> findByID(int id) {
        Images image = null;
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT id, url, format FROM images WHERE id = ?");
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String url = null;
            String format = null;
            int oid = 0;
            image = null;
            if (rs.next()) {
                image = new Images();
                image.setId(rs.getInt("id"));
                image.setUrl(rs.getString("url"));
                image.setFormat(rs.getString("format"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.of(image);
    }
}