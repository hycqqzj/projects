package com.hyc.agent;

import java.lang.instrument.Instrumentation;

public class LogAgent {
    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=========premain方法执行1========");
        System.out.println(agentArgs);
    }

    /**
     * 如果不存在 premain(String agentArgs, Instrumentation inst)则会执行 premain(String agentArgs)
     *
     * @param agentArgs
     */
    public static void premain(String agentArgs) {
        System.out.println("=========premain方法执行2========");
        System.out.println(agentArgs);
    }

    public static void agentmain(String agentArgs, Instrumentation inst){
        System.out.println("=========agentmain方法执行1========");
    }

    public static void agentmain(String agentArgs){
        System.out.println("=========agentmain方法执行2========");
    }
}