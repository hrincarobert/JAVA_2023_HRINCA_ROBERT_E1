package com.example.lab11.repositories;
import com.example.lab11.entities.AlbumsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumsRepository extends JpaRepository<AlbumsEntity, Long> {
}
