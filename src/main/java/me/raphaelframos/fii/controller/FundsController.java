package me.raphaelframos.fii.controller;

import me.raphaelframos.fii.data.DetailFundDTO;
import me.raphaelframos.fii.data.FundRankingDTO;
import me.raphaelframos.fii.data.FundsDTO;
import me.raphaelframos.fii.service.FundsExplorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<ArrayList<FundRankingDTO>> ranking(){
        return ResponseEntity.ok(fundsExplorerService.ranking());
    }

    @RequestMapping("/ranking/")
    public ResponseEntity<ArrayList<FundRankingDTO>> ranking(@RequestParam("type") String type, @RequestParam("category") String category){
        return ResponseEntity.ok(fundsExplorerService.ranking(type, category));
    }
}
