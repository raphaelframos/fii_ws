package me.raphaelframos.fii.controller;

import me.raphaelframos.fii.data.InsightDTO;
import me.raphaelframos.fii.service.InsightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("insights")
public class InsightController {

    @Autowired
    private InsightsService service;

    @RequestMapping("/{id}")
    public InsightDTO make(@PathVariable("id") Long id){
        InsightDTO insightDTO = service.makeBy(id);
        return insightDTO;
    }
}
