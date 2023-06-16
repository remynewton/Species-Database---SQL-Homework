package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Classes;
import com.laba.solvd.Species.domain.Kingdoms;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.KingdomsRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class KingdomsRepositoryImpl implements KingdomsRepository {
    private static final ConnectionPool CONNECTION_POOL;

    static {
        try {
            CONNECTION_POOL = ConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Kingdoms kingdom) {
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO kingdoms (id, name) VALUES (?, ?)");
        ) {
            ps.setInt(1, kingdom.getId());
            ps.setString(2, kingdom.getName());
            ps.executeUpdate();

            List<Classes> classes = kingdom.getClasses();
            String temp = "INSERT INTO classes (id, name, kingdom_id) VALUES (?, ?, ?)";
            for (Classes classEntry : classes) {
                try (PreparedStatement tempPs = connection.prepareStatement(temp)) {
                    tempPs.setInt(1, classEntry.getId());
                    tempPs.setString(2, classEntry.getName());
                    tempPs.setInt(3, kingdom.getId());
                    tempPs.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
