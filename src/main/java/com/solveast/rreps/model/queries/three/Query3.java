package com.solveast.rreps.model.queries.three;

import com.solveast.rreps.model.queries.base.BaseQuery;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 27.11.2016.
 */
public class Query3 extends BaseQuery {
    private String iso3166_3;
    private String name;
    private String familyName;
    private String unhcrNum;
    private Long fileStatusId;
    private String fileStatusName;

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

    public Report3 toReport3() {
        Report3 report = new Report3();

        report.setClientId(super.getClientId());
        report.setIso3166_3(getIso3166_3());
        report.setUnhcrNum(getUnhcrNum());
        report.setFio(getName() + " " + getFamilyName());
        report.setFamilyMembers(1);
        report.setFileStatusName(getFileStatusId() + ": " + getFileStatusName());
        return report;
    }

    @Override
    public String toString() {
        return "Query3{" +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", unhcrNum='" + unhcrNum + '\'' +
                ", fileStatusId=" + fileStatusId +
                ", fileStatusName='" + fileStatusName + '\'' +
                '}';
    }
}
