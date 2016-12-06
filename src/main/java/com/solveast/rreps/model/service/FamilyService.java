package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.queries.family.FamilyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 04.12.2016.
 */
@Service
public class FamilyService {
    @Autowired
    private FamilyDao dao;

    private enum Family {PLAIN, WITH_BIRTHDAYS}

    private List<FamilyQuery> get(Family family) {
        if (family == Family.PLAIN) {
            List<FamilyQuery> families = dao.getFamily();
            return families;
        }
        if (family == Family.WITH_BIRTHDAYS) {
            List<FamilyQuery> families = dao.getFamilyWithBirthdays();
            return families;
        }

        return null;
    }

    public Map<Long, FamilyQuery> getFamilyMapWithBirthdays() {
        List<FamilyQuery> family = get(Family.WITH_BIRTHDAYS);
        Map<Long, Integer> clientFamilyMembersNumber = getClientTotalFamilyNumber(family);

        Map<Long, FamilyQuery> familyMap = new HashMap<>();
        for (FamilyQuery item : family) {
            item.setFamilyMembersNumber(clientFamilyMembersNumber.get(item.getClientId()));
        }

        return familyMap;
    }

    public Map<Long, Integer> getFamilyMap() {
        return getClientTotalFamilyNumber(get(Family.PLAIN));
    }

    private Map<Long, Integer> getClientTotalFamilyNumber(List<FamilyQuery> family) {
        Map<Long, Integer> familyMap = new HashMap<>();

        for (FamilyQuery item : family) {
            if (item.getClientId() != null && item.getClientId() > 0)
                familyMap.put(item.getClientId(), 0);

            if (item.getApplicantId() != null && item.getApplicantId() > 0)
                familyMap.put(item.getApplicantId(), 0);
        }

        Integer totalForClient;
        Integer totalForApplicant;
        for (FamilyQuery item : family) {
            totalForClient = familyMap.get(item.getClientId());
            familyMap.put(item.getClientId(), totalForClient + 1);

            totalForApplicant = familyMap.get(item.getApplicantId());
            if (totalForApplicant != null && totalForApplicant > 0)
                familyMap.put(item.getApplicantId(), totalForApplicant + 1);
        }

        return familyMap;
    }
}
