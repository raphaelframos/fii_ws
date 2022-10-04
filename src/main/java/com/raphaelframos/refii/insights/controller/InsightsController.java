package com.raphaelframos.refii.insights.controller;

import com.raphaelframos.refii.insights.service.InsightsService;
import com.raphaelframos.refii.scrap.data.InsightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("refii/insights")
public class InsightsController {

    @Autowired
    private InsightsService service;

    @RequestMapping("/today")
    public ResponseEntity<String> today(@PathParam("id") Long id){
        return ResponseEntity.ok(service.today(id));
    }

    @RequestMapping("/")
    public ResponseEntity<InsightDTO> make(@PathParam("id") Long id){
        InsightDTO insightDTO = service.makeBy(id);
        return ResponseEntity.ok(insightDTO);
    }
}
