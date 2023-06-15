package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.References;

import java.sql.SQLException;
import java.util.Optional;

public interface ReferencesRepository {
    void create(References reference) throws SQLException, ClassNotFoundException;
    Optional<References> findByID(int id) throws SQLException, ClassNotFoundException;
}
