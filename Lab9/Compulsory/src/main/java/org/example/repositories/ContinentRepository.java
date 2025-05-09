package org.example.repositories;

import org.example.entities.Continent;

import javax.persistence.EntityManager;
import java.util.List;

public class ContinentRepository {
    private EntityManager em;

    public ContinentRepository(EntityManager em) {
        this.em = em;
    }

    public void create(Continent continent) {
        em.getTransaction().begin();
        em.persist(continent);
        em.getTransaction().commit();
    }

    public Continent findById(Long id) {
        return em.find(Continent.class, id);
    }

    public List<Continent> findByName(String name) {
        return em.createQuery(
                        "select e from Continent e where e.name = :name", Continent.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Continent> findAll() {
        return em.createNamedQuery("Continent.findAll", Continent.class)
                .getResultList();
    }
}
