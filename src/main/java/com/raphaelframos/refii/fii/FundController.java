package com.raphaelframos.refii.fii;

import com.raphaelframos.refii.common.model.Option;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("refii/funds")
public class FundController {

    @RequestMapping("/")
    public List<FundResponse> fund(){
        List<FundResponse> funds = new ArrayList<>();
        FundResponse fund = new FundResponse();
        fund.setId(1L);
        fund.setName("MXRF11");
        fund.setSegment("Papel");
        fund.setOption1(new Option("R$ 100,00", "Preço"));
        fund.setOption2(new Option("12%", "Dy"));
        fund.setOption3(new Option("R$ 10,00", "Dividendo"));
        fund.setOption4(new Option("1", "P/VP"));
        funds.add(fund);
        funds.add(fund);
        funds.add(fund);
        funds.add(fund);
        funds.add(fund);
        return funds;
    }
}
