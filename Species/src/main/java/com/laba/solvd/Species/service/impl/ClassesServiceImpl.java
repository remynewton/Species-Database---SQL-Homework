package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Classes;
import com.laba.solvd.Species.persistence.ClassesRepository;
import com.laba.solvd.Species.persistence.impl.ClassesRepositoryImpl;
import com.laba.solvd.Species.service.ClassesService;

public class ClassesServiceImpl implements ClassesService {
    private final ClassesRepository classesRepository;

    public ClassesServiceImpl() {
        this.classesRepository = new ClassesRepositoryImpl();
    }

    @Override
    public void create(Classes oclass) {
        oclass.setId(null);
        classesRepository.create(oclass);
    }
}
