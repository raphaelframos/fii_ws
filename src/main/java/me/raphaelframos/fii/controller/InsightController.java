package me.raphaelframos.fii.controller;

import me.raphaelframos.fii.data.InsightDTO;
import me.raphaelframos.fii.insights.InsightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("refii/insights")
public class InsightController {

    @Autowired
    private InsightsService service;

    @RequestMapping("/today")
    public String today(@PathParam("id") Long id){
        return service.today();
    }

    @RequestMapping("/")
    public InsightDTO make(@PathParam("id") Long id){
        InsightDTO insightDTO = service.makeBy(id);
        return insightDTO;
    }
}
