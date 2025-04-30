package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
                try {
                    var continents = new ContinentDAO();
                    continents.create("Europe");
                    Database.getConnection().commit();
                    var countries = new CountryDAO();
                    int europeId = continents.findByName("Europe");
                    countries.create("Romania", europeId);
                    countries.create("Ukraine", europeId);
                    Database.getConnection().commit();
                   List<String> countriesInEurope= countries.findByContinent("Europe");
                   countriesInEurope.stream().forEach(System.out::println);
                    countries.findByContinent("Europe");
                    Database.getConnection().close();
                } catch (SQLException e) {
                    System.err.println(e);
                  Database.rollback();
                }
            }
        }
