package com.benchmark.model;

public class User {
    public long id;
    public String name;
    public String email;
    public int age;
    public boolean active;

    public User() {}

    public User(long id, String name, String email, int age, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.active = active;
    }
}
