package com.bookMovies.tickets.services;

import com.bookMovies.tickets.entity.Screen;
import com.bookMovies.tickets.entity.Theatre;
import com.bookMovies.tickets.repositories.ScreenRepository;
import com.bookMovies.tickets.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public Screen createScreen(Screen screen) {

        Long theatreId = screen.getTheatre().getId();

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        screen.setTheatre(theatre);

        return screenRepository.save(screen);
    }
}
