package com.example.lab11.repositories;

import com.example.lab11.entities.ArtistsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistsRepository extends JpaRepository<ArtistsEntity, Long> {
}
