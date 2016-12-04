package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.queries.FamilyQuery;
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

    public Map<Long, Integer> getFamilyMap() {
        Map<Long, Integer> familyMap = new HashMap<>();

        List<FamilyQuery> family = dao.getFamily();

        for (FamilyQuery item : family) {
            if (item.getClientId() != null && item.getClientId() > 0)
                familyMap.put(item.getClientId(), 0);

            if (item.getApplicantId() != null && item.getApplicantId() > 0)
                familyMap.put(item.getApplicantId(), 0);
        }

        Integer totalForClient;
        Integer totalForApplicant;
        for (FamilyQuery item : family) {
            if(item.getApplicantId() == 205)
                totalForClient=0;

            totalForClient = familyMap.get(item.getClientId());
            familyMap.put(item.getClientId(), totalForClient + 1);

            totalForApplicant = familyMap.get(item.getApplicantId());
            if (totalForApplicant != null && totalForApplicant > 0)
                familyMap.put(item.getApplicantId(), totalForApplicant + 1);
        }

        return familyMap;
    }
}
