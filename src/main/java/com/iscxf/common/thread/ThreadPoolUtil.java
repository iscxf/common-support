package com.iscxf.common.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxf
 * Created on 2020/4/3
 */
public class ThreadPoolUtil {

    /** CPU的核数 */
    private static final int CPU_NUM = Runtime.getRuntime().availableProcessors();

    /** 创建线程池，采用CallerRunsPolicy策略 */
    public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            CPU_NUM * 2 + 1,
            20,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            new ThreadPoolExecutor.CallerRunsPolicy());

    /** 创建自定义名称线程池 */
    public static final ThreadPoolExecutor customerThreadNamePoolExecutor = new ThreadPoolExecutor(
            CPU_NUM * 2 + 1,
            20,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            new CustomerNameThreadFactory("customerName"),
            new ThreadPoolExecutor.CallerRunsPolicy());
}
