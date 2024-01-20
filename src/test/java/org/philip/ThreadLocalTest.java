package org.philip;


import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet(){
        // 提供一個 ThreadLocal 對象
        ThreadLocal tl = new ThreadLocal();

        // 開啟兩個線程
        new Thread(() -> {
            tl.set("小紅");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "紅色").start();

        new Thread(() -> {
            tl.set("小白");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "白色").start();
    }


}
