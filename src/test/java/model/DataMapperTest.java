package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DataMapperTest {


    @BeforeEach
    void setUp() {
        DataMapper.createTable();
        DataMapper.fillTable();
    }

    @AfterEach
    void tearDown() {
        DataMapper.deleteTable();
    }

    @Test
    void listAllByName() {
            ArrayList<String> namesExpected = new ArrayList<>();
            namesExpected.add("Henning Dahl");
            namesExpected.add("Hannah Dinesen");
            namesExpected.add("Amin Kotchic");
            namesExpected.add("Harun Dupsmith");

            ArrayList<String> namesActual = DataMapper.ListAllByName();

            assertEquals(namesExpected,namesActual);

    }

    @Test
    public void seePersonDetails(){
        Person personExpected = new Person(4,"Harun","Dupsmith","kothis55","+4540677667","Rolighedsvej 104, 2100 Kbh Ø");
        Person personActual = DataMapper.getAllPersons().get(3);
        //AssertEqual not working on objekt. Memory spot is different.
        assertEquals(personExpected.toString(),personActual.toString());
    }

    @Test
    public void editUserDetails(){
        Person personExpected = DataMapper.getAllPersons().get(0);
        Random rnd = new Random();
        DataMapper.editDetails(personExpected, rnd.nextInt(1000000));

        Person personActual = DataMapper.getAllPersons().get(0);
        System.out.println(personExpected);
        assertNotEquals(personExpected.toString(),personActual.toString());

    }

}