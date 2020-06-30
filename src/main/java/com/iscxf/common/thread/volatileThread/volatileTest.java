package com.iscxf.common.thread.volatileThread;

/**
 * volatile 相关操作
 * volatile 保证操作的可见性但不保证原子性，原子性要用靠synchronized来实现
 * @author chenxf
 * Created on 2020/4/21
 */
public class volatileTest {

    private static volatile int sum = 0;

    public static void main(String[] args) {
        Thread[] threadPool = new Thread[3];
        for (int i=0;i<threadPool.length;i++) {
            threadPool[i] = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        for (int i=0;i<10000;i++) {
                            sum++;
                        }
                    } catch (Exception e) {
                        System.out.println("new Thread error! "+e);
                    }
                }
            });
            threadPool[i].start();
        }
        for (Thread t : threadPool){
            try {
                t.join();
            }catch (Exception e){
                System.out.println("join error! "+e);
            }
        }
        System.out.println("结果：sum="+ sum);

    }

}
