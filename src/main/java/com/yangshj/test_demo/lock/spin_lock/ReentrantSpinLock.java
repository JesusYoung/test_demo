package com.yangshj.test_demo.lock.spin_lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现一个可重入的自旋锁
 */
public class ReentrantSpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    // 重入次数
    private int count = 0;



    public static void main(String[] args) {
        ReentrantSpinLock spinLock = new ReentrantSpinLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
            spinLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了了自旋锁");
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }


    public void lock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            ++count;
            return;
        }
        // 自旋获取锁
        while (!owner.compareAndSet(null, t)) {
            System.out.println("自旋了");
        }
    }

    private void unlock() {
        Thread t = Thread.currentThread();
        // 只有持有锁的线程才能解锁
        if (t == owner.get()) {
            if (count > 0) {
                --count;
            }
        } else {
            // 此处无需CAS操作，因为没有竞争，因为只有线程持有者才能解锁
            owner.set(null);
        }
    }
}
