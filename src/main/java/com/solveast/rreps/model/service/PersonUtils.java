package com.solveast.rreps.model.service;

import com.solveast.rreps.model.queries.family.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 10.12.2016.
 */
public class PersonUtils {

//  удаляем которые внесены в систему задним числом unhcr_date
    public static List<? extends Person> fixUnhcrDate(List<? extends Person> persons, LocalDateTime from){
        List<Person> forDelete = new ArrayList<>();
        LocalDateTime unch;
        for (Person item : persons) {
            unch = item.getUnhcrDate();
            if(unch != null){
                if (unch.isBefore(from))
                    forDelete.add(item);
            }
        }
        persons.removeAll(forDelete);
        return persons;
    }

    public static boolean isUnhcrDateAfterFrom(Person person, LocalDateTime from) {
        if(person.getUnhcrDate() == null)
            return true;
        if(person.getUnhcrDate().isAfter(from))
            return true;
        return false;
    }
}
