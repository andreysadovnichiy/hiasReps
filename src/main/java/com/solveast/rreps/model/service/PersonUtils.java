package com.solveast.rreps.model.service;

import com.solveast.rreps.model.queries.family.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 10.12.2016.
 */
public class PersonUtils {

    public static boolean isUnhcrDateAfterFrom(Person person, LocalDateTime from) {
        if (person.getUnhcrDate() == null)
            return true;
        if (person.getUnhcrDate().isAfter(from))
            return true;
        return false;
    }

    public static boolean isIso3166_3Exists(Person person) {
        return !(person.getIso3166_3() == null);
    }

    public static boolean isSexCdExists(Person person) {
        return !(person.getSexCd() == null);
    }
/*
    public static boolean isCanGetMoreOrLessThen18(Person person) {
        if (person.getBirthDate() != null)
            return true;
        if (person.getUnRelationshipCd() == "CHI")
            return true;
        return false;
    }*/
}
