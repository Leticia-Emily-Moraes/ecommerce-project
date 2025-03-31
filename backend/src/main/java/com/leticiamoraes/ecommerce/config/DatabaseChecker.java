package com.leticiamoraes.ecommerce.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseChecker {

    @Autowired
    private DataSource dataSource;

    private final int MAX_RETRIES = 5;
    private final int WAIT_MS = 3000;

    @PostConstruct
    public void checkDatabaseConnection(){
        int attempts = 0;
        while (attempts < MAX_RETRIES){
            try(Connection connection = dataSource.getConnection()){
                System.out.println("Database connection established: " + connection.getMetaData().getDatabaseProductName());
                return;
            }catch(SQLException e){
                attempts++;
                System.err.println("Failed to connect to database. Attemp "+ attempts + " of" + MAX_RETRIES);
                try{
                    Thread.sleep(WAIT_MS);
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new IllegalStateException("Could not connect to database after " + MAX_RETRIES + " attempts");
    }
}
