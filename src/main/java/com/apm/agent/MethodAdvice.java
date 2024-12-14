package com.apm.agent;

import net.bytebuddy.asm.Advice;

public class MethodAdvice {

    @Advice.OnMethodEnter
    static long onEnter(@Advice.Origin String method) {
        // Check if the call depth exceeds the configured max depth
        if (CallContext.getDepth() > Config.MAX_DEPTH) {
            Metrics.incrementMaxDepthCounter(method); // Log max-depth violation
            return -1; // Skip tracking this call
        }

        // Enter the call context
        FunctionCallRecord record = CallContext.enter(method);

        // Return the unique call ID
        return record.getCallId();
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    static void onExit(@Advice.Origin String method,
                       @Advice.Enter long callId,
                       @Advice.Thrown Throwable throwable) {
        if (callId == -1) {
            return; // Skip if the call wasn't tracked
        }

        boolean exceptionThrown = throwable != null;
        CallContext.exit(exceptionThrown); // Exit the call context
    }
}
