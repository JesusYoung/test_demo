package com.yangshj.test_demo.thread.volatile_stop_thread;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    public volatile boolean canceled = false;
    private BlockingQueue<Integer> storage;

    Producer(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 50 == 0) {
                    System.out.println("p: canceled-" + canceled);
                    storage.put(num);
                    System.out.println(num + "是50的倍数,被放到仓库中了。");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行。");
        }
    }
}
