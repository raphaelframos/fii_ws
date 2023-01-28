package com.raphaelframos.refii.wallet;

import com.raphaelframos.refii.common.entity.FundWalletEntity;
import com.raphaelframos.refii.common.model.Option;
import com.raphaelframos.refii.wallet.data.BalanceResponse;
import com.raphaelframos.refii.wallet.data.FundResponse;
import com.raphaelframos.refii.wallet.repository.FundWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
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
}
