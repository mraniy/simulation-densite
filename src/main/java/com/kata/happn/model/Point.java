package com.kata.happn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {

    private String id;

    private Float latitude;

    private Float longitude;
}
