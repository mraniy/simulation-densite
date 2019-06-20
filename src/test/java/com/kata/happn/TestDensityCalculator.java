package com.kata.happn;

import com.kata.happn.model.Zone;
import com.kata.happn.service.DensityCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDensityCalculator {

    @Autowired
    private DensityCalculator densityCalculator;


    private Zone zone1;

    private Zone zone2;

    private Zone zone3;

    @Before
    public void setUp() {
         zone1 =  new Zone(8F,8.5F, 89F, 89.5F);
         zone2 =  new Zone(-45.5F,-45F, 2F, 2.5F);
         zone3 =  new Zone(-21F,21.5F, -76F, 75.5F);
    }

    @Test
    public void should_return_empty_map_when_list_of_zones_is_empty() {
        // given
        List<Zone> zones = null;
        // when
        List<Zone> zoneList = densityCalculator.process(zones, 2L);
        // then
        assertThat(zoneList.size() , is(0));
    }

    @Test
    public void should_return_correct_map_when_list_of_zone_is_not_empty() {
        // given
        Zone zone1occurence1 = new Zone(zone1);
        Zone zone1occurence2 = new Zone(zone1);
        Zone zone1occurence3 = new Zone(zone1);
        Zone zone1occurence4 = new Zone(zone1);
        Zone zone2occurence1 = new Zone(zone2);
        Zone zone2occurence2 = new Zone(zone2);
        Zone zone2occurence3 =  new Zone(zone2);
        Zone zone3occurence1 =  new Zone(zone3);
        Zone zone3occurence2 =  new Zone(zone3);
        List<Zone> zones = Arrays.asList(zone1occurence1, zone1occurence2, zone1occurence3,
                zone1occurence4, zone2occurence1, zone2occurence2, zone2occurence3, zone3occurence1, zone3occurence2);
        Long limit = 2L;
        // when

        List<Zone> zoneList = densityCalculator.process(zones, limit);
        // then
        assertThat(zoneList.size(), is(limit.intValue()));
        assertThat(zoneList.get(0), is(zone1));
        assertThat(zoneList.get(1), is(zone2));

    }
}
