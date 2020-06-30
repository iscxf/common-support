package com.iscxf.common.common.model;

import lombok.Data;

/**
 * @author chenxf
 * Created on 2020/4/3
 */
@Data
public class Person {

    private String name;

    private Integer age;

    private Double height;

    private String email;

    private Address address;

    public Person() {
    }

    public Person(String name, Integer age, Double height, String email) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.email = email;
        this.address = new Address();
    }

}
