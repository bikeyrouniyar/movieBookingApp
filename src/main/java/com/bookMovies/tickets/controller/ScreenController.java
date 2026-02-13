package com.bookMovies.tickets.controller;

import com.bookMovies.tickets.entity.Screen;
import com.bookMovies.tickets.repositories.ScreenRepository;
import com.bookMovies.tickets.services.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @Autowired
    private ScreenRepository screenRepository;

    @PostMapping
    public Screen create(@RequestBody Screen screen) {
        return screenService.createScreen(screen);
    }

    @GetMapping
    public List<Screen> getAll() {
        return screenRepository.findAll();
    }

    @GetMapping("/{id}")
    public Screen getById(@PathVariable Long id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screen not found"));
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        screenRepository.deleteById(id);
        return "Screen deleted successfully";
    }
}
