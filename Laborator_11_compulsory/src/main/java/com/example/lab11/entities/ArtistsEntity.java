package com.example.lab11.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;

@Table(name = "artists", schema = "public", catalog = "music")
@NamedQueries({
        @NamedQuery(name = "Artist.findAll",
                query = "select e from ArtistsEntity e order by e.name"),
        @NamedQuery(name = "Artist.findByName",
                query = "SELECT a FROM ArtistsEntity a WHERE a.name LIKE :name"),
})
@Entity
public class ArtistsEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

    public ArtistsEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArtistsEntity(String name) {
        this.name = name;
    }

    public ArtistsEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistsEntity that = (ArtistsEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ArtistsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
