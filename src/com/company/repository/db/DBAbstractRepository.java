package com.company.repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBAbstractRepository {
    protected static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    protected static final String USER = "postgres";
    protected static final String PASSWORD = "root";

    protected Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

    protected DBAbstractRepository() throws SQLException {
    }
}
