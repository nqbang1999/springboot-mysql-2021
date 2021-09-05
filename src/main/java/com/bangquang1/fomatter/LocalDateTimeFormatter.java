package com.bangquang1.fomatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        if (text != null) {
            System.out.println("vào đây fomatter");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-YYYY");
            return LocalDateTime.parse(text, dtf);
        } else {
            return LocalDateTime.now();
        }
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return null;
    }
}
