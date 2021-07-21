package com.techelevator.tenmo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogAPIRequest {

    public static void logAPICall(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
        String timeNow = now.format(formatter);
        System.out.println(timeNow + "-" + message);
    }

}
