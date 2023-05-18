package com.example.lab11.entities;

import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.*;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "albums", schema = "public", catalog = "music")
@NamedQueries({
        @NamedQuery(
                name = "Albums.findAll",
                query = "SELECT a FROM AlbumsEntity a"
        ),
        @NamedQuery(
                name = "Albums.findById",
                query = "SELECT a FROM AlbumsEntity a WHERE a.id = :id"
        ),
        @NamedQuery(
                name = "Albums.findByTitle",
                query = "SELECT a FROM AlbumsEntity a WHERE a.title LIKE CONCAT('%',:title,'%')"
        ),
        @NamedQuery(
                name = "Albums.findByArtist",
                query = "SELECT a FROM AlbumsEntity a WHERE a.artistObj.id = :artist"
        ),
        @NamedQuery(
                name = "Albums.findByGenre",
                query = "SELECT a FROM AlbumsEntity a WHERE a.genre = :genre"
        ),
        @NamedQuery(
                name = "Albums.findByReleaseYear",
                query = "SELECT a FROM AlbumsEntity a WHERE a.releaseYear = :releaseYear"
        )
})

public class AlbumsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "genre")
    private int genre;
    @Basic
    @Column(name = "release_year")
    private Integer releaseYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist")
    private ArtistsEntity artistObj;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "has_genre",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenresEntity> genres = new HashSet<>();


    public AlbumsEntity() {

    }


    public Set<GenresEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenresEntity> genres) {
        this.genres = genres;
    }

    public ArtistsEntity getArtistObj() {
        return artistObj;
    }

    public void setArtistObj(ArtistsEntity artistObj) {
        this.artistObj = artistObj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumsEntity that = (AlbumsEntity) o;
        return id == that.id &&  genre == that.genre && Objects.equals(title, that.title) && Objects.equals(releaseYear, that.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, releaseYear);
    }

    @Override
    public String toString() {
        return "AlbumsEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", releaseYear=" + releaseYear +
                ", artistObj=" + artistObj +
                ", genres=" + genres +
                '}';
    }
}
