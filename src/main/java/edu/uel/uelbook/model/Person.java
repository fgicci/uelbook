package edu.uel.uelbook.model;

import java.util.List;
import java.util.Map;

public class Person {

    private String code;
    private String firstName;
    private String surname;
    private Map<String, Person> friends;

    public Person() {}

    public Person(String code, String firstName, String surname, Map<String, Person> friends) {
        this.code = code;
        this.firstName = firstName;
        this.surname = surname;
        this.friends = friends;;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirst() {
        return firstName;
    }

    public void setFirst(String firstName) {
        this.firstName = firstName;
    }

    public String getLast() {
        return surname;
    }

    public void setLast(String surname) {
        this.surname = surname;
    }

    public Map<String, Person> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, Person> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return code != null ? code.equals(person.code) : person.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "code=" + code +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", friends=" + friends +
                '}';
    }
}
