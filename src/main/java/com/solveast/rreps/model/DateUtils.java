package com.solveast.rreps.model;

import com.solveast.rreps.model.queries.family.Person;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Андрей on 30.11.2016.
 */
public class DateUtils {

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

    public static LocalDateTime toLocalDateTime(String str) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());

            Date date = formatter.parse(str);
            Timestamp timeStampDate = new Timestamp(date.getTime());

            return toLocalDateTime(timeStampDate);
        } catch (Exception e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }

    public static LocalDateTime toLocalDateTimeMultiFormat(String str) {
        List<String> patterns = Arrays.asList("yyyy-MM-dd", "dd-MM-yyyy", "dd.MM.yyyy");
        for (String item : patterns) {
            try {
                DateFormat formatter;
                formatter = new SimpleDateFormat(item, Locale.getDefault());

                Date date = formatter.parse(str);
                Timestamp timeStampDate = new Timestamp(date.getTime());

                LocalDateTime dateOutput = toLocalDateTime(timeStampDate);
                if (dateOutput.getYear() > 1980)
                    return dateOutput;
            } catch (Exception e) {
            }
        }
        return null;
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

    public static Integer getNowYear() {
        return LocalDateTime.now().getYear();
    }

    public static Integer getAge(LocalDateTime dateTime) {
        if (dateTime != null)
            return LocalDateTime.now().getYear() - dateTime.getYear();
        else
            return 999;
    }

    public static Integer getAge(Timestamp dateTime) {
        if (dateTime != null)
            return getAge(dateTime.toLocalDateTime());
        else
            return 100;
    }

    public static Integer getAge(Person person) {
        if (person != null && person.getBirthDate() != null)
            return getAge(person.getBirthDate());
        else
            return 100;
    }

    public static String getTitle(String source, LocalDateTime from, LocalDateTime to) {
        StringBuilder builder = new StringBuilder();

        if (from != null && to != null) {
            builder
                    .append(source)
                    .append(" ")
                    .append(getMonth(from.getMonthValue()))
                    .append(" ")
                    .append(from.getYear());

            if (from.getMonthValue() != to.getMonthValue())
                builder
                        .append(" - ")
                        .append(getMonth(to.getMonthValue()))
                        .append(" ")
                        .append(from.getYear());
        }
        return builder.toString();
    }

    private static String getMonth(int month) {
        switch (month) {
            case 1:
                return "Jan.";
            case 2:
                return "Feb.";
            case 3:
                return "Mar.";
            case 4:
                return "Apr.";
            case 5:
                return "May.";
            case 6:
                return "Jun.";
            case 7:
                return "Jul.";
            case 8:
                return "Aug.";
            case 9:
                return "Sep.";
            case 10:
                return "Oct.";
            case 11:
                return "Nov.";
            case 12:
                return "Dec.";
        }
        return "";
    }
}
