package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Image;

import java.util.Optional;

public interface ImageRepository {
    void create (Image image);
    void update (Image image);
    Optional<Image> findByID(int id);
}
