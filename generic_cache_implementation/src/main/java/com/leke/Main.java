package com.leke;

public class Main {
    public static void main(String[] args) {
        Cache<Integer> c = new Cache<>(5);
        c.put(50);
        c.put(40);
        c.put(30);
        c.put(20);
        c.put(10);
        c.put(1000);

        c.print();

        // int removedValue = c.remove(30);
        // System.out.println("Remved the value " + removedValue + " from the cache");
        // c.print();
    }
}