package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.ReportOneDao;
import com.solveast.rreps.model.schemas.Query1;
import com.solveast.rreps.model.schemas.Report1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 25.11.2016.
 */

/*

*/
@Service
public class ReportTwoService {
    @Autowired
    private ReportOneDao reportDao;

    public Map<String, Object> getData(Timestamp from, Timestamp to) {
        Map<String, Object> data = new HashMap<>();

        List<Query1> rawData = reportDao.getQuery(from, to);
        data.put("rawData", rawData);

        Map<String, Report1> proccessedData = processData(rawData);
        data.put("proccessedData", proccessedData);

        return data;
    }

    public Map<String, Report1> processData(List<Query1> clients) {
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
}
