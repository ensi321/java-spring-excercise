package org.example;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.example.model.Price;
import org.example.repository.PriceRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class PriceController {

    @Autowired
    PriceRepository priceRepository;

    @GetMapping("/")
    public String index() {
        return "Working";
    }

    @GetMapping("/all-prices")
    public List<Price> getAllPrices(){
        return priceRepository.findAll();
    }

    @GetMapping(value = "/price", params = {"ticker", "startdate"})
    public List<Price> getPrice(@RequestParam("ticker") String ticker,
                                @RequestParam("startdate") String startDate,
                                @RequestParam(value = "enddate", required = false) String endDate)
            throws IllegalArgumentException{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedStartDate;
        Date convertedEndDate;

        try {
            convertedStartDate = formatter.parse(startDate);
            if (endDate == null){
                convertedEndDate = new Date();
            }
            else {
                convertedEndDate = formatter.parse(endDate);
            }
        }
        catch (ParseException e){
            throw new IllegalArgumentException(
                    String.format("Invalid date format! Expecting yyyy-MM-dd Given: %s, %s", startDate, endDate));
        }

        return priceRepository.filterPrice(ticker, convertedStartDate, convertedEndDate);
    }


    @PostMapping(value = "/insert-price", consumes = "application/json")
    public List<Price> insertPrice(@RequestBody List<Price> newPrices){
        for (Price newPrice : newPrices){
            Price matchingPrice = priceRepository.getPriceByTickerAndDate(newPrice.getTicker(), newPrice.getDate());

            if (matchingPrice == null){
                priceRepository.save(newPrice);
            }
            else{
                matchingPrice.setOpen(newPrice.getOpen());
                matchingPrice.setHigh(newPrice.getHigh());
                matchingPrice.setLow(newPrice.getLow());
                matchingPrice.setClose(newPrice.getClose());
                matchingPrice.setAdjClose(newPrice.getAdjClose());
                matchingPrice.setVolume(newPrice.getVolume());
                priceRepository.save(matchingPrice);
            }

        }
        return newPrices;
    }

    @DeleteMapping("/delete-symbol")
    public String deleteTicker(@RequestParam("ticker") String ticker){
        Integer count =  priceRepository.deletePricesByTicker(ticker);
        String response = String.format("Successfully deleted %d records.", count);

        return response;
    }




}