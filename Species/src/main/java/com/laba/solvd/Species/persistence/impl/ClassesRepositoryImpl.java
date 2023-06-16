package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Classes;
import com.laba.solvd.Species.domain.Families;
import com.laba.solvd.Species.persistence.ClassesRepository;
import com.laba.solvd.Species.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClassesRepositoryImpl implements ClassesRepository {
    private static final ConnectionPool CONNECTION_POOL;

    static {
        try {
            CONNECTION_POOL = ConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Classes classes) {
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO classes (id, name) VALUES (?, ?)");
        ) {
            ps.setInt(1, classes.getId());
            ps.setString(2, classes.getName());
            ps.executeUpdate();

            List<Families> families = classes.getFamilies();
            String temp = "INSERT INTO families (id, name, class_id) VALUES (?, ?, ?)";
            for (Families family : families) {
                try (PreparedStatement tempPs = connection.prepareStatement(temp)) {
                    tempPs.setInt(1, family.getId());
                    tempPs.setString(2, family.getName());
                    tempPs.setInt(3, classes.getId());
                    tempPs.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

