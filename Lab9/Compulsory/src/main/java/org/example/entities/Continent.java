package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CONTINENTS")
@NamedQueries({
        @NamedQuery(name = "Continent.findAll",
                query = "select e from Continent e order by e.name"),
})
public class Continent implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 30)
    private String name;

    public Continent() {
    }

    public Continent(long id, String name) {
        this.id=id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
