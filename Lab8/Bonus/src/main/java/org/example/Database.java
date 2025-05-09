package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "STUDENT";
    private static final String PASSWORD = "testParola123";
    private static HikariDataSource connectionPool;

    private Database()
    {

    }

    public static Connection getConnection() {
        if(connectionPool == null) {
            createConnection();
        }

        try {
            var con=connectionPool.getConnection();
             return con;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void createConnection() {
            HikariConfig newConfig=new HikariConfig();
            newConfig.setJdbcUrl(URL);
            newConfig.setUsername(USER);
            newConfig.setPassword(PASSWORD);
            newConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");

            newConfig.setMaximumPoolSize(10);
            newConfig.setMinimumIdle(3);
            newConfig.setIdleTimeout(30000);
            newConfig.setConnectionTimeout(100000);
            newConfig.setMaxLifetime(60000);
            
            connectionPool = new HikariDataSource(newConfig);


    }
    public static void closeConnection() {

            if (connectionPool != null && !connectionPool.isClosed()) {
                connectionPool.close();
            }

    }





}
