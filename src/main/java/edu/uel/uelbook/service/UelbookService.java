package edu.uel.uelbook.service;

import edu.uel.uelbook.exception.NoSuchCodeException;
import edu.uel.uelbook.exception.PersonExistsException;
import edu.uel.uelbook.model.Person;

import java.util.List;
import java.util.Set;

public interface UelbookService {

    public Person addPerson(final String code, final String firstName, final String surname) throws PersonExistsException;
    public String getPerson(String code) throws NoSuchCodeException;
    public void addFriendShip(String personCode, String personFriendCode) throws NoSuchCodeException;
    public Set<Person> listFriends(String code)  throws NoSuchCodeException;
    public Set<Person> friendsOfFriends(String code) throws NoSuchCodeException;
    public Set<String> mostPopular() throws NoSuchCodeException;
    public Set<String> mostInfluencer() throws NoSuchCodeException;
}
