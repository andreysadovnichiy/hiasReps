package com.solveast.rreps.model.queries.three;

import java.time.LocalDateTime;

/**
 * Created by Андрей on 10.12.2016.
 */
public class Report3 {
    private Long clientId;
    private String iso3166_3;
    private Integer familyMembers;
    private String fio;
    private String unhcrNum;
    private LocalDateTime unhcrDate;
    private String fileStatusName;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getUnhcrDate() {
        return unhcrDate;
    }

    public void setUnhcrDate(LocalDateTime unhcrDate) {
        this.unhcrDate = unhcrDate;
    }

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getUnhcrNum() {
        return unhcrNum;
    }

    public void setUnhcrNum(String unhcrNum) {
        this.unhcrNum = unhcrNum;
    }

    public String getFileStatusName() {
        return fileStatusName;
    }

    public void setFileStatusName(String fileStatusName) {
        this.fileStatusName = fileStatusName;
    }

    public Integer getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(Integer familyMembers) {
        this.familyMembers = familyMembers;
    }

    public void incFamilyMember() {
        familyMembers = familyMembers + 1;
    }

    @Override
    public String toString() {
        return "Report3{" +
                "clientId=" + clientId +
                ", unhcrDate=" + unhcrDate +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", familyMembers=" + familyMembers +
                ", name='" + fio + '\'' +
                ", unhcrNum='" + unhcrNum + '\'' +
                ", fileStatusName='" + fileStatusName + '\'' +
                '}';
    }
}
