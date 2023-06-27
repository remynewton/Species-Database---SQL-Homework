package com.laba.solvd.Species.parsers;

import com.laba.solvd.Species.domain.Species;

import java.io.File;
import java.util.List;

public interface Parser {
    Species parse(File file);
}
