package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Habitats;

import java.sql.SQLException;

public interface HabitatsRepository {
    void create (Habitats habitats);
}