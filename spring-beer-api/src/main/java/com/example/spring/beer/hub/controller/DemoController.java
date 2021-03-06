package com.example.spring.beer.hub.controller;

import com.example.spring.beer.hub.entity.Buster;
import com.example.spring.beer.hub.entity.Order;
import com.example.spring.beer.hub.service.BarService;
import com.example.spring.beer.hub.service.BusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final BusterService busterService;
    private final BarService barService;

    //TODO Sorry for that but it is only for demo purpose,
    // you should not use GET method for data modification requests for sure
    @GetMapping("/iwantbeer/{name}/{money}")
    public ResponseEntity getCompany(@PathVariable("name") String name,
                                     @PathVariable("money") String money) {
        try {
            int moneyValue = Integer.parseInt(money);
            if(moneyValue < 1 || moneyValue > 5000) {
                throw new BeerException();
            }
            barService.makeOrder(Order.builder()
                    .money(moneyValue)
                    .name(name)
                    .build());
            return ResponseEntity.ok("Wait for it buddy. :)" +
                    " \nWe will find a company and a place for saturday's evening." +
                    " \nLook on the big screen");
        } catch (Exception e) {
            return ResponseEntity.ok("Hey! Do you wanna drink or not?\n" +
                    " Do not use strings, negative numbers or numbers bigger than 5000 for name, please");
        }

    }

    @GetMapping("/iwantbeer")
    public ResponseEntity helpMe() {
        return ResponseEntity.ok("Please go to http://buster.igg.biz/iwantbeer/{NICKNAME}/{MONEY_AMOUNT}");
    }

    @GetMapping("buster")
    public Buster chooseBuster() {
        return busterService.create();
    }
}
