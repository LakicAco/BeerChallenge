package com.aco.beerchallenge.repository;

import com.aco.beerchallenge.model.BeerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public interface BeerRepositoryApi {

    BeerEntity save(BeerEntity beerEntity);

    void delete(BeerEntity beerEntity);

    void deleteById(Long id);

    Optional<BeerEntity> findById(Long id);

    List<BeerEntity> findAll();

    void saveAll(List<BeerEntity> beers);

}
