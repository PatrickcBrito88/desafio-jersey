package org.brito.desafiojersey.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class ObjectUtils {

    public static LocalDateTime convertToLocalDateTime(Object timestampObject) {
        return Objects.nonNull(timestampObject) ? ((Timestamp) timestampObject).toLocalDateTime() : null;
    }

    public static Long convertToLong(Object integerObject) {
        return ((Integer) integerObject).longValue();
    }

}
