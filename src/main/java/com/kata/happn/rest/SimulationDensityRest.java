package com.kata.happn.rest;

import com.kata.happn.model.Point;
import com.kata.happn.model.Zone;
import com.kata.happn.service.DensityCalculator;
import com.kata.happn.service.FileTransformer;
import com.kata.happn.service.ZoneCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SimulationDensityRest {

    @Autowired
    FileTransformer fileTransformer;

    @Autowired
    DensityCalculator densityCalculator;

    @Autowired
    ZoneCalculator zoneCalculator;

    @GetMapping("/densiest")
    public List<Zone> getUserById(String limit){
        // call file reader interface and retrieve the list of points
        List<Point> points = fileTransformer.readtsvFile("/static/data.tsv");
        // for each point determine the zone that belongs to the point
        List<Zone> zones = points.stream()
                .map(zoneCalculator::calculateZoneBelongingToPoint)
                .collect(Collectors.toList());
        // send this list of zones to the calculator that will process the density of each zone and sort it by densiest zones
        Map<Zone, Long> zoneAndDensity = densityCalculator.process(zones);
        // return the first {{number of zones}} in the list
        return zoneAndDensity.keySet()
                .stream()
                .limit(Long.valueOf(2))
                .collect(Collectors.toList());
    }




}
