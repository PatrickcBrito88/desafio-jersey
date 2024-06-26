package org.brito.desafiojersey.utils;

import org.mindrot.jbcrypt.BCrypt;

public class CriptUtils {

    public static String buscaPassCriptografado(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

}
