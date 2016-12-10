package com.solveast.rreps.model.queries.family;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 10.12.2016.
 */
public class Family {
    private Person client;
    private List<Person> family = new ArrayList<>();
    private Integer familyPersonNumber;

    public Family(Person client, List<Person> family) {
        this.client = client;
        this.family = family;
    }

    public Family() {
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public List<Person> getFamily() {
        return family;
    }

    public void setFamily(List<Person> family) {
        this.family = family;
    }

    public Integer getFamilyPersonNumber() {
        if (family == null)
            return 1;
        return family.size() + 1;
    }
}
