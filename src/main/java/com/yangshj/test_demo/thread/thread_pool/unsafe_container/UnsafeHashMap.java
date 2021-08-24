package com.yangshj.test_demo.thread.thread_pool.unsafe_container;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class UnsafeHashMap {

    public static void main(String[] args) {


        showUnsafeHashMap();
    }



    private static void showUnsafeHashMap() {

        final Map<Integer, String> map = new HashMap<>();

        // 65 535
        final Integer targetKey = 0b1111_1111_1111_1111;
        final String targetValue = "v";
        map.put(targetKey, targetValue);

        new Thread(() -> IntStream.range(0, targetKey).forEach(key -> map.put(key, "someValue"))).start();

        while (true) {
            if (null == map.get(targetKey)) {
                throw new RuntimeException("HashMap is not thread safe.");
            }
        }
    }
}
