package com.dagemen;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/app*.xml", "classpath:spring-mvc/spring-*.xml"})
public class Test {
    public static void main(String[] args) {
        List<? extends Number> list1 = Arrays.asList(1, 2, 3, 5, 6);
//        list1.add(new Integer(1));
        for (Number number : list1) {
            System.out.println(number);
        }

        System.out.println("---------------分割线----------------");
        List<? super Number> list2 = new ArrayList<>();
        list2.add(new Integer(1));
        list2.add(new Long(1l));
        list2.add(new Float(1f));

        for (Object o : list2) {

            System.out.println(o);
        }

    }
}
