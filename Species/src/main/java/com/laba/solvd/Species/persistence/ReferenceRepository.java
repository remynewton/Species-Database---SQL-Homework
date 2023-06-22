package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Reference;

import java.util.Optional;

public interface ReferenceRepository {
    void create(Reference reference);
    Optional<Reference> findByID(int id);
}
