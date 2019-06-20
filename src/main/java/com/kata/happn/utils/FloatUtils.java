package com.kata.happn.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Optional;

import static com.kata.happn.Constants.INCREMENTZONES;

public class FloatUtils {



    public static Float retrieveMinLimit(Float number) {
        String strNumber = String.valueOf(number);
        String numberAfterComma = strNumber.substring(strNumber.indexOf('.') + 1);
        DecimalFormat df = new DecimalFormat("###");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String format = df.format(Double.valueOf(number));
        return Optional.of(numberAfterComma)
                .filter(s -> Integer.valueOf(s) >= 5 && number < 0
                        || (Integer.valueOf(s) < 5 && number >= 0))
                .map(s -> Float.valueOf(format))
                .orElseGet(() -> Float.valueOf(format) - INCREMENTZONES);

    }

    public static Float retrieveMaxLimit(Float number) {
        String strNumber = String.valueOf(number);
        String numberAfterComma = strNumber.substring(strNumber.indexOf('.') + 1);
        DecimalFormat df = new DecimalFormat("###");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String format = df.format(Double.valueOf(number));
        return Optional.of(numberAfterComma)
                .filter(s -> Integer.valueOf(s) >= 5 && number < 0
                        || (Integer.valueOf(s) < 5 && number >= 0))
                .map(s -> Float.valueOf(format) + INCREMENTZONES)
                .orElseGet(() -> Float.valueOf(format));
    }
}
