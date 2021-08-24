package com.yangshj.test_demo.lock.read_write_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * 读锁可以多个同时持有
 * 写锁只能一个线程持有
 *
 */
public class ReadWriteLock {


    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();


    public static void main(String[] args) {
        new Thread(ReadWriteLock::read).start();
        new Thread(ReadWriteLock::read).start();
        new Thread(ReadWriteLock::read).start();
        new Thread(ReadWriteLock::read).start();
        new Thread(ReadWriteLock::write).start();
        new Thread(ReadWriteLock::write).start();
        new Thread(ReadWriteLock::write).start();
        new Thread(ReadWriteLock::write).start();
    }

    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }
}
