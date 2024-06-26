package org.brito.constantes;

public class scripts {

    public static String CRIAR_DATABASE = "CREATE DATABASE porto";
    public static String CRIAR_TABELA_USUARIOS = "CREATE TABLE usuarios (" +
            "id SERIAL PRIMARY KEY," +
            "login VARCHAR(255) UNIQUE NOT NULL," +
            "password VARCHAR(255) NOT NULL," +
            "role VARCHAR(50) NOT NULL)";
    public static String INSERIR_USUARIO = "INSERT INTO usuarios (login, password, role) VALUES (?, ?, ?)";
    public static String BUSCAR_PASSWORD_PELO_LOGIN = "SELECT password FROM usuarios WHERE login = ?";
}
