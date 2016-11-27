package com.solveast.rreps.model.queries;

/**
 * Created by Андрей on 27.11.2016.
 */
public class Report4 {
    private Integer male_0_4 = 0;
    private Integer female_0_4 = 0;
    private Integer male_5_17 = 0;
    private Integer female_5_17 = 0;
    private Integer male_18_59 = 0;
    private Integer female_18_59 = 0;
    private Integer male_60_ = 0;
    private Integer female_60_ = 0;
    private Integer total = 0;

    public void set(String sex, Integer age) {
        if (sex.equals("m") && age <= 4)
            male_0_4++;
        else if (sex.equals("f") && age <= 4)
            female_0_4++;
        else if (sex.equals("m") && age >= 5 && age <= 17)
            male_5_17++;
        else if (sex.equals("f") && age >= 5 && age <= 17)
            female_5_17++;
        else if (sex.equals("m") && age >= 18 && age <= 59)
            male_18_59++;
        else if (sex.equals("f") && age >= 18 && age <= 59)
            female_18_59++;
        else if (sex.equals("m") && age >= 60)
            male_60_++;
        else if (sex.equals("f") && age >= 60)
            female_60_++;
        else
            return;

        total++;
    }

    public Integer getMale_0_4() {
        return male_0_4;
    }

    public Integer getFemale_0_4() {
        return female_0_4;
    }

    public Integer getMale_5_17() {
        return male_5_17;
    }

    public Integer getFemale_5_17() {
        return female_5_17;
    }

    public Integer getMale_18_59() {
        return male_18_59;
    }

    public Integer getFemale_18_59() {
        return female_18_59;
    }

    public Integer getMale_60_() {
        return male_60_;
    }

    public Integer getFemale_60_() {
        return female_60_;
    }

    public Integer getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Report4{" +
                "male_0_4=" + male_0_4 +
                ", female_0_4=" + female_0_4 +
                ", male_5_17=" + male_5_17 +
                ", female_5_17=" + female_5_17 +
                ", male_18_59=" + male_18_59 +
                ", female_18_59=" + female_18_59 +
                ", male_60_=" + male_60_ +
                ", female_60_=" + female_60_ +
                ", total=" + total +
                '}';
    }
}
