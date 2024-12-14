package com.apm.agent;

import java.util.concurrent.atomic.AtomicLong;

public class FunctionCallRecord {
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    private final long callId;
    private final long parentCallId;
    private final String functionName;
    private final long startTime;
    private long endTime;
    private long duration;
    private boolean completed;
    private boolean exceptionThrown;

    public FunctionCallRecord(long parentCallId, String functionName) {
        this.callId = ID_GENERATOR.incrementAndGet();
        this.parentCallId = parentCallId;
        this.functionName = functionName;
        this.startTime = System.currentTimeMillis();
        this.completed = false;
    }

    public long getCallId() {
        return callId;
    }

    public long getParentCallId() {
        return parentCallId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isExceptionThrown() {
        return exceptionThrown;
    }

    public void markCompletion(boolean exceptionThrown) {
        this.endTime = System.currentTimeMillis();
        this.duration = endTime - startTime;
        this.completed = true;
        this.exceptionThrown = exceptionThrown;
    }

    @Override
    public String toString() {
        return "FunctionCallRecord{" +
                "callId=" + callId +
                ", parentCallId=" + parentCallId +
                ", functionName='" + functionName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", completed=" + completed +
                ", exceptionThrown=" + exceptionThrown +
                '}';
    }
}
