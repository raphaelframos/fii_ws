package com.raphaelframos.refii.fund;

import com.raphaelframos.refii.common.service.FundService;
import com.raphaelframos.refii.scrap.data.FundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("refii/funds")
public class FundController {

    @Autowired
    private FundService service;

    public FundController(FundService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public List<FundResponse> fund(){
        return Collections.emptyList();
    }
    @RequestMapping("/all")
    public List<FundDTO> all(){
        return service.findAll();
    }

}
