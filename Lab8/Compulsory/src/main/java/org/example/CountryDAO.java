package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
