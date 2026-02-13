package com.bookMovies.tickets.controller;

import com.bookMovies.tickets.dto.BookingRequest;
import com.bookMovies.tickets.entity.Booking;
import com.bookMovies.tickets.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> book(@RequestBody BookingRequest request) {

        return bookingService.bookTickets(request);
    }
}

