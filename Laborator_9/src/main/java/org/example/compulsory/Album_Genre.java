package org.example.compulsory;


import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Album_Genre {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private int album_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private int genre_id;

    public Album_Genre(){};

    public Album_Genre(int album_id, int genre_id){
        this.album_id = album_id;
        this.genre_id = genre_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    @Override
    public String toString() {
        return "Album_Gernre [album_id=" + album_id + ", genre_id=" + genre_id + "]";
    }
}
