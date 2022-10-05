package com.raphaelframos.refii.profile;

import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    public Profile findBy(Long id) {
        return new Profile();
    }
}
