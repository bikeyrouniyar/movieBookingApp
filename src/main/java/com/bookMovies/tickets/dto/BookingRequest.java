package com.bookMovies.tickets.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    private Long showId;
    private String userEmail;
    private List<String> seatNumbers;
}
