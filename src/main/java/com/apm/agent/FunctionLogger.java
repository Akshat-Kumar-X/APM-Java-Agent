package com.apm.agent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FunctionLogger {
    private static final ConcurrentHashMap<String, AtomicInteger> shortCallCounter = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicInteger> maxDepthCounter = new ConcurrentHashMap<>();

    // Logs the function call record if it meets the criteria
    public static void log(FunctionCallRecord record) {
        if (record.getDuration() < Config.MIN_FUNCTION_COLLECTION_DURATION) {
            incrementShortCallCounter(record.getFunctionName());
            return;
        }

        System.out.println("Function Call Record: " + record);
    }

    // Increments the counter for functions that completed too quickly
    public static void incrementShortCallCounter(String functionName) {
        shortCallCounter.computeIfAbsent(functionName, k -> new AtomicInteger()).incrementAndGet();
    }

    // Increments the counter for functions that exceed the max depth
    public static void incrementMaxDepthCounter(String functionName) {
        maxDepthCounter.computeIfAbsent(functionName, k -> new AtomicInteger()).incrementAndGet();
    }

    // Optionally provide a way to report metrics (e.g., for debugging or exporting)
    public static void reportMetrics() {
        System.out.println("Short Call Counts: " + shortCallCounter);
        System.out.println("Max Depth Violations: " + maxDepthCounter);
    }
}
