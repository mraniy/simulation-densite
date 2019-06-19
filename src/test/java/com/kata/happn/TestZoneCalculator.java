package com.kata.happn;

import com.kata.happn.model.Point;
import com.kata.happn.model.Zone;
import com.kata.happn.service.ZoneCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestZoneCalculator {

    @Autowired
    private ZoneCalculator zoneCalculator;

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_illegal_argument_exception_when_longitude_of_point_is_null() {
        // given
        Point point = null;
        // when
        zoneCalculator.calculateZoneBelongingToPoint(point);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_illegal_argument_exception_when_longitude_of_point_is_not_according_to_limits() {
        // given
        Point point = new Point("id1", -2.4F,181F);
        // when
        zoneCalculator.calculateZoneBelongingToPoint(point);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_illegal_argument_exception_when_latitude_of_point_is_not_according_to_limits() {
        // given
        Point point = new Point("id1", 91F,46F);
        // when
        zoneCalculator.calculateZoneBelongingToPoint(point);
    }

    @Test
    public void should_return_correct_zone_when_point_is_according() {
        // given
        Point point = new Point("id1", -2.4F,48.9F);
        // when
        Zone zone = zoneCalculator.calculateZoneBelongingToPoint(point);
        // then
        assertThat(zone.getMinLatitude() , is(-2.5F));
        assertThat(zone.getMaxLatitude() , is(-2F));
        assertThat(zone.getMinLongitude() , is(48.5F));
        assertThat(zone.getMaxLongitude(), is(49F));
    }
}
