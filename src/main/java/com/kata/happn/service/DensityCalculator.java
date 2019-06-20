package com.kata.happn.service;


import com.google.common.collect.ImmutableMap;
import com.kata.happn.model.Zone;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DensityCalculator {

    public List<Zone> process(List<Zone> zoneList,Long limit) {
        return Optional.ofNullable(zoneList)
                .filter(zones -> !CollectionUtils.isEmpty(zones))
                .map(zones ->
                        zoneList.stream()
                                .collect(Collectors.groupingBy(zone -> zone,
                                        Collectors.counting()))
                ).orElseGet(() -> ImmutableMap.of())
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)) .keySet()
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
