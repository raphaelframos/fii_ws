package com.raphaelframos.refii.insights.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("refii/insights")
public class InsightsController {

    @RequestMapping("/today")
    public String today(){
        return "Olá! Possuir mais de 10 FIIs pode ser uma boa estratégia!";
    }
}
