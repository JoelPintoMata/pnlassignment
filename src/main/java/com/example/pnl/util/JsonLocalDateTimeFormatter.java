package com.example.pnl.util;

import java.time.format.DateTimeFormatter;

public class JsonLocalDateTimeFormatter {

    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm.ss.SSS");
}