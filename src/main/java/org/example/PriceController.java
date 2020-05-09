package org.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
public class PriceController {

    @Autowired
    PriceRepository priceRepository;

    @RequestMapping("/")
    public String index() {
        return "Working";
    }

    @RequestMapping("/prices")
    public List<Price> getAllPrices(){
        return priceRepository.findAll();
    }


}