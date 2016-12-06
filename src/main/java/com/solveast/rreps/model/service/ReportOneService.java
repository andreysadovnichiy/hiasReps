package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.ReportOneDao;
import com.solveast.rreps.model.queries.family.FamilyReport;
import com.solveast.rreps.model.queries.one.IntroData;
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
        Map<Long, List<FamilyReport>> families = familyService.getFamily();

        //фикс внесенных задним числом
        rawData = fixUnhcrdate(rawData, from.toLocalDateTime(), to.toLocalDateTime());
        //конвертация запроса во входные данные
        List<IntroData> introData = toIntroData1(rawData);
        //просчет возроста для апликанта и установка статуса more18 в true
        introData = fixAgeForApplicant(introData);
        //Удаляем семьи которые не вошли в отчетный период
        families = fixFamiliesUpToQueredApplicant(introData, families);
        //Добавляем количество членов семьи в отчет
        introData = fixFamily(introData, families);

        Map<String, Report1> report = createReport(introData, families);

        data.put("introData", introData);
        data.put("report", report);
        return data;
    }

    private Map<String, Report1> createReport(List<IntroData> introData, Map<Long, List<FamilyReport>> families) {
        Map<String, Report1> reportMap = new HashMap<>();

        for (IntroData item : introData) {
            Report1 report = reportMap.get(item.getIso3166_3());
            if (report == null)
                report = new Report1();

            report.setIso3166_3(item.getIso3166_3());
            if ("m".equals(item.getSexCd()) && item.getMore18())
                report.setMale(report.getMale() + 1);
            else if ("f".equals(item.getSexCd()) && item.getMore18())
                report.setFemale(report.getFemale() + 1);
            else if ("m".equals(item.getSexCd()) && !item.getMore18())
                report.setBoys(report.getBoys() + 1);
            else if ("f".equals(item.getSexCd()) && !item.getMore18())
                report.setGirls(report.getGirls() + 1);

            //Добавляем семью
            List<FamilyReport> family = families.get(item.getClientId());

            if (family != null) {
                for (FamilyReport entry : family) {
                    if ("m".equals(entry.getSexCd()) && entry.getMore18())
                        report.setMale(report.getMale() + 1);
                    else if ("f".equals(entry.getSexCd()) && entry.getMore18())
                        report.setFemale(report.getFemale() + 1);
                    else if ("m".equals(entry.getSexCd()) && !entry.getMore18())
                        report.setBoys(report.getBoys() + 1);
                    else if ("f".equals(entry.getSexCd()) && !entry.getMore18())
                        report.setGirls(report.getGirls() + 1);
                }
            }

            reportMap.put(item.getIso3166_3(), report);
        }

        return reportMap;
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

    private List<IntroData> toIntroData1(List<Query1> sources) {
        List<IntroData> targets = new ArrayList<>();

        for (Query1 item : sources) {
            IntroData target = new IntroData();
            target.setClientId(item.getClientId());
            target.setApplicant(item.getApplicant());
            target.setBirthDate(item.getBirthDate());
            target.setSexCd(item.getSexCd());
            target.setRegisterTime(item.getRegisterTime());
            target.setUnhcrDate(item.getUnhcrDate());
            target.setApplicantId(item.getApplicantId());
            target.setIso3166_3(item.getIso3166_3());

            targets.add(target);
        }

        return targets;
    }

    private List<IntroData> fixAgeForApplicant(List<IntroData> sources) {
        int nowYear = LocalDateTime.now().getYear();
        int age;

        for (IntroData item : sources) {
            LocalDateTime birthDate = item.getBirthDate();
            if (birthDate == null)
                item.setAge(100);
            else {
                age = nowYear - birthDate.getYear();
                item.setAge(age);
            }
            item.setMore18(true);
        }

        return sources;
    }

    private Map<Long, List<FamilyReport>> fixFamiliesUpToQueredApplicant(List<IntroData> introData, Map<Long, List<FamilyReport>> families) {
        Map<Long, List<FamilyReport>> target = new HashMap<>();

        for (IntroData item : introData) {
            List<FamilyReport> family = families.get(item.getClientId());
            if (family != null && family.size() > 0) {
                target.put(item.getClientId(), family);
            }
        }

        return target;
    }

    private List<IntroData> fixFamily(List<IntroData> sources, Map<Long, List<FamilyReport>> families) {
        for (IntroData item : sources) {
            List<FamilyReport> family = families.get(item.getClientId());
            if (family == null)
                item.setFamilyMembersNumber(1);
            else
                item.setFamilyMembersNumber(family.size() + 1);
        }

        return sources;
    }
}
