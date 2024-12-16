package com.ivanfranchin.newsclient.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateTimeUtil {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ssX";
    private static final ZoneId ZONE_ID = ZoneId.of("UTC");
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern(PATTERN);

    private DateTimeUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static Date fromStringToDate(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, DTF);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZONE_ID);
        return Date.from(zonedDateTime.toInstant());
    }
}
