package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.References;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.ReferencesRepository;

import java.sql.*;
import java.util.Optional;

public class ReferencesRepositoryImpl implements ReferencesRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(References reference) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO references (id, title, author, year) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, reference.getId());
        ps.setString(2, reference.getTitle());
        ps.setString(3, reference.getAuthor());
        ps.setDate(3, (Date) reference.getDate());
        ps.executeUpdate();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public Optional<References> findByID(int id) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        References reference = null;
        String sql = "SELECT id, title, author, year FROM references WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int oid = rs.getInt("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            Date date = rs.getDate("year");
            reference = new References(oid, title, author, date);
        }
        return Optional.ofNullable(reference);
    }
}
