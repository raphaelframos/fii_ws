package com.raphaelframos.refii.profile;

import com.raphaelframos.refii.common.entity.Profile;
import com.raphaelframos.refii.common.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("refii/profiles/")
public class ProfileController {

    @Autowired
    private ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @RequestMapping("test")
    public String test(){
        return "Teste";
    }

    @RequestMapping("")
    public ResponseEntity<ChatResponse> create(@RequestParam("id") Long id,
            @RequestParam("value") String value, @RequestParam("position") int position){
        ChatResponse chatResponse = service.create(id, value, position);
        return ResponseEntity.ok(chatResponse);
    }

    @RequestMapping("{id}")
    public ResponseEntity<Profile> findBy(@PathVariable("id") Long userId){
        return ResponseEntity.ok(service.findBy(userId).get());
    }
}
