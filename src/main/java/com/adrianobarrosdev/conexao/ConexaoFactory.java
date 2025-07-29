package com.adrianobarrosdev.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/sgpro";
    private static final String USUARIO = "postgres";
    private static final String SENHA = System.getenv("DB_PASSWORD");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver do PostgreSQL", e);
        }
    }

    public static Connection getConnection() throws SQLException {
    	Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
    	connection.setAutoCommit(false);
        return connection;
    }
}