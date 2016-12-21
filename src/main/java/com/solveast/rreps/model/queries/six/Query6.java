package com.solveast.rreps.model.queries.six;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.base.BaseQuery;
import com.solveast.rreps.model.queries.family.Person;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 18.12.2016.
 */
public class Query6 extends BaseQuery {
    private String iso3166_3;

    private LocalDateTime realTimeStart;
    private LocalDateTime realTimeStop;
    private LocalDateTime scheduledTimeStart;
    private LocalDateTime scheduledTimeStop;

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public LocalDateTime getRealTimeStart() {
        return realTimeStart;
    }

    public void setRealTimeStart(LocalDateTime realTimeStart) {
        this.realTimeStart = realTimeStart;
    }

    public void setRealTimeStart(Timestamp realTimeStart) {
        this.realTimeStart = DateUtils.toLocalDateTime(realTimeStart);
    }

    public LocalDateTime getRealTimeStop() {
        return realTimeStop;
    }

    public void setRealTimeStop(LocalDateTime realTimeStop) {
        this.realTimeStop = realTimeStop;
    }

    public void setRealTimeStop(Timestamp realTimeStop) {
        this.realTimeStop = DateUtils.toLocalDateTime(realTimeStop);
    }

    public LocalDateTime getScheduledTimeStart() {
        return scheduledTimeStart;
    }

    public void setScheduledTimeStart(LocalDateTime scheduledTimeStart) {
        this.scheduledTimeStart = scheduledTimeStart;
    }

    public void setScheduledTimeStart(Timestamp scheduledTimeStart) {
        this.scheduledTimeStart = DateUtils.toLocalDateTime(scheduledTimeStart);
    }

    public LocalDateTime getScheduledTimeStop() {
        return scheduledTimeStop;
    }

    public void setScheduledTimeStop(LocalDateTime scheduledTimeStop) {
        this.scheduledTimeStop = scheduledTimeStop;
    }

    public void setScheduledTimeStop(Timestamp scheduledTimeStop) {
        this.scheduledTimeStop = DateUtils.toLocalDateTime(scheduledTimeStop);
    }

    public Person toPerson() {
        Person person = new Person();

        person.setClientId(getClientId());
        person.setApplicantId(getApplicantId());
        person.setRegisterTime(getRegisterTime());
        person.setUnhcrDate(getUnhcrDate());
        person.setSexCd(getSexCd());
        person.setBirthDate(getBirthDate());
        person.setUnRelationshipCd(getUnRelationshipCd());
        person.setFileStatusId(getFileStatusId());
        person.setIso3166_3(getIso3166_3());
        return person;
    }

    @Override
    public String toString() {
        return "Query6{" +
                "iso3166_3='" + iso3166_3 + '\'' +
                ", realTimeStart=" + realTimeStart +
                ", realTimeStop=" + realTimeStop +
                ", scheduledTimeStart=" + scheduledTimeStart +
                ", scheduledTimeStop=" + scheduledTimeStop +
                '}';
    }
}
