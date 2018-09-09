package com.lonelydeveloper97.currenciesmvp.utills;

import org.junit.Assert;
import org.junit.Test;

public class FormatTest {

    @Test
    public void testPriceFormat(){
        double manyZero = 1.000000000002d;
        Assert.assertEquals("1", Format.PRICE_FORMAT.format(manyZero));

        double halfUp = 1.0000005;
        Assert.assertEquals("1.000001", Format.PRICE_FORMAT.format(halfUp));

        double down = 1.0000004;
        Assert.assertEquals("1", Format.PRICE_FORMAT.format(down));
    }
}