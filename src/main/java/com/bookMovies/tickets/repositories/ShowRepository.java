package com.bookMovies.tickets.repositories;

import com.bookMovies.tickets.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query("""
        SELECT s FROM Show s
        WHERE s.movie.name = :movieName
        AND s.screen.theatre.city = :city
        AND s.showDate = :date
    """)
    List<Show> findShows(
            @Param("movieName") String movieName,
            @Param("city") String city,
            @Param("date") LocalDate date);
}
