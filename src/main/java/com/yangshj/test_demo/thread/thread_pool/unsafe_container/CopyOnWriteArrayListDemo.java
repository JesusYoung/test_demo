package com.yangshj.test_demo.thread.thread_pool.unsafe_container;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * copyOnWrite，再迭代中也已修改
 */
public class CopyOnWriteArrayListDemo {


    public static void main(String[] args) {


        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[] {1, 2, 3});

        System.out.println(list);

        Iterator<Integer> itr1 = list.iterator();

        list.add(4);

        System.out.println(list);



        Iterator<Integer> itr2 = list.iterator();

        System.out.println("====Verify Iterator 1 content====");

        itr1.forEachRemaining(System.out::println); //1,2,3

        System.out.println("====Verify Iterator 2 content====");

        itr2.forEachRemaining(System.out::println); //1,2,3,4
    }
}
