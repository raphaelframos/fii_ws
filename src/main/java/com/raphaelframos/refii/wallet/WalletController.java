package com.raphaelframos.refii.wallet;

import com.raphaelframos.refii.wallet.data.BalanceResponse;
import com.raphaelframos.refii.wallet.data.FundResponse;
import com.raphaelframos.refii.wallet.data.WalletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("refii/wallets")
public class WalletController {

    @Autowired
    private WalletService service;

    @RequestMapping("/")
    public ResponseEntity<List<FundResponse>> myFiis(@PathParam("id") Long id){
        return ResponseEntity.ok(service.findBy(id));
    }

    @RequestMapping("/balance/")
    public ResponseEntity<BalanceResponse> balance(@PathParam("id") Long id){
        return ResponseEntity.ok(service.balance(id));
    }

    @RequestMapping("/historic/{userId}")
    public ResponseEntity<List<WalletResponse>> findHistoric(
            @PathVariable("userId") Long userId,
            @PathParam("fundId") Long fundId){
        return ResponseEntity.ok(service.historic(fundId, userId));
    }
}
