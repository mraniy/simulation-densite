package com.kata.happn.rest;

import com.kata.happn.model.Zone;
import com.kata.happn.service.DensityFacade;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SimulationDensityRest {

    private DensityFacade densityFacade;

    @Autowired
    public SimulationDensityRest(DensityFacade densityFacade) {
        this.densityFacade = densityFacade;
    }

    @RequestMapping(value = "/densiest/{limit}" , method = RequestMethod.GET)
    public ResponseEntity<List<Zone>> getDensiestZonesFromTsvFile(@PathVariable("limit") String limit){
        return Try.of(() -> densityFacade.getDensiestZones("/static/data.tsv",limit))
                .map(zones -> ResponseEntity.ok().body(zones))
                .getOrElseGet(throwable -> new ResponseEntity(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }




}
