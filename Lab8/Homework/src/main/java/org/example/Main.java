package org.example;

import org.example.DAO.CityDAO;
import org.example.DAO.ContinentDAO;
import org.example.DAO.CountryDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
                try {
                    var continents = new ContinentDAO();
//                    continents.create("Europe");
                    var countries = new CountryDAO();
                    int europeId = continents.findByName("Europe");
//                    countries.create("Romania", europeId);
//                    countries.create("Ukraine", europeId);
//                    System.out.println("Ceva");
//                    int romaniaId=countries.findByName("Romania");
//                    int ukraineId=countries.findByName("Ukraine");
//                    System.out.println(romaniaId+" "+ukraineId);
                     var cities = new CityDAO();
//                    cities.create("Bucharest", "Romania");
//                    cities.create("Kyiv", "Ukraine", 1, 50.45, 30.45);
                  // cities.importCities();
                  var distance = cities.distanceBetweenCities("Bangui", "Guatemala City");
                  System.out.println("Distance "+distance+ " km");
                    Database.getConnection().close();
                } catch (SQLException e) {
                    System.err.println(e);

                }
            }
        }
