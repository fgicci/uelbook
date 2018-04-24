package edu.uel.uelbook.service;

import edu.uel.uelbook.exception.NoSuchCodeException;
import edu.uel.uelbook.exception.PersonExistsException;
import edu.uel.uelbook.model.Person;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class UelbookServiceImpl implements UelbookService {

    private Map<String, Person> data;

    public UelbookServiceImpl() {
        this.data = new HashMap<String, Person>();
    }

    public UelbookServiceImpl(Map<String, Person> data) {
        this.data = data;
    }

    @Override
    public Person addPerson(String code, String firstName, String surname) throws PersonExistsException {
        Person person = new Person(code, firstName, surname, null);
        if (data.containsKey(code)) throw new PersonExistsException("Person exists!");
        return data.put(code, person);
    }

    @Override
    public String getPerson(String code) throws NoSuchCodeException {
        Person person = findPerson(code);
        return person.getCode() + " " + person.getFirst() + " " + person.getLast();
    }

    @Override
    public void addFriendShip(String personCode, String personFriendCode) throws NoSuchCodeException {
        Person person = findPerson(personCode);
        Person friend = findPerson(personFriendCode);
        person.getFriends().put(personFriendCode, friend);
        friend.getFriends().put(personCode, person);
        data.put(personCode, person);
        data.put(personFriendCode, friend);
    }

    private Person findPerson(String code) throws NoSuchCodeException {
        if (!data.containsKey(code)) throw new NoSuchCodeException("Person does not exists! Code:" + code);
        return data.get(code);
    }

    @Override
    public Set<Person> listFriends(String code) throws NoSuchCodeException {
        return findPerson(code).getFriends().values().stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Person> friendsOfFriends(String code) throws NoSuchCodeException {
        Set<Person> fof = new HashSet<Person>();
        for (Person friend : listFriends(code)) {
            fof.addAll(listFriends(friend.getCode()));
        }
        return fof;
    }

    @Override
    public Set<String> mostPopular() {
        Map<Integer, Set<Person>> result = new TreeMap<Integer, Set<Person>>((Comparator<Integer>)(o1, o2) -> o2.compareTo(o1));
        data.values().stream().forEach(person -> {
            try {
                result.put(listFriends(person.getCode()).size(), listFriends(person.getCode()));
            } catch (NoSuchCodeException ex) {
                result.put(0, new HashSet<Person>());
            }
        });
        Map.Entry<Integer, Set<Person>> entry = result.entrySet().iterator().next();
        return entry.getValue().stream().map(Person::getCode).collect(Collectors.toSet());
    }

    @Override
    public Set<String> mostInfluencer() {
        Map<Integer, Set<Person>> result = new TreeMap<Integer, Set<Person>>((Comparator<Integer>)(o1, o2) -> o2.compareTo(o1));
        data.values().stream().forEach(person -> {
            try {
                result.put(friendsOfFriends(person.getCode()).size(), friendsOfFriends(person.getCode()));
            } catch (NoSuchCodeException ex) {
                result.put(0, new HashSet<Person>());
            }
        });
        Map.Entry<Integer, Set<Person>> entry = result.entrySet().iterator().next();
        return entry.getValue().stream().map(Person::getCode).collect(Collectors.toSet());
    }
}