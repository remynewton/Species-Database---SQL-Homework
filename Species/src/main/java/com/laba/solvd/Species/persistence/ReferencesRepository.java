package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.References;

import java.util.Optional;

public interface ReferencesRepository {
    void create(References reference);
    Optional<References> findByID(int id);
}
