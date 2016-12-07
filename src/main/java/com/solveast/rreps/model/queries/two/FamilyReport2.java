package com.solveast.rreps.model.queries.two;

import com.solveast.rreps.model.DateUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 07.12.2016.
 */
public class FamilyReport2 {
    private Long clientId;
    private Long applicantId;
    private String sexCd;
    private LocalDateTime birthDate;
    private Integer age;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getSexCd() {
        return sexCd;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        setAge(birthDate);
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAge(Timestamp age) {
        if (age != null)
            this.age = DateUtils.getAge(age.toLocalDateTime());
    }

    public void setAge(LocalDateTime age) {
        this.age = DateUtils.getAge(age);
    }

    @Override
    public String toString() {
        return "FamilyReport2{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                ", sexCd='" + sexCd + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                '}';
    }
}


