package com.kata.happn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@AllArgsConstructor
public class Zone {
    private final Float minLatitude;
    private final Float maxLatitude;
    private final Float minLongitude;
    private final Float maxLongitude;



    public Zone(Zone zone) {
        this.minLatitude = zone.getMinLatitude();
        this.maxLatitude = zone.getMaxLatitude();
        this.minLongitude = zone.getMinLongitude();
        this.maxLongitude = zone.getMaxLongitude();
    }
}
