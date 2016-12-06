package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.ReportOneDao;
import com.solveast.rreps.model.queries.one.IntroData1;
import com.solveast.rreps.model.queries.one.Query1;
import com.solveast.rreps.model.queries.one.Report1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Андрей on 28.10.2016.
 */

/*
1. запрос аппликант равно true
2. fixUnhcrdate
3. birthday to age для аппликанта
4. получить список членов семей с birthday
5. birthday to age для членов семей
*/

@Service
public class ReportOneService {
    @Autowired
    private ReportOneDao reportDao;
    @Autowired
    private FamilyService familyService;

    public Map<String, Object> getData(Timestamp from, Timestamp to) {
        Map<String, Object> data = new HashMap<>();

        List<Query1> rawData = reportDao.getQuery(from, to);
        //фикс внесенных задним числом
        rawData = fixUnhcrdate(rawData, from.toLocalDateTime(), to.toLocalDateTime());
        //конвертация запроса во входные данные
        List<IntroData1> introData = toIntroData1(rawData);
        //просчет возроста для апликанта и установка статуса more18 в true
        introData = fixAgeForApplicant(introData);


        introData = fixFamily(introData);



        Map<String, Report1> report = processData(rawData);

        data.put("introData", introData);
        data.put("report", report);
        return data;
    }


    private Map<String, Report1> processData(List<Query1> clients) {
        int adultYear = 18;
        int nowYear = LocalDateTime.now().getYear();

        Map<String, Report1> report1Map = new HashMap<>();

        for (Query1 client : clients) {
            String country = client.getIso3166_3();
            Report1 report = report1Map.get(country);
            if (report == null)
                report = new Report1();

            if (client.getBirthDate() == null) {
                if (client.getApplicant() == true && client.getSexCd().equals("m"))
                    report.setMale(report.getMale() + 1);
                else if (client.getApplicant() == true && client.getSexCd().equals("f"))
                    report.setFemale(report.getFemale() + 1);
                else if (client.getApplicant() == false && client.getUn_relationship_cd().equals("CHI") && client.getSexCd().equals("m"))
                    report.setBoys(report.getBoys() + 1);
                else if (client.getApplicant() == false && client.getUn_relationship_cd().equals("CHI") && client.getSexCd().equals("f"))
                    report.setGirls(report.getGirls() + 1);

            } else {
                int birthDateYear = nowYear - 100;
                birthDateYear = client.getBirthDate().getYear();

                if (nowYear - birthDateYear < adultYear) {
                    if (client.getSexCd().equals("m"))
                        report.setBoys(report.getBoys() + 1);
                    else
                        report.setGirls(report.getGirls() + 1);
                } else {
                    if (client.getSexCd().equals("m"))
                        report.setMale(report.getMale() + 1);
                    else
                        report.setFemale(report.getFemale() + 1);
                }
            }
            report.setIso3166_3(country);
            report1Map.put(country, report);
        }
        return report1Map;
    }

    private List<Query1> fixUnhcrdate(List<Query1> query, LocalDateTime from, LocalDateTime to) {
        List<Query1> forDelete = new ArrayList<>();

        for (Query1 item : query) {
            if (item.getUnhcrDate() != null)
                if (item.getUnhcrDate().isBefore(from))
                    forDelete.add(item);
        }

        query.removeAll(forDelete);
        return query;
    }

    private List<IntroData1> toIntroData1(List<Query1> sources) {
        List<IntroData1> targets = new ArrayList<>();

        for (Query1 item : sources) {
            IntroData1 target = new IntroData1();
            target.setClientId(item.getClientId());
            target.setApplicant(item.getApplicant());
            target.setBirthDate(item.getBirthDate());
            target.setSexCd(item.getSexCd());
            target.setRegisterTime(item.getRegisterTime());
            target.setUnhcrDate(item.getUnhcrDate());
            target.setApplicantId(item.getApplicantId());
            target.setIso3166_3(item.getIso3166_3());
        }

        return targets;
    }

    private List<IntroData1> fixAgeForApplicant(List<IntroData1> sources) {
        int nowYear = LocalDateTime.now().getYear();
        int age;

        for (IntroData1 item : sources) {
            LocalDateTime birthDate = item.getBirthDate();
            if(birthDate == null)
                item.setAge(100);
            age = nowYear - birthDate.getYear();
            item.setAge(age);
            item.setMore18(true);
        }

        return sources;
    }


    private List<IntroData1> fixFamily(List<IntroData1> sources) {
        /*Map<Long, Integer> familyMap = familyService.getFamilyMapWithBirthdays();

        for (Query1 item : rawData)
            item.setApplicantsNumber(familyMap.get(item.getClientId()));

        return rawData;*/
        return sources;
    }
}
