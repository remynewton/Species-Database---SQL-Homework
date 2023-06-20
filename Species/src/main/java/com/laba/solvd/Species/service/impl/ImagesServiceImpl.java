package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Images;
import com.laba.solvd.Species.persistence.ImagesRepository;
import com.laba.solvd.Species.persistence.impl.ImagesRepositoryImpl;
import com.laba.solvd.Species.service.ImagesService;

public class ImagesServiceImpl implements ImagesService {
    private final ImagesRepository imagesRepository;

    public ImagesServiceImpl() {
        this.imagesRepository = new ImagesRepositoryImpl();
    }

    @Override
    public void create(Images image) {
        image.setId(null);
        imagesRepository.create(image);
    }
}
