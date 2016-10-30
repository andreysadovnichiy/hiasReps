package com.solveast.rreps.model.schemas.clients;

/**
 * Created by Андрей on 28.10.2016.
 */
public class FStatus {
    private Long fileStatusId;
    private Boolean active;
    private Integer ord;

    public FStatus(Long fileStatusId, Boolean active, Integer ord) {
        this.fileStatusId = fileStatusId;
        this.active = active;
        this.ord = ord;
    }

    public FStatus() {
    }

    public Long getFileStatusId() {
        return fileStatusId;
    }

    public void setFileStatusId(Long fileStatusId) {
        this.fileStatusId = fileStatusId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    @Override
    public String toString() {
        return "FStatus{" +
                "fileStatusId=" + fileStatusId +
                ", active=" + active +
                ", ord=" + ord +
                '}';
    }
}
