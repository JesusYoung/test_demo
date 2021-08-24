package com.yangshj.test_demo.thread.thread_pool.cas;

public class DebugCAS implements Runnable {

    private volatile int value;


    public static void main(String[] args) throws InterruptedException {
        DebugCAS debugCAS = new DebugCAS();
        debugCAS.value = 100;

        Thread t1 = new Thread(debugCAS, "thread-1");
        Thread t2 = new Thread(debugCAS, "thread-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(debugCAS.value);
    }


    private synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
            System.out.println("线程" + Thread.currentThread().getName() + "执行成功。");
        } else {
            System.out.println("线程" + Thread.currentThread().getName() + "初始值与预期值不同，执行失败。");
        }
        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(100, 150);
    }
}
