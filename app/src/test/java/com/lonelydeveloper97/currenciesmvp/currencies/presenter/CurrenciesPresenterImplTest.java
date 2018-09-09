package com.lonelydeveloper97.currenciesmvp.currencies.presenter;

import com.lonelydeveloper97.currenciesmvp.currencies.CurrenciesContract;
import com.lonelydeveloper97.currenciesmvp.currencies.TestUtills;
import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.model.impl.CurrenciesModelImpl;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import io.reactivex.Flowable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrenciesPresenterImplTest {

    @Mock
    private
    CurrenciesContract.View view;
    @Mock
    private
    CurrenciesContract.View.CurrencyDataListener currencyDataListener;

    @Before
    public void init(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler((a) -> new TestScheduler());
    }

    @Test
    public void updateBase() {
        BaseCurrency eur = new BaseCurrency("EUR", 100);
        BaseCurrency usd = new BaseCurrency("USD", 100);

        CurrenciesResponse mock1 = TestUtills.createMock("1", Collections.singletonMap("AAA", 200d));
        CurrenciesModelImpl model = new CurrenciesModelImpl(eur, baseCurrency -> {
            if (baseCurrency == eur) {
                return Flowable.just(mock1);
            } else if (baseCurrency == usd) {
                return Flowable.just(mock1);
            }
            throw new AssertionError("not valid base set");
        });
        CurrenciesContract.Presenter presenter = new CurrenciesPresenterImpl(model);

        when(view.getDataListener()).thenReturn(currencyDataListener);

        doAnswer(invocation -> {
            Assert.assertEquals(eur, invocation.getArgument(0));
            return null;
        }).when(currencyDataListener).updateBaseExternally(any(BaseCurrency.class));
        presenter.subscribe(view);

        doAnswer(invocation -> {
            Assert.assertEquals(usd, invocation.getArgument(0));
            return null;
        }).when(currencyDataListener).updateBaseExternally(any(BaseCurrency.class));
        presenter.updateBase(usd);

        Assert.assertEquals(model.base(), usd);
    }
}