package com.bookMovies.tickets.services;

import com.bookMovies.tickets.entity.Show;
import com.bookMovies.tickets.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BrowseService {

    @Autowired
    private ShowRepository showRepository;

    public List<Show> browse(String movieName, String city, LocalDate date) {
        return showRepository
                .findShows(movieName, city, date);
    }
}
