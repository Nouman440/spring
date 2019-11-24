package com.hiwi.noman.Components;

/**
 * Created by nomi on 15.01.19.
 */
public class Jobinfo2 {

    private Integer jobId;
     public String technik;
    public String unit;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getTechnik() {
        return technik;
    }

    public void setTechnik(String technik) {
        this.technik = technik;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String min;
    public String type;
    public String max;
    public String year;
    public String source;
    public String comment;


    @Override
    public String toString() {
        return "JobInfo2{" +
                "jobId=" + jobId +
                ", technil='" + technik + '\'' +
                ", unit='" + unit + '\'' +
                ", min='" + min + '\'' +
                ", type='" + type + '\'' +
                ", max='" + max + '\'' +
                ", year='" + year + '\'' +
                ", source='" + source + '\'' +
                ", comment='" + comment + '\'' +

                '}';
    }
}
