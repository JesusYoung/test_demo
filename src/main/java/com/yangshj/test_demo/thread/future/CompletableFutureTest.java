package com.yangshj.test_demo.thread.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture
 *
 * @author yangshj
 * @since 2023/3/3 15:18
 */
public class CompletableFutureTest {


    public static void main(String[] args) {
        CompletableFutureTest test = new CompletableFutureTest();

        test.testOr();
    }

    /**
     * 测试描述CompletableFuture线程的or关系
     *
     *   理解为cf1或cf2有一个执行完就可以执行cf3，结果是两个都执行完，才执行cf3  ？？？？？？
     *
     *   测试结果：当第一个线程返回结果时，就会执行cf3，不会等待后面的线程
     */
    private void testOr() {


        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("cf1: " + Thread.currentThread().getName());
//                int a = (new Random().nextInt(20) + 10) * 100;
                int a = 500;
                System.out.println("a: " + a);
                Thread.sleep(a);
                return String.valueOf(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "a";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("cf2: " + Thread.currentThread().getName());
//                int b = (new Random().nextInt(30) + 20) * 100;
                int b = 15000;
                System.out.println("b: " + b);
                Thread.sleep(b);
                return String.valueOf(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "b";
        });

        CompletableFuture<String> cf3 = cf1.applyToEither(cf2, s -> {
            System.out.println("cf3: " + Thread.currentThread().getName());
            return s;
        });

        System.out.println("开始等待。。。");
        long start = System.currentTimeMillis();
        String result = cf3.join();
        long end = System.currentTimeMillis();
        System.out.println("result: " + result);
        System.out.println("等待耗时：" + (end - start));
    }
}
