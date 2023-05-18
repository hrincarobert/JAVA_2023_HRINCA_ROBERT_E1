package com.example.lab11.services;


import com.example.lab11.entities.GenresEntity;
import com.example.lab11.exceptions.EntityNotFoundException;
import com.example.lab11.repositories.GenresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenresRepository genresRepository;

    // CREATE
    public void create(GenresEntity genres){
        genresRepository.save(genres);
    }

    // READ
    public GenresEntity getById(Long id){
        return genresRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Genre", id));
    }

    public List<GenresEntity> getAll(){
        return genresRepository.findAll();
    }

    // UPDATE
    public void update(GenresEntity ent){
        Long id = (long) ent.getId();
        this.getById(id);
        genresRepository.save(ent);
    }

    //    DELETE
    public void remove(Long id)
    {
        var category = this.getById(id);
        genresRepository.delete(category);
    }
}