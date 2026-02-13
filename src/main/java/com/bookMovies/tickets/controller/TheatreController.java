package com.bookMovies.tickets.controller;

import com.bookMovies.tickets.entity.Theatre;
import com.bookMovies.tickets.repositories.TheatreRepository;
import com.bookMovies.tickets.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @Autowired
    private TheatreRepository theatreRepository;

    @PostMapping
    public Theatre createTheatre(@RequestBody Theatre theatre) {
        return theatreService.createTheatre(theatre);
    }

    @GetMapping
    public List<Theatre> getAll() {
        return theatreRepository.findAll();
    }

    @GetMapping("/{id}")
    public Theatre getById(@PathVariable Long id) {
        return theatreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        theatreRepository.deleteById(id);
        return "Theatre deleted successfully";
    }
}
