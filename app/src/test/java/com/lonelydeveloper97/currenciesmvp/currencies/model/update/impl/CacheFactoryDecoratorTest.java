package com.lonelydeveloper97.currenciesmvp.currencies.model.update.impl;

import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesResponse;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.impl.CacheFactoryDecorator;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;

import static com.lonelydeveloper97.currenciesmvp.currencies.TestUtills.createMock;


public class CacheFactoryDecoratorTest {

    @Test
    public void simpleTest() {
        CacheFactoryDecorator currenciesDataSupplier = new CacheFactoryDecorator(base -> Flowable.just(createMock(base.getName())), createMock("1"));
        currenciesDataSupplier.create(new BaseCurrency("EUR", 100)).test().assertResult(createMock("EUR"));
    }

    @Test
    public void testException(){
        CacheFactoryDecorator currenciesDataSupplier2 = new CacheFactoryDecorator(base -> Flowable.error(new Exception()), createMock("1"));
        String baseName = currenciesDataSupplier2.create(new BaseCurrency("EUR", 100)).test().values().get(0).getBase();
        Assert.assertEquals("EUR", baseName);
    }

    @Test
    public void testConvert(){
        HashMap<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.5);
        CurrenciesResponse mock = createMock("1", rates);
        CacheFactoryDecorator currenciesDataSupplier = new CacheFactoryDecorator(base -> Flowable.error(new Exception()), mock);
        Map<String, Double> ratesResult = currenciesDataSupplier.create(new BaseCurrency("EUR", 100)).test().values().get(0).getRates();
        Assert.assertEquals(2.0, ratesResult.get("1"), 0);
        Assert.assertEquals(1, ratesResult.size());
    }


}