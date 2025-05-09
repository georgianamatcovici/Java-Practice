package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class EntityManagerFactorySingleton {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ExamplePU");
    private EntityManagerFactorySingleton() {}
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    public static void close() {
        emf.close();
    }

}
