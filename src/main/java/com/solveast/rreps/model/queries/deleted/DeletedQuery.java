package com.solveast.rreps.model.queries.deleted;

import com.solveast.rreps.model.queries.family.FamilyReport;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 04.12.2016.
 */
public class DeletedQuery {
    private Long clientId;

    public DeletedQuery() {
    }

    public DeletedQuery(Long clientId) {
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "DeletedQuery{" +
                "clientId=" + clientId +
                '}';
    }
}
