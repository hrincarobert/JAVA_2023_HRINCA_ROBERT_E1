package com.example.lab11.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "has_genre", schema = "public", catalog = "music")
@IdClass(JunctionEntityPK.class)
@NamedQueries({
        @NamedQuery(name = "Junction.findByAlbumId",
                query = "select j from JunctionEntity j where j.albumId = :albumId"),
        @NamedQuery(name = "Junction.findByGenreId",
                query = "select j from JunctionEntity j where j.genreId = :genreId"),
})
public class JunctionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "album_id")
    private int albumId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "genre_id")
    private int genreId;

    public JunctionEntity(int albumId, int genreId) {
        this.albumId = albumId;
        this.genreId = genreId;
    }

    public JunctionEntity() {

    }

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
        JunctionEntity that = (JunctionEntity) o;
        return albumId == that.albumId && genreId == that.genreId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, genreId);
    }

    @Override
    public String toString() {
        return "JunctionEntity{" +
                "albumId=" + albumId +
                ", genreId=" + genreId +
                '}';
    }
}
