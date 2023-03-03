package com.yangshj.test_demo.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


/**
 * Future测试
 *
 * 模拟经典的烧水泡茶流程
 *
 *     洗水壶(1分钟) ---- 烧开水(15分)  ---- 泡茶
 *                       洗茶壶(1分钟)
 *                       洗茶杯(2分钟)
 *                       拿茶叶(1分钟)
 *
 * 洗水壶、烧开水、泡茶用一个线程T1，洗茶壶、洗茶杯、拿茶叶用另一个线程T2
 * T1线程中的泡茶，要在T2线程完成后才执行，也就是要拿到T2线程的执行结果
 *
 * @author yangshj
 * @since 2023/3/3 14:03
 */
public class FutureTaskTest {


    public static void main(String[] args) {
        FutureTaskTest test = new FutureTaskTest();

        try {
            test.test();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void test() throws ExecutionException, InterruptedException {
        // 创建任务T2的FutureTask
        FutureTask ft2 = new FutureTask<>(new T2Task());
        // 创建任务T1的FutureTask
        FutureTask ft1 = new FutureTask<>(new T1Task(ft2));
        // 线程T1执行任务ft1
        Thread T1 = new Thread(ft1);
        T1.start();
        // 线程T2执行任务ft2
        Thread T2 = new Thread(ft2);
        T2.start();

        // 等待线程T1执行结果
        System.out.println(ft1.get());
    }

    /**
     * T1Task需要执行的任务
     *      洗水壶、烧开水、泡茶
     */
    class T1Task implements Callable<String> {

        FutureTask<String> ft2;

        // T1任务需要T2任务的FutureTask
        T1Task(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1:洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T1:烧开水...");
//            TimeUnit.SECONDS.sleep(15);
            // 此处不休眠，阻塞等待ft2执行，更直观

            // 获取T2线程的茶叶
            long start = System.currentTimeMillis();
            System.out.println("开始等待拿T2的结果。。。。。");
            String ft = ft2.get();
            Long end = System.currentTimeMillis();
            System.out.println("T1:拿到茶叶:" + ft + "，耗时：" + (end - start));

            System.out.println("T1:泡茶...");
            return "上茶:" + ft;
        }
    }

    // T2Task需要执行的任务:// 洗茶壶、洗茶杯、拿茶叶
    class T2Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            System.out.println("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return "龙井";
        }
    }
}
