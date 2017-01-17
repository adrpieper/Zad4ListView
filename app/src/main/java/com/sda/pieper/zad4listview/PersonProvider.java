package com.sda.pieper.zad4listview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-01-17.
 */

public class PersonProvider {


    public List<Person> getPersons() {

        List<Person> persons = new ArrayList<>();
        Person jan = new Person();
        jan.setName("Jan");
        jan.setSurname("Kowalski");
        Person janina = new Person();
        janina = new Person();
        janina.setName("Janina");
        janina.setSurname("Nowak");
        persons.add(jan);
        persons.add(janina);

        return persons;
    }
}
