package org.example.DAO;

import org.example.Database;
import org.example.Frontend.MainWindow;
import org.example.Frontend.SphericalMercator;
import org.example.Graphs.GraphClass;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityDAO {
    private GraphClass graphObject=new GraphClass();
    private List<Point> points = Collections.synchronizedList(new ArrayList<>());
    private List<Integer> colors = Collections.synchronizedList(new ArrayList<>());
    private int firstId=-1, lastId=-1;
    public void create(String name, String country) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into cities (country, name) values (?, ?)")) {
            pstmt.setString(1, country);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        }
    }

    public void create(String name, String country, int capital, double lat, double longit) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into cities (country, name, capital, latitude, longitude) values (?, ?, ?, ?, ?)")) {
            pstmt.setString(1, country);
            pstmt.setString(2, name);
            pstmt.setInt(3, capital);
            pstmt.setDouble(4, lat);
            pstmt.setDouble(5, longit);
            pstmt.executeUpdate();
        }
    }

    public void importCities() throws SQLException {
        Connection con = Database.getConnection();
        PreparedStatement pstmt = con.prepareStatement("insert into cities (country, name, capital, latitude, longitude) values (?, ?, ?, ?, ?)");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("concap.csv"))) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",", -1);
                //System.out.println(tokens[0]+" "+tokens[1]+" "+tokens[2]+" "+tokens[3]+" "+tokens[4]+" "+tokens[5]);
                if (tokens.length == 6) {
                    pstmt.setString(1, tokens[0]);
                    pstmt.setString(2, tokens[1]);
                    pstmt.setInt(3, 1);
                    pstmt.setDouble(4, Double.parseDouble(tokens[2]));
                    pstmt.setDouble(5, Double.parseDouble(tokens[3]));
                    System.out.println("Insert: ");
                    pstmt.executeUpdate();
                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public double distanceBetweenCities(String firstCity, String secondCity) throws SQLException {
        Connection con = Database.getConnection();
        double lat1=0, lat2=0, lon1=0, lon2=0;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select latitude, longitude from cities where name='" + firstCity + "'")) {


            if (rs.next()) {
                lat1 = rs.getDouble(1);
                lon1 = rs.getDouble(2);
            }
            ResultSet rs2 = stmt.executeQuery("select latitude, longitude from cities where name='" + secondCity + "'");
            if (rs2.next()) {
                lat2 = rs2.getDouble(1);
                lon2 = rs2.getDouble(2);
            }

        }
        return haversineFormula(lat1, lon1, lat2, lon2);

    }


    private double haversineFormula(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
         double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public void insertCitiesInGraph() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from cities")) {
            while (rs.next()) {
                int id = rs.getInt(1);
                if(firstId==-1) firstId=id;
                lastId=id;
                graphObject.addVertex(id);
                System.out.println("Inserted " + id);

            }
            System.out.println("First+Last  " + firstId + "   " + lastId);

        }


        }

        public void generateRandomSisterRelationships() throws SQLException {
            for (int i = 0; i < 50; i++) {
                int randomId = (int) (Math.random() * (lastId - firstId - 1)) + firstId;
                int anotherRandomId = (int) (Math.random() * (lastId - firstId - 1)) + firstId;
                graphObject.addEdge(randomId, anotherRandomId);
                System.out.println("Added edge " + randomId + " to " + anotherRandomId);
            }
        }

        public void getSubsets() throws SQLException
        {
            Connection con = Database.getConnection();
            SphericalMercator mercator = new SphericalMercator();
            try (PreparedStatement pstmt = con.prepareStatement("select name, latitude, longitude from cities where id=?")) {

              var subsets=  graphObject.findTwoConnected();

                double bboxMin = -20037508.34, bboxMax = 20037508.34;
                int panelWidth = 600;
                int panelHeight = 500;

                int currentColor=1;

                for(var subset: subsets)
              {
                  while(!subset.isEmpty())
                  {   System.out.println("Subset: ");
                      var id=subset.pop();
                      pstmt.setInt(1, id);
                      try (ResultSet rs = pstmt.executeQuery()) {
                          while (rs.next()) {
                              String name = rs.getString("name");
                              double lat = rs.getDouble("latitude");
                              double lon = rs.getDouble("longitude");
                              double xProj = mercator.xAxisProjection(lon);
                              double yProj = mercator.yAxisProjection(lat);
                              int x = (int) ((xProj - bboxMin) / (bboxMax - bboxMin) * panelWidth);
                              int y = (int) ((1 - (yProj - bboxMin) / (bboxMax - bboxMin)) * panelHeight);
                              //((org.example.Frontend.Canvas) canvas).addPoint(new Point(x, y));
                              points.add(new Point(x, y));
                              colors.add(currentColor);
                              System.out.println(name);
                          }

                      }
                  }
                  currentColor++;
                  System.out.println();


                  }
//                if(points!=null && points.size()>0)
//                {
//                    for (Point p : points) {
//                        System.out.println("Point: " + p.x + ", " + p.y);
//                    }
//                }
                MainWindow mainWindow = new MainWindow(points, colors);

              }

        }


}
