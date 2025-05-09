package org.example;

import org.example.DAO.CityDAO;
import org.example.DAO.ContinentDAO;
import org.example.DAO.CountryDAO;
import org.example.Frontend.MainWindow;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
                try {
                 //   var frame=new MainWindow();
                    var continents = new ContinentDAO();
                    var countries = new CountryDAO();
//                    int romaniaId=countries.findByName("Romania");
//                    int ukraineId=countries.findByName("Ukraine");
                    var cities = new CityDAO();
//                  var distance = cities.distanceBetweenCities("Bangui", "Guatemala City");
//                  System.out.println("Distance "+distance);
                    cities.insertCitiesInGraph();
                    cities.generateRandomSisterRelationships();
                    cities.getSubsets();

                    Database.getConnection().close();
                } catch (SQLException e) {
                    System.err.println(e);

                }
            }
        }
