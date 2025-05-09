package org.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRIES")
public class Country {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 30)
    private String name;

    @Column(name = "CODE", length = 30)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTINENT")
    private Continent continent;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

}