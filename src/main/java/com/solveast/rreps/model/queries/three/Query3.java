package com.solveast.rreps.model.queries.three;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 27.11.2016.
 */
public class Query3 {
    private Long clientId;
    private Integer totalFamilyMembers;
    private String iso3166_3;
    private String name;
    private String familyName;
    private String unhcrNum;
    private LocalDateTime unhcrDate;
    private Long fileStatusId;
    private String fileStatusName;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Integer getTotalFamilyMembers() {
        return totalFamilyMembers;
    }

    public void setTotalFamilyMembers(Integer totalFamilyMembers) {
        this.totalFamilyMembers = totalFamilyMembers;
    }

    public LocalDateTime getUnhcrDate() {
        return unhcrDate;
    }

    public void setUnhcrDate(Timestamp unhcrDate) {
        if (unhcrDate != null)
            this.unhcrDate = unhcrDate.toLocalDateTime();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUnhcrNum() {
        return unhcrNum;
    }

    public void setUnhcrNum(String unhcrNum) {
        this.unhcrNum = unhcrNum;
    }

    public Long getFileStatusId() {
        return fileStatusId;
    }

    public void setFileStatusId(Long fileStatusId) {
        this.fileStatusId = fileStatusId;
    }

    public String getFileStatusName() {
        return fileStatusName;
    }

    public void setFileStatusName(String fileStatusName) {
        this.fileStatusName = fileStatusName;
    }

    @Override
    public String toString() {
        return "Query3{" +
                "clientId=" + clientId +
                ", totalFamilyMembers=" + totalFamilyMembers +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", unhcrNum='" + unhcrNum + '\'' +
                ", unhcrDate=" + unhcrDate +
                ", fileStatusId=" + fileStatusId +
                ", fileStatusName='" + fileStatusName + '\'' +
                '}';
    }
}
