package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.ReportOneDao;
import com.solveast.rreps.model.queries.Query1;
import com.solveast.rreps.model.queries.Report1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Андрей on 28.10.2016.
 */

/*

Необходимо формировать следующие отчеты и показатели:
1.	Показатель Количество новых клиентов, посетивших офис за отчетный период:
за основу берем дату создания карточки клиента в системе (clients.t_client:register_time).
При этом надо проконтролировать дату регистрации клиента (clients.t_registration_form:unhcr_date).
Если вторая дата меньше первой, то в анализе участвует вторая дата (т.е. карточка клиента была внесена
в систему задним числом). Т.к. этот показатель связан с количеством клиентов, то за отчетный период
клиента учитываем только один раз. Для того, чтобы посчитать клиентов, первоначально берем в расчет
только тех клиентов, которые являются заявителями/апликантами (clients.t_client:applicant=TRUE).
В количество посетителей также включаем членов семьи апликанта, т.е. тех клиентов, у которых
clients.t_client:applicant_id = clients.t_client:client_id апликанта, вошедшего в расчет. К этому
показателю нужно показать отчет:

##	    Nationality	                           Adults	                         Children
		                                 Males	      Females	           Males	      Females

1    	   Страна
   (clients.t_client:iso3166_3)	   Число муж(>=18)	Число жен(>=18)	    Число мал(<18)	Число дев(<18)

 */


@Service
public class ReportOneService {
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
