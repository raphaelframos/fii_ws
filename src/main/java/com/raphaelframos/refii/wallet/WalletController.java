package com.raphaelframos.refii.wallet;

import com.raphaelframos.refii.common.model.Option;
import com.raphaelframos.refii.wallet.data.BalanceResponse;
import com.raphaelframos.refii.wallet.data.FundResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("refii/wallets")
public class WalletController {

    @RequestMapping("/")
    public ResponseEntity<List<FundResponse>> myFiis(@PathParam("id") Long id){
        FundResponse fundResponse = new FundResponse();
        Option option = new Option();
        option.setValue("1000");
        option.setDescription("Quantidade");
        Option option2 = new Option();
        option2.setValue("R$ 1000,00");
        option2.setDescription("Total");
        Option option3 = new Option();
        option3.setValue("10%");
        option3.setDescription("Carteira");
        fundResponse.setSymbol("MXRF11");
        fundResponse.setSegmented("Híbrido");
        fundResponse.setOption1(option);
        fundResponse.setOption2(option2);
        fundResponse.setOption3(option3);
        return ResponseEntity.ok(Arrays.asList(fundResponse, fundResponse, fundResponse));
    }

    @RequestMapping("/balance/")
    public ResponseEntity<BalanceResponse> balance(@PathParam("id") Long id){
        return ResponseEntity.ok(new BalanceResponse("Saldo", "R$ 10000,00"));
    }
}
