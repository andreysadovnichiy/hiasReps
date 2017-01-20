package com.solveast.rreps.model.queries.family;

import com.solveast.rreps.model.queries.base.BaseQuery;

import java.util.*;

/**
 * Created by Андрей on 12.12.2016.
 */
public class FamilyUtils {
    public static List<Family> getFamilies(Collection<Person> clients, List<BaseQuery> familyQueries) {
        List<Family> families = new ArrayList<>();

        for (Person person : clients) {
            List<Person> familyMembers = new ArrayList<>();
            for (BaseQuery familyQuery : familyQueries) {
                if ((long) familyQuery.getApplicantId() == (long) person.getClientId()) {
                    familyMembers.add(familyQuery.toPerson());
                }
            }

            families.add(new Family(person, familyMembers));
        }
        return families;
    }

    public static Set<Person> cleanNotAplicant(Set<Person> personApplicant) {
        Collection<Person> toDelete = new HashSet<>();

        for (Person person : personApplicant)
            if (person.getApplicant() == false)
                toDelete.add(person);

        personApplicant.removeAll(toDelete);
        return personApplicant;
    }
}
