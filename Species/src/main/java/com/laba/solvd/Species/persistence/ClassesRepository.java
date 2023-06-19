package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.Classes;

import java.sql.SQLException;

public interface ClassesRepository {
    void create (Classes oclass);
}
