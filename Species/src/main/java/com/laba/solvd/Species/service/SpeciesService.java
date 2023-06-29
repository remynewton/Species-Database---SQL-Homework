package com.laba.solvd.Species.service;

import com.laba.solvd.Species.domain.Species;

import java.util.List;
import java.util.Optional;

public interface SpeciesService {
    void create (Species species);
    List<Species> findAll();
    Species findById(int id);
}
