package com.yangshj.test_demo.thread.thread_pool.fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Integer> {

    private int n;

    private ForkJoinTest(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {

        if (n <= 1) {
            return n;
        }

        ForkJoinTest f1 = new ForkJoinTest(n - 1);
        f1.fork();
        ForkJoinTest f2 = new ForkJoinTest(n - 2);
        f2.fork();
        return f1.join() + f2.join();
    }


    /**
     * 斐波那契数列
     * 
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask task = forkJoinPool.submit(new ForkJoinTest(i));
            System.out.println(task.get());
        }
    }
}
