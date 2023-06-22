package com.laba.solvd.Species.service;

import com.laba.solvd.Species.domain.Image;

public interface ImageService {
    void create (Image image);
    Image create (Image image, int id);
}
