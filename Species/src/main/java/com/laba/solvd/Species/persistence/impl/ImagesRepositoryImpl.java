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
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Images image) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO images (id, url, format) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, image.getId());
        ps.setString(2, image.getUrl());
        ps.setString(3, image.getFormat());
        ps.executeUpdate();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void update(Images image) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE images SET id = ?, url = ?, format = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, image.getId());
        ps.setString(2, image.getUrl());
        ps.setString(3, image.getFormat());
        ps.executeUpdate();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public Optional<Images> findByID(int id) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        Images image = null;
        String sql = "SELECT id, url, format FROM images WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int oid = rs.getInt("id");
            String url = rs.getString("url");
            String format = rs.getString("format");
            image = new Images(oid, url, format);
        }
        return Optional.ofNullable(image);
    }
}
