package com.kata.happn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@AllArgsConstructor
public class Zone {
    private Float minLatitude;
    private Float maxLatitude;
    private Float minLongitude;
    private Float maxLongitude;
}
