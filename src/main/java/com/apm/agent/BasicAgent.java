package com.apm.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class BasicAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Agent started: BasicAgent with updated Advice - Debugging Active");

        new AgentBuilder.Default()
                .with(new SilentListener()) // Use a custom silent listener
                .type(ElementMatchers.nameStartsWith("org.example")) // Match classes in org.example
                .transform((builder, typeDescription, classLoader, module, loaded) ->
                        builder.method(ElementMatchers.any()) // Match all methods
                                .intercept(Advice.to(MethodAdvice.class)) // Use `Advice` directly
                )
                .installOn(inst);
    }
}
