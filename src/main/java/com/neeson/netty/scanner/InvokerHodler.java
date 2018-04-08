package com.neeson.netty.scanner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/8
 * Time: 21:57
 * Description:
 */
public class InvokerHodler {

    private static Map<Short,Map<Short,Invoker>> invokes = new HashMap<>();

    /**
     * 添加命令调用
     * @param module
     * @param cmd
     * @param invoker
     */
    public static void addInvoker(short module,short cmd,Invoker invoker){
        Map<Short, Invoker> invokerMap = invokes.get(module);
        if (invokerMap == null){
            invokerMap = new HashMap<>();
            invokes.put(module,invokerMap);
        }
            invokerMap.put(cmd,invoker);
    }

    /**
     * 获取命令
     * @param module
     * @param cmd
     * @return
     */
     public static Invoker getInvoker(short module,short cmd){
         Map<Short, Invoker> shortInvokerMap = invokes.get(module);
         if (shortInvokerMap != null){
             return shortInvokerMap.get(cmd);
         }
         return null;
     }

}
