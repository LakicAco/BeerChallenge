package com.aco.beerchallenge.repository;

import com.aco.beerchallenge.model.BeerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class  BeerRepositoryImpl implements BeerRepositoryApi {

    private BeerJpaRepository beerJpaRepository;

    @Autowired
    public BeerRepositoryImpl(BeerJpaRepository beerJpaRepository){
        this.beerJpaRepository = beerJpaRepository;
    }


    public BeerEntity save(final BeerEntity beerEntity){
        return beerJpaRepository.save(beerEntity);
    };

    public void delete(BeerEntity beerEntity){
        beerJpaRepository.delete(beerEntity);
    }

    public void deleteById(Long id){
        beerJpaRepository.deleteById(id);
    }

    @Override
    public Optional<BeerEntity> findById(Long id) {
        return beerJpaRepository.findById(id);
    }

    public List<BeerEntity> findAll(){
        return beerJpaRepository.findAll();
    }

    @Override
    public void saveAll(List<BeerEntity> beers) {
        beerJpaRepository.saveAll(beers);

    }

}
