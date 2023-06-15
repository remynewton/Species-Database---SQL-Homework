package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Images;

import java.sql.SQLException;
import java.util.Optional;

public interface ImagesRepository {
    void create (Images image) throws SQLException, ClassNotFoundException;
    void update (Images image) throws SQLException, ClassNotFoundException;
    Optional<Images> findByID(int id) throws SQLException, ClassNotFoundException;
}
