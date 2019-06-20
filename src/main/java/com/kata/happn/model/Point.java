package com.kata.happn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {

    private final String id;

    private final Float latitude;

    private final Float longitude;
}
