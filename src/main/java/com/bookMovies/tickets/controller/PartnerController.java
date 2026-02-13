package com.bookMovies.tickets.controller;

import com.bookMovies.tickets.entity.TheatrePartner;
import com.bookMovies.tickets.repositories.TheatrePartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    private TheatrePartnerRepository repository;

    @PostMapping
    public TheatrePartner register(@RequestBody TheatrePartner partner) {
        partner.setActive(true);
        return repository.save(partner);
    }
}
