package org.brito.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtils {

    public static String buscaValidacao(final String chaveMensagem, final Object... params) {
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", Locale.getDefault());
        String pattern = bundle.getString(chaveMensagem);
        return MessageFormat.format(pattern, params);
    }

}
