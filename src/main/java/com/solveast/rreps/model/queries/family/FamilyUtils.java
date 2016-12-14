package com.solveast.rreps.model.queries.family;

import com.solveast.rreps.model.queries.base.BaseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 12.12.2016.
 */
public class FamilyUtils {
    public static List<Family> getFamilies(List<Person> clients, List<BaseQuery> familyQueries) {
        List<Family> families = new ArrayList<>();

        for (Person person : clients) {
            List<Person> familyMembers = new ArrayList<>();
            for (BaseQuery familyQuery : familyQueries) {
                if ((long) familyQuery.getApplicantId() == (long) person.getClientId()) {
                    familyMembers.add(familyQuery.toPerson());
                }
            }

            Family newFamily = new Family();
            newFamily.setClient(person);
            newFamily.setFamily(familyMembers);

            families.add(newFamily);
        }
        return families;
    }
}
