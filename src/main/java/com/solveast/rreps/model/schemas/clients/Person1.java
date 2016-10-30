package com.solveast.rreps.model.schemas.clients;

/**
 * Created by Андрей on 29.10.2016.
 */
public class Person1 {
    private Long id;
    private Integer age;

    public Person1(Long id, Integer age) {
        this.id = id;
        this.age = age;
    }

    public Person1() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age='" + age + '\'' +
                '}';
    }
}
