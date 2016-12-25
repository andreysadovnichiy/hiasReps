package com.solveast.rreps.model.queries.seven;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Андрей on 22.12.2016.
 */
public class ReportSevenAdapter {
    private Map<String, Report7> map;

    public void set(Query7 item) {
        String iso3166_3 = item.getIso3166_3();

        Report7 report = map.get(iso3166_3);
        if(report == null)
            report = new Report7();

        report.set(item);
        map.put(iso3166_3, report);
    }

    public ReportSevenAdapter() {
        map = new TreeMap<>();
    }

    public Map<String, Report7> getMap() {
        return map;
    }
}
