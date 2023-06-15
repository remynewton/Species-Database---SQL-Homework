package com.laba.solvd.Species.persistence.impl;

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
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Species species) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO species (id, common_name, scientific_name, conservation_statuses_id, families_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, species.getId());
        ps.setString(2, species.getCommonName());
        ps.setString(3, species.getScientificName());
        ps.setInt(4, species.getConservationStatusID());
        ps.setInt(5, species.getFamilyID());
        ps.executeUpdate();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public List<Species> findAll() throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT * FROM species";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Species> speciesList = new ArrayList<>();
        while (rs.next()) {
            Species species = new Species();
            species.setId(rs.getInt("id"));
            species.setCommonName(rs.getString("common_name"));
            species.setScientificName(rs.getString("scientific_name"));
            species.setConservationStatusID(rs.getInt("conservation_statuses_id"));
            // implement some way to add taxonomy foreign key
            speciesList.add(species);
        }

        rs.close();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);

        return speciesList;
    }

    @Override
    public Optional<Species> findByID(int ID) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT * FROM species WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();

        Optional<Species> optionalSpecies = Optional.empty();
        if (rs.next()) {
            Species species = new Species();
            species.setId(rs.getInt("id"));
            species.setCommonName(rs.getString("common_name"));
            species.setScientificName(rs.getString("scientific_name"));
            species.setConservationStatusID(rs.getInt("conservation_statuses_id"));
            // implement some way to add taxonomy foreign key
            optionalSpecies = Optional.of(species);
        }

        rs.close();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);

        return optionalSpecies;
    }

    @Override
    public void update(Species species) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE species SET common_name = ?, scientific_name = ?, conservation_statuses_id = ?, families_id = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, species.getCommonName());
        ps.setString(2, species.getScientificName());
        ps.setInt(3, species.getConservationStatusID());
        ps.setInt(4, species.getFamilyID());
        ps.setInt(5, species.getId());
        ps.executeUpdate();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteByID(int id) throws SQLException, ClassNotFoundException {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "DELETE FROM species WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        CONNECTION_POOL.releaseConnection(connection);
    }
}
