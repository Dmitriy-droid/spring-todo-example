package com.tsds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    class Car {
        public String name;
    }

    public void example() {
        List<String> l = new ArrayList<>();
        l.add("qwe");
        l.add("asd");

        l.forEach((el) -> {
            System.out.println(el);
            System.out.println(el);
        });

        for (String elem : l) {
            System.out.println(elem);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        int i = 1;
        boolean b = true;
        char c = 'a';
        float f = 1.5f;
        double d = 1.5;
        byte bt = 12;
        int i2 = 0x12;

        Integer i3 = 1;
        Boolean bb = false;
        Character cc = 'a';
        Double dd = 1.5;


        List<Integer> l2 = new ArrayList<>();
        l2.add(123);


        Car ccc = new Car();

        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        car.name = "Ford";
        cars.add(car);
        cars.add(car);

        System.out.println(l2);


        Map<String, Integer> m = new HashMap<>();
        m.put("key1", 12);

        var v = m.get("key1");

        /**
         *            {"name": 123}
         *
         *            {"key1": 12}
         *
         *            {"text": "ab", "id": 1}
         *            [1, 2, 3]
         *            ["qwe", "asd"]
         *
         *
         *            [{"name": "Ford"}, {"name": "Ford"}]
         */

    }
}
