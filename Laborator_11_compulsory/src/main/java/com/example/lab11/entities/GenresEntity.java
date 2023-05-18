package com.example.lab11.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genres", schema = "public", catalog = "music")
@NamedQueries({
        @NamedQuery(name = "GenresEntity.findByName",
                query = "select e from GenresEntity e where e.name like :namePattern"),
})
public class GenresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<AlbumsEntity> albums = new HashSet<>();

    public GenresEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenresEntity() {
    }

    public Set<AlbumsEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumsEntity> albums) {
        this.albums = albums;
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
        GenresEntity that = (GenresEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GenresEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
