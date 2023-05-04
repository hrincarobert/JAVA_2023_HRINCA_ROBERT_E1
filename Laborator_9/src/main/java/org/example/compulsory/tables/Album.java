package org.example.compulsory.tables;

import javax.persistence.*;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "release_year")
    private int release_year;

    @Column(name = "title")
    private String title;

    @Column(name = "artist_id")
    private int artist_id;
    public Album(){};

    public Album(int id, int release_year, String title, int artist_id){
        this.id = id;
        this.release_year = release_year;
        this.title = title;
        this.artist_id = artist_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public int getArtist_id() {
        return artist_id;
    }

    @Override
    public String toString() {
        return "Album [id=" + id + ", release_year=" + release_year + ", title=" + title + ", artist_id=" + artist_id + "]";
    }
}
