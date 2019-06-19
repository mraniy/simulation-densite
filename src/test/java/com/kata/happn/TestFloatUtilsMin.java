package com.kata.happn;
import com.kata.happn.utils.FloatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class TestFloatUtilsMin {

    @Parameter(value = 0)
    public Float numberA;


    @Parameter(value = 1)
    public Float expected;

    @Parameters(name = "{index}: testGetMin({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {-23.4F, -23.5F},
                {-23.7F, -24F},
                {36.7F, 36.5F},
                {-8.9F, -9F},
                {79.3F, 79F},
                {60.2F, 60F}
        });
    }

    @Test
    public void should_return_correct_min_value() {
        assertThat(FloatUtils.retrieveMinLimit(numberA), is(expected));
    }

}