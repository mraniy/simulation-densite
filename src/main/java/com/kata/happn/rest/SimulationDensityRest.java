package com.kata.happn.rest;

import com.kata.happn.model.Zone;
import com.kata.happn.service.FileTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SimulationDensityRest {

    @Autowired
    FileTransformer fileTransformer;

    public List<Zone> retrieveDensestZones(/*@RequestParam("file") MultipartFile file, */ String numberOfZones) {
        // call file reader interface and retrieve the list of points
        // for each point determine the zone that belongs to the point
        // send this list of zones to the calculator that will calculate the density of each zone and sort it by densiest zones
        // return the first {{number of zones}} in the list
        return null;
    }


}
