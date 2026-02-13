package com.bookMovies.tickets.controller;

import com.bookMovies.tickets.entity.Show;
import com.bookMovies.tickets.repositories.ShowRepository;
import com.bookMovies.tickets.services.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    private ShowRepository showRepository;

    @GetMapping
    public List<Show> browse(@RequestParam String city,
                             @RequestParam LocalDate date) {

        return showRepository.findAll().stream()
                .filter(s -> s.getScreen().getTheatre().getCity().equals(city)
                        && s.getShowDate().equals(date))
                .toList();
    }
}

