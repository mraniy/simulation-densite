package com.kata.happn.service;


import com.kata.happn.model.Point;
import com.kata.happn.model.Zone;
import com.kata.happn.utils.FloatUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.kata.happn.utils.FloatUtils.retrieveMaxLimit;
import static com.kata.happn.utils.FloatUtils.retrieveMinLimit;

@Component
public class ZoneCalculator {

    public Zone calculateZoneBelongingToPoint(Point point) throws IllegalArgumentException {

        return Optional.ofNullable(point)
                .filter(this::isPointCorrect)
                .map(this::getZone)
                .orElseThrow(() -> new IllegalArgumentException("the point you are trying to determine the zone is not according"));

    }

    private Zone getZone(Point point) {
        Float minLatitude = retrieveMinLimit(point.getLatitude());
        Float maxLatitude = retrieveMaxLimit(point.getLatitude());
        Float minLongitude = retrieveMinLimit(point.getLongitude());
        Float maxLongitude = retrieveMaxLimit(point.getLongitude());
        return new Zone(minLatitude, maxLatitude, minLongitude, maxLongitude);
    }


    private boolean isPointCorrect(Point point1) {
        return point1.getLatitude()>= -90F
                && point1.getLatitude()<= 90F
                && point1.getLongitude() >= -180F
                && point1.getLongitude() <= 180F;
    }

}
