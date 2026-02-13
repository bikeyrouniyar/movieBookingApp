package com.bookMovies.tickets.services;

import com.bookMovies.tickets.dto.BookingRequest;
import com.bookMovies.tickets.entity.Booking;
import com.bookMovies.tickets.entity.Seat;
import com.bookMovies.tickets.entity.SeatStatus;
import com.bookMovies.tickets.entity.Show;
import com.bookMovies.tickets.repositories.BookingRepository;
import com.bookMovies.tickets.repositories.SeatRepository;
import com.bookMovies.tickets.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public ResponseEntity<?> bookTickets(BookingRequest request) {

        Optional<Show> optionalShow = showRepository.findById(request.getShowId());

        if (optionalShow.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body("Show not found");
        }

        Show show = optionalShow.get();

        List<Seat> seats = seatRepository
                .findByShowIdAndSeatNumberIn(show.getId(), request.getSeatNumbers());

        if (seats.size() != request.getSeatNumbers().size()) {
            return ResponseEntity
                    .status(404)
                    .body("One or more seats not found");
        }

        for (Seat seat : seats) {
            if (seat.getStatus() == SeatStatus.BOOKED) {
                return ResponseEntity
                        .status(409)
                        .body("Seat " + seat.getSeatNumber() + " already booked");
            }
        }

        for (Seat seat : seats) {
            seat.setStatus(SeatStatus.BOOKED);
        }

        Booking booking = new Booking();
        booking.setUserEmail(request.getUserEmail());
        booking.setShow(show);
        booking.setTotalAmount(show.getPrice() * seats.size());

        bookingRepository.save(booking);

        return ResponseEntity.ok(booking);
    }
}

