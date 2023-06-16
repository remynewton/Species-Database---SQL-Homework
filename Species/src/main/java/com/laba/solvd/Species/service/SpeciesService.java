package com.laba.solvd.Species.service;

import com.laba.solvd.Species.domain.Species;

import java.util.List;

public interface SpeciesService {
    void create (Species species);
    List<Species> findAll();
}
