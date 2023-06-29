package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.*;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.SpeciesRepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpeciesRepositoryImpl implements SpeciesRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private static final ConnectionPool CONNECTION_POOL;
    private static final String sqlFindAll = "SELECT s.id AS species_id, s.common_name, s.scientific_name, f.name AS family_name, cs.status AS conservation_status, i.url AS image_url, h.name AS habitat_name, c.name AS characteristic_name\n" +
            "FROM species.species AS s\n" +
            "JOIN species.families AS f ON s.families_id = f.id\n" +
            "JOIN species.conservation_statuses AS cs ON s.conservation_statuses_id = cs.id\n" +
            "LEFT JOIN species.species_images AS si ON s.id = si.species_species_id\n" +
            "LEFT JOIN species.images AS i ON si.image_id = i.id\n" +
            "LEFT JOIN species.species_locations AS sl ON s.id = sl.species\n" +
            "LEFT JOIN species.locations AS l ON sl.location_id = l.id\n" +
            "LEFT JOIN species.habitats AS h ON l.habitats_id = h.id\n" +
            "LEFT JOIN species.species_characteristics AS sc ON s.id = sc.species_id\n" +
            "LEFT JOIN species.characteristics AS c ON sc.characteristic_id = c.id\n" +
            "JOIN species.species_references AS sr ON s.id = sr.species_id\n" +
            "JOIN species.references AS r ON sr.reference_id = r.id;\n";

    static {
        try {
            CONNECTION_POOL = ConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Species species) {
        Connection connection = null;
        String sql = "INSERT INTO species (common_name, scientific_name, conservation_statuses_id, families_id) VALUES (?, ?, ?, ?)";
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, species.getCommonName());
            ps.setString(2, species.getScientificName());
            ps.setInt(3, species.getConservationStatus().getId());
            ps.setInt(4, species.getFamily().getId());
            ps.executeUpdate();
            ResultSet idResultSet = ps.getGeneratedKeys();
            while (idResultSet.next()) {
                species.setId(idResultSet.getInt("id"));
            }
            ps.close();
        } catch(ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Species> findAll() {
        List<Species> speciesList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlFindAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Species species = mapRowToSpecies(rs);
                speciesList.add(species);
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return speciesList;
    }

    private Species mapRowToSpecies(ResultSet rs) throws SQLException {
        Species species = new Species();
        species.setId(rs.getInt("species_id"));
        species.setCommonName(rs.getString("common_name"));
        species.setScientificName(rs.getString("scientific_name"));
        species.setImages(ImageRepositoryImpl.mapRow(rs));
        species.setCharacteristics(CharacteristicRepositoryImpl.mapRow(rs));
        species.setReferences(ReferenceRepositoryImpl.mapRow(rs));
        return species;
    }

    @Override
    public Species findByID(int ID) {
        String sql = "SELECT * FROM species WHERE id = ?";
        Connection connection = null;
        Species species = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    species = new Species();
                    species.setId(rs.getInt("id"));
                    species.setCommonName(rs.getString("common_name"));
                    species.setScientificName(rs.getString("scientific_name"));
                }
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return species;
    }

    @Override
    public void update(Species species) {
        Connection connection = null;
                String sql = "UPDATE species SET common_name = ?, scientific_name = ?, conservation_statuses_id = ?, families_id = ? WHERE id = ?";
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, species.getCommonName());
            ps.setString(2, species.getScientificName());
            ps.setInt(3, species.getConservationStatus().getId());
            ps.setInt(4, species.getFamily().getId());
            ps.setInt(5, species.getId());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteByID(int id) {
        String sql = "DELETE FROM species WHERE id = ?";
        Connection connection = null;
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void setReference(Species species, Reference reference) {
        Connection connection = null;
        String sql = "INSERT INTO species_references (species_id, reference_id) VALUES (?, ?)";
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, species.getId());
            ps.setInt(2, reference.getId());
            ps.executeUpdate();
            ps.close();
        } catch(ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void setCharacteristic(Species species, Characteristic characteristic) {
        Connection connection = null;
        String sql = "INSERT INTO species_characteristics (species_id, characteristic_id) VALUES (?, ?)";
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, species.getId());
            ps.setInt(2, characteristic.getId());
            ps.executeUpdate();
            ps.close();
        } catch(ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void setImage(Species species, Image image) {
        Connection connection = null;
        String sql = "INSERT INTO species_images (image_id, species_species_id) VALUES (?, ?)";
        try {
            connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, image.getId());
            ps.setInt(2, species.getId());
            ps.executeUpdate();
            ps.close();
        } catch(ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void setConservationStatus(Species species, ConservationStatus conservationStatus) {
    }
}
