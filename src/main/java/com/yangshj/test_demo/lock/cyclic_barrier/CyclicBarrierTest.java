package com.yangshj.test_demo.lock.cyclic_barrier;

import java.util.Vector;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier测试
 *
 * CyclicBarrier为多个线程并行执行，同时完成后，由最后一个完成的线程去执行回调操作（回调操作可以再开启一个线程去并行）
 *
 * @author yangshj
 * @since 2023/3/2 15:21
 */
public class CyclicBarrierTest {

    // 订单队列
    Vector<String> pos = new Vector<>();
    // 派送单队列
    Vector<Integer> dos = new Vector<>();

    // 执行回调的线程池
    Executor executor = Executors.newFixedThreadPool(1);

    final CyclicBarrier barrier = new CyclicBarrier(2, ()->{
        System.out.println("callbackThread: " + Thread.currentThread().getName());
        executor.execute(this::check);
    });

    void check() {
        String p = pos.remove(0);
        Integer d = dos.remove(0);

        System.out.println("execCallbackThread: " + Thread.currentThread().getName());
        System.out.println("p: " + p + ", d: " + d);
    }

    void checkAll() {
        // 循环查询订单库
        Thread thread1 = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                try {
                    System.out.println("posThread" + i + ": " + Thread.currentThread().getName());
                    pos.add("p" + i);
                    i++;
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        // 循环查询运单库
        Thread thread2 = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                try {
                    // 模拟其中一个并行线程为耗时线程
                    Thread.sleep(100);

                    System.out.println("dosThread" + i + ": " + Thread.currentThread().getName());
                    dos.add(i);
                    i++;
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }

    public static void main(String[] args) {
        CyclicBarrierTest test = new CyclicBarrierTest();
        test.checkAll();
    }
}
