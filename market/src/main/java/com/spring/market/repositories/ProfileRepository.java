package com.spring.market.repositories;

import com.spring.market.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("select p from Profile p where p.user.username = ?1")
    Optional<Profile> findByUsername(String username);
}
