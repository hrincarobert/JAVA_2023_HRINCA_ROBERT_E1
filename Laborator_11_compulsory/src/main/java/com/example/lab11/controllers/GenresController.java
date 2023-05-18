package com.example.lab11.controllers;

import com.example.lab11.entities.GenresEntity;
import com.example.lab11.services.GenreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/genres")
@RequiredArgsConstructor
public class GenresController {
    private final GenreService genresService;

    @GetMapping("/{id}")
    public ResponseEntity<GenresEntity> getById(@PathVariable @Valid @Min(0) Long id){
        GenresEntity category = genresService.getById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/")
    public ResponseEntity<List<GenresEntity>> getAll(){
        List<GenresEntity> categories = genresService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid GenresEntity ent) {
        genresService.create(ent);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable @Valid @Min(0) Long id, @RequestBody @Valid GenresEntity ent){
        genresService.update(ent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable @Valid @Min(0) Long id) {
        genresService.remove(id);
    }
}
