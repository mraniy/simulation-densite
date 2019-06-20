package com.kata.happn.service;


import com.google.common.collect.ImmutableMap;
import com.kata.happn.model.Zone;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DensityCalculator {

    public Map<Zone, Long> process(List<Zone> zoneList) {
        Map<Zone, Long> zoneAndDensity = Optional.ofNullable(zoneList)
                .filter(zones -> !CollectionUtils.isEmpty(zones))
                .map(zones ->
                        zoneList.stream()
                                .collect(Collectors.groupingBy(zone -> zone,
                                        Collectors.counting()))
                ).orElseGet(() -> ImmutableMap.of())
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return zoneAndDensity;
    }
}
