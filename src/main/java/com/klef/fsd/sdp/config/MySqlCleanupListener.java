package com.klef.fsd.sdp.config;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener // This annotation registers the listener with Tomcat
public class MySqlCleanupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // This method is called when the application starts up.
        // We don't need to do anything here.
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // This method is called when the application is being shut down or undeployed.
        System.out.println("Deregistering MySQL JDBC drivers...");

        // Get all the currently registered JDBC drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                // We only want to deregister the MySQL driver
                if (driver.getClass().getClassLoader() == getClass().getClassLoader()) {
                    DriverManager.deregisterDriver(driver);
                    System.out.println("Deregistered JDBC driver: " + driver);
                }
            } catch (SQLException e) {
                System.err.println("Error deregistering JDBC driver: " + driver);
                e.printStackTrace();
            }
        }

        // This is an extra cleanup step for the specific thread mentioned in the error
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
            System.out.println("MySQL connection cleanup thread shut down successfully.");
        } catch (Exception e) {
            System.err.println("Failed to shut down MySQL connection cleanup thread.");
            e.printStackTrace();
        }
    }
}
