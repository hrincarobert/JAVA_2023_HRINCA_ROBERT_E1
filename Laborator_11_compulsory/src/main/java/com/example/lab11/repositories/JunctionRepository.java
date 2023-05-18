package com.example.lab11.repositories;

import com.example.lab11.entities.JunctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface JunctionRepository extends JpaRepository<JunctionEntity, Long> {
}
