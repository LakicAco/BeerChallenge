package com.aco.beerchallenge.junit;

import com.aco.beerchallenge.service.BeerService;
import com.aco.beerchallenge.service.mapping.BeerJson;
import com.aco.beerchallenge.service.mapping.MashTempJson;
import com.aco.beerchallenge.service.mapping.MethodJson;
import com.aco.beerchallenge.service.mapping.TempJson;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest(properties = "spring.profiles.active=test")
@PropertySource(value = "classpath:application-test.yml")
public class BeerServiceTest {
    @Autowired
    private BeerService underTest;
    private MethodJson mockedMethod;
    private BeerJson mockedBeer;

    @BeforeEach
    public void setUp() {
        mockedBeer = new BeerJson();

        mockedMethod = new MethodJson();

        mockedBeer.setMethodJson(mockedMethod);
    }

    @Test
    public void getOneTempAverage_Success() {

        TempJson mockedTemp = new TempJson();
        mockedTemp.setUnit("celsius");
        mockedTemp.setValue(65.0);


        MashTempJson mockedMashTemp  = new MashTempJson();
        mockedMashTemp.setDuration(75L);

        mockedMashTemp.setTempJson(mockedTemp);
        mockedMethod.setMashTemp(Collections.singletonList(mockedMashTemp));
        mockedBeer.setMethodJson(mockedMethod);

        Double average = underTest.getAverageTemp(mockedBeer.getMethodJson().getMashTemp());

        assertThat(average, is(notNullValue()));
        assertThat(average, is(65.0));

    }

    @Test
    public void getThreeTempAverage_Success() {

        TempJson mockedTemp1 = new TempJson();
        mockedTemp1.setUnit("celsius");
        mockedTemp1.setValue(45.0);

        TempJson mockedTemp2 = new TempJson();
        mockedTemp2.setUnit("celsius");
        mockedTemp2.setValue(25.0);

        TempJson mockedTemp3 = new TempJson();
        mockedTemp3.setUnit("celsius");
        mockedTemp3.setValue(20.0);

        MashTempJson mockedMashTemp1  = new MashTempJson();
        mockedMashTemp1.setDuration(75L);
        mockedMashTemp1.setTempJson(mockedTemp1);
        MashTempJson mockedMashTemp2  = new MashTempJson();
        mockedMashTemp2.setDuration(85L);
        mockedMashTemp2.setTempJson(mockedTemp2);
        MashTempJson mockedMashTemp3  = new MashTempJson();
        mockedMashTemp3.setDuration(75L);
        mockedMashTemp3.setTempJson(mockedTemp3);

        List<MashTempJson> mockedMashTemps = new ArrayList<>();
        mockedMashTemps.add(mockedMashTemp1);
        mockedMashTemps.add(mockedMashTemp2);
        mockedMashTemps.add(mockedMashTemp3);

        mockedMethod.setMashTemp(mockedMashTemps);
        mockedBeer.setMethodJson(mockedMethod);

        Double average = underTest.getAverageTemp(mockedBeer.getMethodJson().getMashTemp());

        assertThat(average, is(notNullValue()));
        assertThat(average, is(30.0));

    }
}
