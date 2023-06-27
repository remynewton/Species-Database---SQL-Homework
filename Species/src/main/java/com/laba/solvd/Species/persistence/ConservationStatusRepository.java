package com.laba.solvd.Species.persistence;

import com.laba.solvd.Species.domain.ConservationStatus;

public interface ConservationStatusRepository {
    void create (ConservationStatus conservationStatus);
}
