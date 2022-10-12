package com.aco.beerchallenge.service;

import com.aco.beerchallenge.exception.BeerNotFoundException;
import com.aco.beerchallenge.exception.GeneralServerError;
import com.aco.beerchallenge.exception.NoBeersException;
import com.aco.beerchallenge.model.TemperatureUnit;
import com.aco.beerchallenge.model.dto.BeerDto;
import com.aco.beerchallenge.model.BeerEntity;
import com.aco.beerchallenge.model.BeerTemperatureEntity;
import com.aco.beerchallenge.repository.BeerRepositoryApi;
import com.aco.beerchallenge.service.mapping.BeerJson;
import com.aco.beerchallenge.service.mapping.MashTempJson;
import com.aco.beerchallenge.service.mapping.TempJson;
import com.aco.beerchallenge.util.LogEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BeerService {

    private static final Logger LOG = LoggerFactory.getLogger(BeerService.class);

    private final static String GENERATE_BEER_URL = "https://api.punkapi.com/v2/beers/random";

    private final BeerRepositoryApi beerRepository;

    public BeerService( BeerRepositoryApi beerRepository){
        this.beerRepository = beerRepository;
    }

    private  BeerDto getBeerDto(BeerEntity beer) {
        BeerDto beerDto = new BeerDto();
        beerDto.setInternalId(beer.getInternalId());
        beerDto.setName(beer.getName());
        beerDto.setDescription(beer.getDescription());
        beerDto.setMeanValueTemp(beer.getMeanValueTemp());
        return beerDto;
    }

    @LogEvent
    public BeerDto findById(Long id){
        Optional<BeerEntity> beer = beerRepository.findById(id);
        if (beer.isPresent()) {
            return getBeerDto(beer.get());
        }
        else
        {
            throw new BeerNotFoundException(BeerJson.class, MessageFormat.format(getResourceBundle().getString("noBeerForId"), id));
        }
    }

    @LogEvent
    public void delete(Long id){
        try {
            beerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new BeerNotFoundException(BeerJson.class, MessageFormat.format(getResourceBundle().getString("noBeerForId"), id));
        }
    }


    @LogEvent
    public List<BeerDto> findAll() {
        List<BeerEntity> beers = beerRepository.findAll();
        if (beers.isEmpty()) {
            throw new NoBeersException(BeerJson.class, getResourceBundle().getString("noBeerAtAll"));
        } else {
            return beers.stream().map(this::getBeerDto).collect(Collectors.toList());
        }
    }
    @LogEvent
    public void init(){

        List<BeerEntity> beers = new ArrayList<>();
        int beerCount = beerRepository.findAll().size();
        for(int i=0; i< 10-beerCount; i++){
            List<BeerTemperatureEntity> beerTemperatures = new ArrayList<>();
            BeerJson beerJson = getRandomBeer()[0];
            BeerEntity beerEntity = mapBeer(beerJson);
            for(MashTempJson mashTemp: beerJson.getMethodJson().getMashTemp()){
                TempJson temp = mashTemp.getTempJson();

                BeerTemperatureEntity beerTempEntity = mapBeerTemperature(temp);
                beerTempEntity.setDuration(mashTemp.getDuration());
                beerTempEntity.setBeer(beerEntity);
                beerTemperatures.add(beerTempEntity);
            }
            beerEntity.setTemperatureEntityList(beerTemperatures);
            beers.add(beerEntity);
        }
        beerRepository.saveAll(beers);

    }

    private BeerJson[] getRandomBeer() {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new URL(GENERATE_BEER_URL), BeerJson[].class);
        } catch (
                IOException e) {
            LOG.error("Something went wrong while requesting beer", e);
            throw new GeneralServerError(e.getMessage(), BeerService.class);
        }
    }


    private ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle("message", LocaleContextHolder.getLocale());
    }
    private BeerEntity mapBeer(BeerJson beerJson){
        BeerEntity beerEntity = new BeerEntity();
        beerEntity.setInternalId(beerJson.getInternalId());
        beerEntity.setName(beerJson.getName());
        beerEntity.setDescription(beerJson.getDescription());
        beerEntity.setMeanValueTemp(getAverageTemp(beerJson.getMethodJson().getMashTemp()));
        return beerEntity;
    }

    private BeerTemperatureEntity mapBeerTemperature(TempJson tempJson){
        BeerTemperatureEntity beerTemperatureEntity = new BeerTemperatureEntity();
        beerTemperatureEntity.setUnit(TemperatureUnit.toEnum(tempJson.getUnit()));
        beerTemperatureEntity.setValue(tempJson.getValue());
        beerTemperatureEntity.setDuration(tempJson.getDuration());
        return beerTemperatureEntity;
    }

    public Double getAverageTemp(List<MashTempJson> list){

        List<Double> tempValueList = list.stream().map(x->x.getTempJson().getValue()).collect(Collectors.toList());
        DoubleSummaryStatistics iss = tempValueList.stream().mapToDouble((a) -> a).summaryStatistics();
        return iss.getAverage();
    }

}
