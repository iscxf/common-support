package com.iscxf.common.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import static com.iscxf.common.thread.ThreadPoolUtil.threadPoolExecutor;

/**
 * @author chenxf
 * Created on 2020/4/3
 */
public class ThreadMain {

    public static void main(String[] args) {
        try {
            //自定义Callable
            Callable myCallable = new Callable() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(3000);
                    System.out.println("Callable call方法执行中");
                    return "call方法返回值";
                }
            };

            //自定义Runnable
            Runnable myRunnable = new Runnable(){
                @Override
                public void run(){
                    try {
                        Thread.sleep(3000);
                        System.out.println("Runnable run方法执行中");
                    }catch (Exception e){
                        //
                    }
                }
            };

            Thread manualRunnableThread = new Thread(myRunnable);
            manualRunnableThread.start();

            threadPoolExecutor.submit(myCallable);

            List<Future<?>> futures = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                final int finalInt = i;
                Future<?> future = threadPoolExecutor.submit(() -> {
                    String name = Thread.currentThread().getName();
                    System.out.println("I'm thread name: "+  name);
                    return sum(finalInt);
                });
                futures.add(future);
            }

            // 阻塞主线程，等待多线程执行完成后继续
            for (Future<?> future : futures) {
                //get 会阻塞线程直到完成，同步还可以获取到返回的结果
                String data = (String)future.get();
                System.out.println("trace data:" + data);
            }
//            threadPoolExecutor.shutdown();
        } catch (Exception e) {
            //
        }
    }

    static Integer sum = 0;
    private synchronized static String sum(Integer i){
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //
        }
        sum ++;
        System.out.println("thread number: "+  i + ",sum:" + sum);
        return sum.toString();
    }
}
