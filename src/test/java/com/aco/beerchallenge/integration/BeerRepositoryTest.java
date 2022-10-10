package com.aco.beerchallenge.integration;

import com.aco.beerchallenge.model.BeerEntity;
import com.aco.beerchallenge.repository.BeerRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;

@SpringBootTest(properties = "spring.profiles.active=test")
@PropertySource(value = "classpath:application-test.yml")
public class BeerRepositoryTest {
    @Autowired
    private BeerRepositoryImpl underTest;

    @BeforeEach
    public void setUp() {
        BeerEntity beerBg = new BeerEntity();


        beerBg.setName("BG pivo");
        beerBg.setDescription(("Beogradsko pivo"));
        beerBg.setInternalId(77L);
        beerBg.setMeanValueTemp(87.0);

        BeerEntity beerVa = new BeerEntity();
        beerVa.setName("Valjevsko");
        beerVa.setDescription(("Valjevsko pivo"));
        beerVa.setInternalId(75L);
        beerVa.setMeanValueTemp(85.0);

        BeerEntity beerZr = new BeerEntity();
        beerZr.setName("Zajecarsko");
        beerZr.setDescription(("Zajecarsko pivo"));
        beerZr.setInternalId(79L);
        beerZr.setMeanValueTemp(82.0);

        List<BeerEntity> beers = new ArrayList<>();
        beers.add(beerBg);
        beers.add(beerVa);
        beers.add(beerZr);

        underTest.saveAll(beers);

    }

    @AfterEach
    public void deleteAll() {

        List<BeerEntity> beers = underTest.findAll();
        for (BeerEntity beer : beers) {
            underTest.deleteById(beer.getInternalId());
        }
    }

    @Test
    public void getOneBeerFindById() {
        Optional<BeerEntity> beer = underTest.findById(77L);

        assertThat(beer.get().getName(), is("BG pivo"));
    }

    @Test
    public void getAllBeers() {
        List<BeerEntity> beer = underTest.findAll();

        assertThat(beer.size(), is(3));
    }

    @Test
    public void deleteOneBeerById() {
        Optional<BeerEntity> beer = underTest.findById(77L);

        assertThat(beer.get().getName(), is("BG pivo"));

        underTest.deleteById(77L);

        beer = underTest.findById(77L);

        assertFalse(beer.isPresent());
    }
}
