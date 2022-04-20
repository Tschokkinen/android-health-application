package com.ericaskari.healthapplication;

public class User {
    private String name;
    private int age;
    private int height;
    private int weight;
    private String longTermIllness;

    public User(String name, int age, int height, int weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public User(String name, int age, int height, int weight, String longTermIllness) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.longTermIllness = longTermIllness;
    }
}
