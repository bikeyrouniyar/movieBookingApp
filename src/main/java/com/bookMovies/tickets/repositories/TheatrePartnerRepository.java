package com.bookMovies.tickets.repositories;

import com.bookMovies.tickets.entity.TheatrePartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatrePartnerRepository extends JpaRepository<TheatrePartner, Long> {
}
