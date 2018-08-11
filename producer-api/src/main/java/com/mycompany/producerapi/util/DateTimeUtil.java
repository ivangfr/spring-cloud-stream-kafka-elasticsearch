package com.mycompany.producerapi.util;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {

    public static String createCurrentDateAsString(){
        return Instant.ofEpochSecond(Instant.now().getEpochSecond()).toString();
    }

}
