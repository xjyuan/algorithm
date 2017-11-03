package com.cy.demo;

import org.junit.Test;

/**
 * Created by chenying on 2017/11/3.
 */
public class Java8Test {

    @Test
    public void test() {
        MyRunnable intBinaryOperator = () -> {
            System.out.println(111);
        };
        intBinaryOperator.run();
    }
}

@FunctionalInterface
interface MyRunnable {
    void run();
}