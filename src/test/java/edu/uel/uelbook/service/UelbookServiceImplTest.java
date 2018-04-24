package edu.uel.uelbook.service;

import edu.uel.uelbook.exception.NoSuchCodeException;
import edu.uel.uelbook.exception.PersonExistsException;
import edu.uel.uelbook.model.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UelbookServiceImplTest {

    private UelbookService uelbookService;

    @Before
    public void initService() {
        uelbookService = new UelbookServiceImpl();
    }

    @Test
    public void addPerson() {
        try {
            Person person = uelbookService.addPerson("FAB01", "Fabio", "Gicci Hernandes");
        } catch (PersonExistsException ex) {}
    }

    @Test
    public void addPersonWithExistException() {
        try {
            Person person = uelbookService.addPerson("FAB01", "Fabio", "Gicci Hernandes");
        } catch (PersonExistsException ex) {
            assertEquals(ex.getMessage(), "Person exists! Code: FAB01");
        }
    }

    @Test
    public void getPerson() {
        try {
            assertEquals("FAB01 Fabio Gicci Hernandes", uelbookService.getPerson("FAB01"));
        } catch (NoSuchCodeException e) {}
    }

    @Test
    public void addPersonNoSuchCodeException() {
        try {
            uelbookService.getPerson("FER01");
        } catch (NoSuchCodeException ex) {
            assertEquals(ex.getMessage(), "Person does not exists! Code: FER01");
        }
    }

    @Test
    public void addFriendShip() {
        try {
            Person fer = uelbookService.addPerson("FER01", "Fernando", "Gicci Hernandes");
            Person fel = uelbookService.addPerson("FEL01", "Felipe", "Gicci Hernandes");
            uelbookService.addFriendShip("FAB01", "FEL01");
            assertEquals(true, uelbookService.listFriends("FAB01").contains(fel));
            assertEquals(false, uelbookService.listFriends("FER01").contains(fel));
        } catch (PersonExistsException | NoSuchCodeException ex) {}
    }

    @Test
    public void listFriends() {
    }

    @Test
    public void friendsOfFriends() {
    }

    @Test
    public void mostPopular() {
    }

    @Test
    public void mostInfluencer() {
    }

    @Test
    public void fullTestWithFile() {
    }
}