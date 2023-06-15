package com.laba.solvd.Species.persistence.impl;

import com.laba.solvd.Species.domain.Taxonomies;
import com.laba.solvd.Species.persistence.ConnectionPool;
import com.laba.solvd.Species.persistence.TaxonomiesRepository;

import java.util.Optional;

public class TaxonomiesRepositoryImpl implements TaxonomiesRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Taxonomies taxonomy) {

    }

    @Override
    public void update(Taxonomies taxonomy) {

    }

    @Override
    public Optional<Taxonomies> findByKingdom(String kingdom) {
        return Optional.empty();
    }

    @Override
    public Optional<Taxonomies> findByClass(String itsClass) {
        return Optional.empty();
    }

    @Override
    public Optional<Taxonomies> findByFamily(String family) {
        return Optional.empty();
    }
}
