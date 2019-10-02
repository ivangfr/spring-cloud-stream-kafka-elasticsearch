package com.mycompany.newsclient.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ssX";
    private static final String ZONE_ID = "UTC";
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern(PATTERN);

    private DateTimeUtil() {
    }

    public static Date fromStringToDate(String string) {
        LocalDateTime ldt = LocalDateTime.parse(string, DTF);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZONE_ID));
        return Date.from(zdt.toInstant());
    }

}
