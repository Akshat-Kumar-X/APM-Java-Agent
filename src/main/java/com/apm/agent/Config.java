package com.apm.agent;

public class Config {
    public static final int MIN_FUNCTION_COLLECTION_DURATION = 10; // Milliseconds
    public static final int MAX_DEPTH = 10;

    public static boolean shouldSkipShortCalls() {
        return MIN_FUNCTION_COLLECTION_DURATION > 0;
    }
}
