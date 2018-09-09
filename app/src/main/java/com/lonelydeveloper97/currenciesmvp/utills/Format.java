package com.lonelydeveloper97.currenciesmvp.utills;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Format {
    public final static DecimalFormat PRICE_FORMAT = new DecimalFormat("#.######");
    static {
        PRICE_FORMAT.setRoundingMode(RoundingMode.HALF_UP);
    }
}
