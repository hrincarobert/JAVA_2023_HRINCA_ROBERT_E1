package org.example;

import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;

import java.io.Serializable;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
                query = "select e from Album e order by e.id"),
        @NamedQuery(name = "Album.findById",
                query = "select e from Album e where e.id = :id"),
        @NamedQuery(name = "Album.findByName",
                query = "select e from Album e where e.title = :title"),
        @NamedQuery(name = "Album.findId",
                query = "select count(e)+1 from Album e"),
})
public class Album extends AbstractEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "title")
    private String title;

    @Column(name = "artist_id")
    private long artistID;

    public Album(Long id, int releaseYear, String title, long artistID) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artistID = artistID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setArtistID(long artistID) {
        this.artistID = artistID;
    }

    public long getArtistID() {
        return artistID;
    }

    @Override
    public String toString() {
        return "Album {id = " + id + ", releaseYear = "+ releaseYear + ", title = " + title + ", artistID = " + artistID + "}";
    }
}