package com.kata.happn.service;

import com.kata.happn.model.Point;

import java.util.List;

public interface FileExtractor {

     List<Point> readFile(String filePath);
}
