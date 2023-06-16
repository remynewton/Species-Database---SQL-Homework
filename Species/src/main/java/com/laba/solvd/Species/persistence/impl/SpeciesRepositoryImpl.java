package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.ConservationStatuses;
import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.SpeciesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpeciesRepositoryImpl implements SpeciesRepository {

    private static final ConnectionPool CONNECTION_POOL;

    static {
        try {
            CONNECTION_POOL = ConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Species species) {
        String sql = "INSERT INTO species (id, common_name, scientific_name, conservation_statuses_id, families_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = CONNECTION_POOL.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, species.getId());
            ps.setString(2, species.getCommonName());
            ps.setString(3, species.getScientificName());
            ps.setInt(4, species.getConservationStatus().getId());
            ps.setInt(5, species.getFamily().getId());
            ps.executeUpdate();
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Species> findAll() {
        List<Species> speciesList = new ArrayList<>();
        String sql = "SELECT * FROM species";
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Species species = new Species();
                species.setId(rs.getInt("id"));
                species.setCommonName(rs.getString("common_name"));
                species.setScientificName(rs.getString("scientific_name"));
                speciesList.add(species);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return speciesList;
    }

    @Override
    public Optional<Species> findByID(int ID) {
        Optional<Species> optionalSpecies = Optional.empty();
        String sql = "SELECT * FROM species WHERE id = ?";
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Species species = new Species();
                    species.setId(rs.getInt("id"));
                    species.setCommonName(rs.getString("common_name"));
                    species.setScientificName(rs.getString("scientific_name"));
                    optionalSpecies = Optional.of(species);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return optionalSpecies;
    }

    @Override
    public void update(Species species) {
        String sql = "UPDATE species SET common_name = ?, scientific_name = ?, conservation_statuses_id = ?, families_id = ? WHERE id = ?";
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, species.getCommonName());
            ps.setString(2, species.getScientificName());
            ps.setInt(3, species.getConservationStatus().getId());
            ps.setInt(4, species.getFamily().getId());
            ps.setInt(5, species.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String sql = "DELETE FROM species WHERE id = ?";
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
