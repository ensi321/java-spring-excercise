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

        return priceRepository.filerPrice(ticker, convertedStartDate, convertedEndDate);
    }


    @PostMapping("/insert-price")
    public Boolean insertPrice() throws Exception{
        throw new Exception("hihi");
    }

    @DeleteMapping("/delete-symbol")
    public String deleteTicker(@RequestParam("ticker") String ticker){
        Long count =  priceRepository.deletePricesByTicker(ticker);
        String response = String.format("Successfully deleted %d records.", count.intValue());

        return response;
    }




}