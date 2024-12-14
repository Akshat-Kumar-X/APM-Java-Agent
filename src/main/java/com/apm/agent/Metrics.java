package com.apm.agent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Metrics {
    private static final ConcurrentHashMap<String, AtomicInteger> maxDepthCounter = new ConcurrentHashMap<>();

    public static void incrementMaxDepthCounter(String functionName) {
        maxDepthCounter.computeIfAbsent(functionName, k -> new AtomicInteger()).incrementAndGet();
    }

    public static void reportMetrics() {
        System.out.println("Max Depth Violations: " + maxDepthCounter);
    }
}
