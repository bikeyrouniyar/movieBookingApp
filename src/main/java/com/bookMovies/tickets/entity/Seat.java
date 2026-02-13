package com.bookMovies.tickets.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"show_id", "seatNumber"})
})
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Show show;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;
}
