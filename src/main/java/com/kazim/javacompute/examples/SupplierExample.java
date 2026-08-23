package com.kazim.javacompute.examples;

import java.util.function.Supplier;

public final class SupplierExample {

    static final Supplier<String> GREETING_SUPPLIER = () -> "hello";

    private SupplierExample() {
    }

    public static String getGreeting() {
        return GREETING_SUPPLIER.get();
    }

    public static void main(String[] args) {
        System.out.println(getGreeting());
    }
}
