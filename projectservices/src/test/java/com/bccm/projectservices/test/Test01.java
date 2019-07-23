package com.bccm.projectservices.test;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Test01 {

    @Test
    public void testDate(){

        LocalDateTime dateTime = LocalDateTime.now();

        Timestamp date02 = new Timestamp(System.currentTimeMillis());

        System.err.println(dateTime);
        System.err.println(date02);


    }
    @Test
    public void testInteger(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 2L;

        System.err.println(c==d);
        System.err.println(e==f);
        System.err.println(c==(a+b));
        System.err.println(c.equals(a+b));
        System.err.println(g==(a+b));
        System.err.println(g.equals(a+b));



    }
}
