package com.aco.beerchallenge.controller;

import com.aco.beerchallenge.model.dto.BeerDto;
import com.aco.beerchallenge.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beerChallenge")
public class BeerRestApiController {
    private final  BeerService beerService;

    @Autowired
    public BeerRestApiController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(value = "/beers/{id}", produces = {"application/json"})
    public ResponseEntity<BeerDto> findById(
            @PathVariable("id") Long id){
        BeerDto beerDto = beerService.findById(id);
        return new ResponseEntity<>(beerDto, HttpStatus.OK);

    }

    @GetMapping(value = "/beers", produces = {"application/json"})
    public ResponseEntity<List<BeerDto>> findAll(){
        List<BeerDto>  beers = beerService.findAll();
        return new ResponseEntity<>(beers, HttpStatus.OK);

    }

    @DeleteMapping(value = "/beers/{id}", produces = {"application/json"})
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id){
        beerService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "beers/init", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> init(){
        beerService.init();

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
