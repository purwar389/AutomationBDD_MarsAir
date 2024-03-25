package com.gp.au.environment;

import java.util.HashMap;

public class test {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String[] input = {"1", "1", "3", "3", "2", "11", "6", "2", "2", "2", "2", "2", "2", "2", "2", "2"};
        HashMap<String, Integer> counts = new HashMap<>();

        for (String s : input) {
            counts.put(s, counts.getOrDefault(s, 0) + 1);
        }

        for (String s : counts.keySet()) {
            if (counts.get(s) != null & counts.get(s) > 1) {
                System.out.println(s + "counts " + counts.get(s));
            }

        }

    }

}