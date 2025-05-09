package org.example.dao;

import org.example.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    public void create(String name, int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (name, continent) values (?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    public List<String> findByContinent(String continent) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "select countries.name  from countries join continents  on countries.continent = continents.id where continents.name = ?"
        ))
        {
            pstmt.setString(1, continent);
            ResultSet rs=pstmt.executeQuery();
            List<String> list=new ArrayList<>();
            while(rs.next())
            {
                list.add(rs.getString("name"));
            }
            return list;

        }

    }
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from countries where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

}
