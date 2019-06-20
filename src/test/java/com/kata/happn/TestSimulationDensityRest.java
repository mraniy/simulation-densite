package com.kata.happn;


import com.kata.happn.model.Zone;
import com.kata.happn.rest.SimulationDensityRest;
import com.kata.happn.service.DensityFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TestSimulationDensityRest {

    @InjectMocks
    private SimulationDensityRest simulationDensityRest;

    @Mock
    private DensityFacade densityFacade;

   @Test
   public void should_return_internal_error_when_an_exception_is_raised() {
       // given
       String some_exception = "Some Exception";
       when(densityFacade.getDensiestZones(anyString(),anyString())).thenThrow(new IllegalArgumentException(some_exception));
       // when
       ResponseEntity<List<Zone>> responseEntity = simulationDensityRest.getDensiestZonesFromTsvFile("2");
       // then
       assertThat(responseEntity.getStatusCodeValue(), is(500));
   }

    @Test
    public void should_return_ok_response_when_a_correct_response_is_returned() {
        // given
        when(densityFacade.getDensiestZones(anyString(),anyString())).thenReturn(Arrays.asList(new Zone(5.5F, 6F,-43F,-42.5F)));
        // when
        ResponseEntity<List<Zone>> responseEntity = simulationDensityRest.getDensiestZonesFromTsvFile("2");
        // then
        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }


}
