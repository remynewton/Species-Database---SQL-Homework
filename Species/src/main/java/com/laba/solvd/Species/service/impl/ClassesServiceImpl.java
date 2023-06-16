package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Classes;
import com.laba.solvd.Species.persistence.ClassesRepository;
import com.laba.solvd.Species.persistence.impl.ClassesRepositoryImpl;
import com.laba.solvd.Species.service.ClassesService;

public class ClassesServiceImpl implements ClassesService {
    private final ClassesRepository classesRepository;

    public ClassesServiceImpl(ClassesRepository classesRepository) {
        this.classesRepository = new ClassesRepositoryImpl();
    }

    @Override
    public void create(Classes oclass) {
        classesRepository.create(oclass);
    }
}
