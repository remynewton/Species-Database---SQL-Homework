package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Class;
import com.laba.solvd.Species.persistence.ClassRepository;
import com.laba.solvd.Species.persistence.impl.ClassRepositoryImpl;
import com.laba.solvd.Species.service.ClassService;

public class ClassServiceImpl implements ClassService {
    private final ClassRepository classesRepository;

    public ClassServiceImpl() {
        this.classesRepository = new ClassRepositoryImpl();
    }

    @Override
    public void create(Class oclass) {
        oclass.setId(null);
        classesRepository.create(oclass);
    }
}
