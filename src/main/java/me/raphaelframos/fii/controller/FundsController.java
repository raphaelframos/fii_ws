package me.raphaelframos.fii.controller;

import me.raphaelframos.fii.data.*;
import me.raphaelframos.fii.service.FundsExplorerService;
import me.raphaelframos.fii.utils.LogUtils;
import me.raphaelframos.fii.utils.SoupUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController
@RequestMapping("funds")
public class FundsController {

    @Autowired
    private FundsExplorerService fundsExplorerService;

    public FundsController(FundsExplorerService fundsExplorerService) {
        this.fundsExplorerService = fundsExplorerService;
    }

    @RequestMapping("/")
    public ResponseEntity<FundsDTO> funds(){
        FundsDTO fundsDTO = fundsExplorerService.listFunds();
        return ResponseEntity.ok(fundsDTO);
    }

    @RequestMapping("/details")
    public ResponseEntity<DetailFundDTO> details(@PathParam("href") String href){
        return ResponseEntity.ok(fundsExplorerService.details(href));
    }

    @RequestMapping("/ranking")
    public ResponseEntity<ArrayList<FundRankingDTO>> details(){
        return ResponseEntity.ok(fundsExplorerService.ranking());
    }
}
