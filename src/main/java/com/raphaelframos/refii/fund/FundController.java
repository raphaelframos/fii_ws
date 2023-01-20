package com.raphaelframos.refii.fund;

import com.raphaelframos.refii.common.model.DetailFund;
import com.raphaelframos.refii.common.service.FundService;
import com.raphaelframos.refii.scrap.data.DetailFundDTO;
import com.raphaelframos.refii.scrap.data.FundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<FundDTO> fund(){
        return service.findAll();
    }


    @RequestMapping("/{id}")
    public ResponseEntity<DetailFund> findFund(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @RequestMapping("/all")
    public List<FundDTO> all(){
        return service.findAll();
    }

}
