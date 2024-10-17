package org.daniel.adventurelog.jdbc;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.nio.file.Path;

public class DatabaseConnectionPool {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:/" + Path.of(System.getProperty("user.home")).resolve("./adventurelog/database/adventurelog.db"));
        config.setUsername("");
        config.setPassword("");
        config.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource(){
        return dataSource;
    }
}
