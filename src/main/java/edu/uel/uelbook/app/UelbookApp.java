package edu.uel.uelbook.app;

import edu.uel.uelbook.exception.NoSuchCodeException;
import edu.uel.uelbook.exception.PersonExistsException;
import edu.uel.uelbook.model.Person;
import edu.uel.uelbook.service.UelbookService;
import edu.uel.uelbook.service.UelbookServiceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UelbookApp {

    public static void main(String[] args) {
        UelbookService uelbookService = new UelbookServiceImpl();
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(args[0]);
            bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(";");
                try {
                    Person person = uelbookService.addPerson(data[0], data[1], data[2]);
                    System.out.println(uelbookService.getPerson(person.getCode()));
                    for (int index = 3; index < data.length; index++) {
                        uelbookService.addFriendShip(person.getCode(), data[index]);
                    }
                } catch (NoSuchCodeException | PersonExistsException ex) {
                    System.out.print(ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
