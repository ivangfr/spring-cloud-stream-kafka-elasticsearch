package com.mycompany.producerapi.util;

import java.time.Instant;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static String createCurrentDateAsString() {
        return Instant.ofEpochSecond(Instant.now().getEpochSecond()).toString();
    }

}
