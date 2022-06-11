package me.raphaelframos.fii.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("funds")
public class FundsController {

    @RequestMapping("/")
    public void scrapping(){
    }
}
