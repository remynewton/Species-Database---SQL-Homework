package com.laba.solvd.Species.service.impl;

import com.laba.solvd.Species.domain.Image;
import com.laba.solvd.Species.persistence.ImageRepository;
import com.laba.solvd.Species.persistence.impl.ImageRepositoryImpl;
import com.laba.solvd.Species.persistence.impl.MapperImpl.ImageMapperImpl;
import com.laba.solvd.Species.service.ImageService;

public class ImageServiceImpl implements ImageService {
    private final ImageRepository imagesRepository;

    public ImageServiceImpl() {
        // this.imagesRepository = new ImageRepositoryImpl();
        this.imagesRepository = new ImageMapperImpl();
    }

    @Override
    public Image create(Image image) {
        imagesRepository.create(image);
        return image;
    }
}
