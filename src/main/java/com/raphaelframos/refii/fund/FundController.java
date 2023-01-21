package com.raphaelframos.refii.fund;

import com.raphaelframos.refii.common.model.ChatResponse;
import com.raphaelframos.refii.common.service.FundService;
import com.raphaelframos.refii.fund.model.FundResponse;
import com.raphaelframos.refii.scrap.data.FundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("refii/funds")
public class FundController {

    @Autowired
    private final FundService service;

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

    @RequestMapping("/create")
    public ResponseEntity<ChatResponse> create(@RequestParam("id") Long id,
                                               @RequestParam("message") String value, @RequestParam("position") int position) {
        ChatResponse chatResponse = service.create(id, value, position);
        return ResponseEntity.ok(chatResponse);
    }

    @RequestMapping("/names")
    public List<String> names(){
        return service.names();
    }

}
