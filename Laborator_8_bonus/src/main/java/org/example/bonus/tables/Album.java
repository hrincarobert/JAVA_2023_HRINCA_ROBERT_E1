package org.example.bonus.tables;


public class Album {
    private int id;
    private int releaseYear;
    private String title;
    private int artistID;


    public Album(){
    }
    public Album(int id, int releaseYear, String title, int artistID){
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artistID = artistID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getTitle(){ return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public int getArtistID() {
        return artistID;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", release year='" + releaseYear + ", title: '" + title + "', artist id: " + artistID + '\'' +
                '}';
    }
}

