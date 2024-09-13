package by.it_academy.jd2.util;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;



public class DBUtils {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/vote";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "postgres";
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final int MAX_POOL_SIZE = 100;

    private static long time = 0;
    private static DataSource dataSource;

    static {
        initializeDataSource();
    }

    private DBUtils() {
    }

    private static void initializeDataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(JDBC_DRIVER);
            cpds.setJdbcUrl(JDBC_URL);
            cpds.setUser(JDBC_USER);
            cpds.setPassword(JDBC_PASSWORD);
            cpds.setMaxPoolSize(MAX_POOL_SIZE);
            dataSource = cpds;
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Error initializing data source", e);
        }
    }

    public static Connection getConnection() {
        try {
            long start = System.currentTimeMillis();
            Connection con = dataSource.getConnection();
            long stop = System.currentTimeMillis();
            time += (stop - start);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException("Connection error", e);
        }
    }

    public static long getConnectionTime() {
        return time;
    }
}