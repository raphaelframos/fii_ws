package com.raphaelframos.refii.insights.controller;

import com.raphaelframos.refii.common.model.Option;
import com.raphaelframos.refii.insights.model.Fii;
import com.raphaelframos.refii.insights.model.Ranking;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("refii/rankings")
public class RankingController {

    @RequestMapping("/")
    public Ranking today(@PathParam("id") Long id){
        Ranking ranking = new Ranking();
        ranking.setTitle("Os top 5 FIIs de dividendos são:");
        Option option = new Option();
        option.setDescription("Dy");
        option.setValue("10%");
        Fii fii = new Fii();
        fii.setId(1L);
        fii.setPosition(1);
        fii.setName("MXRF11");
        fii.setOption(option);
        List<Fii> fiis = Arrays.asList(fii, fii, fii, fii, fii);
        ranking.setFiis(fiis);
        return ranking;
    }
}
