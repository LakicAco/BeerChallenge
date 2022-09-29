package com.aco.beerchallenge.repository;

import com.aco.beerchallenge.model.BeerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public interface BeerJpaRepository extends JpaRepository<BeerEntity, Long> {

}
