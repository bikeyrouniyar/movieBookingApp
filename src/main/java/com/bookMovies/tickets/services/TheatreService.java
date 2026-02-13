package com.bookMovies.tickets.services;

import com.bookMovies.tickets.entity.Theatre;
import com.bookMovies.tickets.entity.TheatrePartner;
import com.bookMovies.tickets.repositories.TheatrePartnerRepository;
import com.bookMovies.tickets.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TheatreService {

    @Autowired
    private TheatrePartnerRepository partnerRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public Theatre createTheatre(@RequestBody Theatre theatre) {

        Long partnerId = theatre.getPartner().getId();

        TheatrePartner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        theatre.setPartner(partner);

        return theatreRepository.save(theatre);
    }

}
