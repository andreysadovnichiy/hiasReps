package com.solveast.rreps.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Андрей on 30.11.2016.
 */
public class DateConverterUtils {

    public static Timestamp toTimestamp(String str_date) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());

            Date date = formatter.parse(str_date);
            Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (Exception e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }

    public static Timestamp toTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null)
            return null;
        return Timestamp.from(localDateTime.toInstant(ZoneOffset.ofHours(0)));
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null)
            return null;
        return timestamp.toLocalDateTime();
    }

}
