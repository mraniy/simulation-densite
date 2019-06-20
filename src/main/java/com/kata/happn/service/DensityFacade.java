package com.kata.happn.service;


import com.kata.happn.model.Point;
import com.kata.happn.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DensityFacade {


    private FileExtractor fileTransformer;


    private DensityCalculator densityCalculator;


    private ZoneCalculator zoneCalculator;


    @Autowired
    public DensityFacade(FileExtractor fileTransformer, DensityCalculator densityCalculator, ZoneCalculator zoneCalculator) {
        this.fileTransformer = fileTransformer;
        this.densityCalculator = densityCalculator;
        this.zoneCalculator = zoneCalculator;
    }

    public List<Zone> getDensiestZones(String filePath, String limit) {
        // call file reader interface and retrieve the list of points
        List<Point> points = fileTransformer.readFile(filePath);
        // for each point determine the zone that belongs to the point
        List<Zone> zones = points.stream()
                .map(zoneCalculator::calculateZoneBelongingToPoint)
                .collect(Collectors.toList());
        // send this list of zones to the calculator that will process the density of each zone and sort it by densiest zones
        // return the first {{number of zones}} in the list
        return densityCalculator.process(zones, Long.valueOf(limit));
    }
}
