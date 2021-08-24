package com.yangshj.test_demo.thread.volatile_stop_thread;

import java.util.concurrent.BlockingQueue;

public class Consumer {

    BlockingQueue<Integer> storage;

    public Consumer(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        return !(Math.random() > 0.97);
    }

}
