package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Taxonomies;
import java.util.Optional;

public interface TaxonomiesRepository {
    void create (Taxonomies taxonomy);
    void update (Taxonomies taxonomy);
    Optional<Taxonomies> findByKingdom(String kingdom);
    Optional<Taxonomies> findByClass(String itsClass);
    Optional<Taxonomies> findByFamily(String family);
}
