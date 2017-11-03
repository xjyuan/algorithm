package com.cy.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenying on 2017/11/3.
 */
public class EquallyDistributed {

    @Test
    void test(){

    }
}

class CSR{
    String name;
    int work;
    List<Zone> zones = new ArrayList<>();

    public CSR(String name,int work){
        this.name = name;
        this.work = work;
    }
}

class Zone{
    String name;
    int work;
}
