package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("First time:");
        SomeBean first = (new Injector()).inject(new SomeBean(), true);
        first.foo();
        System.out.println("Second time:");
        SomeBean second = (new Injector().inject(new SomeBean(), false));
        second.foo();
    }
}