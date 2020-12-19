package com.example.pnl.util;

import com.google.json.JsonSanitizer;

public class Sanitizer {

    public static String sanitize(String result) {
        return JsonSanitizer.sanitize(result);
    }
}
