package com.example.pnl.util;

import java.sql.Date;
import java.util.Optional;

public class DateTimeUtils {

    public static Optional<Date> toSqlDate(Object bezorgDatum) {
        return bezorgDatum instanceof String
                ? Optional.of(Date.valueOf((String)bezorgDatum))
                : Optional.empty();
    }
}
