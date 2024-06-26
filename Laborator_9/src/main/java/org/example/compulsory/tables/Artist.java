package org.example.compulsory.tables;


import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;

import java.io.Serializable;

@Entity
@Table(name = "artist1")
@NamedQueries({
        @NamedQuery(name = "Artist.findAll",
                query = "select e from Artist e order by e.id"),
        @NamedQuery(name = "Artist.findById",
                query = "select e from Artist e where e.id = :id"),
        @NamedQuery(name = "Artist.findByName",
                query = "select e from Artist e where e.name = :name"),
        @NamedQuery(name = "Artist.findId",
                query = "select count(e)+1 from Artist e"),
})
public class Artist extends AbstractEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist id=" + id + ", name="+ name;
    }
}


//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "artists")
//public class Artist {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "name")
//    private String name;
//
//    public Artist(){};
//
//    public Artist(int id, String name){
//        this.id = id;
//        this.name = name;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String toString() {
//        return "Artist [id=" + id + ", name=" + name + "]";
//    }
//}
