package com.kata.happn.service;


import com.google.common.collect.ImmutableMap;
import com.kata.happn.model.Zone;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DensityCalculator {

    public Map<Zone, Long> process(List<Zone> zoneList) {
        return Optional.ofNullable(zoneList)
                .filter(zones -> !CollectionUtils.isEmpty(zones))
                .map(zones ->
                    zoneList.stream()
                            .collect(Collectors.groupingBy(zone -> zone,
                                    Collectors.counting()))
                ).orElseGet(() -> ImmutableMap.of());
    }
}
