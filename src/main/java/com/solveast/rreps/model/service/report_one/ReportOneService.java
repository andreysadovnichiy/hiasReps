package com.solveast.rreps.model.service.report_one;

import com.solveast.rreps.model.schemas.clients.dao.FStatusDao;
import com.solveast.rreps.model.schemas.clients.dao.TClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TClientDao tClientDao;
    @Autowired
    private FStatusDao fStatusDao;

    public String getReport() {
        return tClientDao.getClientById(1L).toString();
    }
}
