package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.References;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.ReferencesRepository;

import java.sql.*;
import java.util.Optional;

public class ReferencesRepositoryImpl implements ReferencesRepository {
    private static final ConnectionPool CONNECTION_POOL;

    static {
        try {
            CONNECTION_POOL = ConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(References reference) {
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO references (id, title, author, year) VALUES (?, ?, ?, ?)");
            ps.setInt(1, reference.getId());
            ps.setString(2, reference.getTitle());
            ps.setString(3, reference.getAuthor());
            ps.setDate(4, (Date) reference.getDate());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<References> findByID(int id) {
        References reference = null;
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, title, author, year FROM references WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                reference = new References();
                reference.setId(rs.getInt("id"));
                reference.setTitle(rs.getString("title"));
                reference.setAuthor(rs.getString("author"));
                reference.setDate(rs.getDate("date"));
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(reference);
    }
}
