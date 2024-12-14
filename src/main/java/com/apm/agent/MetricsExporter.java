package com.apm.agent;

public class MetricsExporter {
    public static void export(String metricName, long value) {
        System.out.println("Exporting metric: " + metricName + " = " + value);
        // Future integration: Send to Elastic APM or Prometheus
    }
}

