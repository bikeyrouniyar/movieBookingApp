package com.bookMovies.tickets.controller;

import com.bookMovies.tickets.entity.Show;
import com.bookMovies.tickets.repositories.ShowRepository;
import com.bookMovies.tickets.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowService showService;

    @PostMapping
    public Show createShow(@RequestBody Show show) {
        return showService.createShow(show);
    }
}
