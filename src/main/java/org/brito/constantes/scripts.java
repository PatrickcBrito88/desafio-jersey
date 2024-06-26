package org.brito.constantes;

public class scripts {

    public static String CRIA_DATABASE = "CREATE DATABASE porto";
    public static String CRIA_TABELA_USUARIOS = "CREATE TABLE usuarios (" +
            "id SERIAL PRIMARY KEY," +
            "login VARCHAR(255) UNIQUE NOT NULL," +
            "password VARCHAR(255) NOT NULL," +
            "role VARCHAR(50) NOT NULL)";
    public static String INSERE_USUARIO = "INSERT INTO usuarios (login, password, role) VALUES (?, ?, ?)";
    public static String VALIDA_USUARIO_SENHA = "SELECT * FROM usuarios WHERE login = ?";
}
