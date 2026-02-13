package com.bookMovies.tickets.services;

import com.bookMovies.tickets.entity.*;
import com.bookMovies.tickets.repositories.MovieRepository;
import com.bookMovies.tickets.repositories.ScreenRepository;
import com.bookMovies.tickets.repositories.SeatRepository;
import com.bookMovies.tickets.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    public Show createShow(Show show) {

        // Fetch full Movie
        Movie movie = movieRepository.findById(show.getMovie().getId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Fetch full Screen
        Screen screen = screenRepository.findById(show.getScreen().getId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        show.setMovie(movie);
        show.setScreen(screen);

        Show savedShow = showRepository.save(show);

        allocateSeats(savedShow, screen.getTotalSeats());

        return savedShow;
    }

    private void allocateSeats(Show show, Integer totalSeats) {

        if (totalSeats == null) {
            throw new RuntimeException("Screen totalSeats is not configured");
        }

        for (int i = 1; i <= totalSeats; i++) {
            Seat seat = new Seat();
            seat.setShow(show);
            seat.setSeatNumber("S" + i);
            seat.setStatus(SeatStatus.AVAILABLE);
            seatRepository.save(seat);
        }
    }
}

