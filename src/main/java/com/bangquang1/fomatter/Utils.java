package com.bangquang1.fomatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    
    public String formatLocalDateTime(LocalDateTime timeInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedTime = timeInput.format(formatter);
        return formattedTime;
    }
}
