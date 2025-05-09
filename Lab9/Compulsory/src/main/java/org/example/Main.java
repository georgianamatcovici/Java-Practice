package org.example;


import org.example.entities.Continent;
import org.example.repositories.ContinentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.testJPA();
    }


    public void testJPA() {
        EntityManager em = EntityManagerFactorySingleton.getEntityManagerFactory().createEntityManager();
        ContinentRepository continentRepo = new ContinentRepository(em);

        Continent continent = new Continent(1L, "Europe");
        continentRepo.create(continent);

        Continent found = continentRepo.findById(1L);
        System.out.println("Found continent: " + found.getName());

        List<Continent> list = continentRepo.findByName("Europe");
        list.forEach(c -> System.out.println("Found by name " + c.getName()));

        em.close();
        EntityManagerFactorySingleton.close();
    }
}


