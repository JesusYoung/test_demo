package com.yangshj.test_demo.lock.dead_lock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadLockDemo implements Runnable {

    private int flag;
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    public static void main(String[] argv) throws InterruptedException {
        DeadLockDemo r1 = new DeadLockDemo();
        DeadLockDemo r2 = new DeadLockDemo();
        r1.flag = 1;
        r2.flag = 2;
        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");
        t1.start();
        t2.start();


        // 代码定位死锁
        Thread.sleep(1000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null && deadlockedThreads.length > 0) {
            for (long deadlockedThread : deadlockedThreads) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThread);
                System.out.println("线程id为" + threadInfo.getThreadId()
                        + "，线程名为" + threadInfo.getThreadName()
                        + "的线程已经发生死锁，需要的锁正被线程"
                        + threadInfo.getLockOwnerName()
                        + "持有。");
            }
        }
    }

    @Override
    public void run() {
        System.out.println("线程"+Thread.currentThread().getName() + "的flag为" + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("线程1获得了两把锁");
                }
            }
        }
        if (flag == 2) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("线程2获得了两把锁");
                }
            }
        }
    }
}
