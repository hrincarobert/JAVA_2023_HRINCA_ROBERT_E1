package com.example.lab11.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class JunctionEntityPK implements Serializable {
    @Column(name = "album_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int albumId;
    @Column(name = "genre_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreId;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JunctionEntityPK that = (JunctionEntityPK) o;
        return albumId == that.albumId && genreId == that.genreId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, genreId);
    }
}
