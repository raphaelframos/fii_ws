package com.raphaelframos.refii.fii;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("refii/fiis")
public class FiiController {

    @RequestMapping("/{id}")
    public void fii(@PathVariable("id") Long id){

    }
}
