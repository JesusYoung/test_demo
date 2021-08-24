package com.yangshj.test_demo.lock.fair_unfair_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    /**
     * 公平锁与非公平锁
     *
     *      公平锁按顺序执行
     *      非公平锁可以出现插队现象
     */
    private final Lock queueLock = new ReentrantLock(true);

    public void printJob(Object document) {

        queueLock.lock();
        try {
            long duration = (long) (Math.random() * 10000);
            System.out.printf("%s: PrintQueue1: Printing a Job during %d seconds - 1\n",
                    Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

        queueLock.lock();
        try {
            long duration = (long) (Math.random() * 10000);
            System.out.printf("%s: PrintQueue2: Printing a Job during %d seconds - 2\n",
                    Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }
}
