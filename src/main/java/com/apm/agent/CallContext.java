package com.apm.agent;

import java.util.Stack;

public class CallContext {
    private static final ThreadLocal<Stack<FunctionCallRecord>> callStack = ThreadLocal.withInitial(Stack::new);

    public static FunctionCallRecord enter(String functionName) {
        Stack<FunctionCallRecord> stack = callStack.get();
        long parentId = stack.isEmpty() ? -1 : stack.peek().getCallId();
        FunctionCallRecord record = new FunctionCallRecord(parentId, functionName);
        stack.push(record);
        return record;
    }

    public static void exit(boolean exceptionThrown) {
        Stack<FunctionCallRecord> stack = callStack.get();
        if (!stack.isEmpty()) {
            FunctionCallRecord record = stack.pop();
            record.markCompletion(exceptionThrown);
            FunctionLogger.log(record); // Log the completed record
        }
    }

    public static int getDepth() {
        return callStack.get().size();
    }
}
