package com.solveast.rreps.model.service;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.queries.family.FamilyQuery;
import com.solveast.rreps.model.queries.family.FamilyReport;
import com.solveast.rreps.model.queries.two.FamilyReport2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Map<Long, List<FamilyReport>> getFamily() {
        Map<Long, List<FamilyReport>> target = new HashMap<>();
        List<FamilyQuery> source = dao.getFamily();

        int age;
        for (FamilyQuery item : source) {
            //если член семьи заявителя-апликанта
            if (item.getApplicantId() > 0) {
                FamilyReport report = item.toFamilyReport();

                //если есть дата рождения
                if (report.getBirthDate() != null) {
                    age = DateUtils.getAge(report.getBirthDate());
                    report.setAge(age);
                    if (age >= 18)
                        report.setMore18(true);
                    else
                        report.setMore18(false);
                }
                //если нет даты рождения
                else {
                    if ("CHI".equals(item.getUnRelationshipCd()))
                        report.setMore18(false);
                    else
                        report.setMore18(true);
                }
                //Формируем Map семьи
                List<FamilyReport> family = target.get(report.getApplicantId());
                if (family == null)
                    family = new ArrayList<>();

                family.add(report);
                target.put(report.getApplicantId(), family);
            }
        }

        return target;
    }
}
