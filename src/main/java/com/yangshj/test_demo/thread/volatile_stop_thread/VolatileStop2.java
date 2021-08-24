package com.yangshj.test_demo.thread.volatile_stop_thread;

import java.util.concurrent.ArrayBlockingQueue;

public class VolatileStop2 {


    /**
     * 尽管已经把 canceled 设置成 true，但生产者仍然没有停止，这是因为在这种情况下，
     * 生产者在执行 storage.put(num) 时发生阻塞，
     * 在它被叫醒之前是没有办法进入下一次循环判断 canceled 的值的，
     * 所以在这种情况下用 volatile 是没有办法让生产者停下来的
     *
     */
    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<>(8);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(500);


        Consumer consumer = new Consumer(storage);
//        while (consumer.needMoreNums()) {
//            System.out.println(consumer.storage.take() + "被消费了。");
//            Thread.sleep(100);
//        }
        System.out.println("消费者不需要更多数据了。");


        producer.canceled = true;
        System.out.println(producer.canceled);
    }
}
