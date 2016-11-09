package com.solveast.rreps.model.db.schemas.clients;

/**
 * Created by Андрей on 09.11.2016.
 */
public class TRegistrationState {
    private Long clientId;
    private Long fileStatusId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getFileStatusId() {
        return fileStatusId;
    }

    public void setFileStatusId(Long fileStatusId) {
        this.fileStatusId = fileStatusId;
    }

    @Override
    public String toString() {
        return "TRegistrationState{" +
                "clientId=" + clientId +
                ", fileStatusId=" + fileStatusId +
                '}';
    }
}
