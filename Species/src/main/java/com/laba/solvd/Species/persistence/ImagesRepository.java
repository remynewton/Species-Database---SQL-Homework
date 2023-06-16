package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Images;

import java.util.Optional;

public interface ImagesRepository {
    void create (Images image);
    void update (Images image);
    Optional<Images> findByID(int id);
}
