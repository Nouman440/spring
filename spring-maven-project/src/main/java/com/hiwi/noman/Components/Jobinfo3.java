package com.hiwi.noman.Components;

/**
 * Created by nomi on 02.03.19.
 */
public class Jobinfo3 {


    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String value1;
    public String value2;
    public String user;
    public String value;
    public String comment;
    public String date;




    @Override
    public String toString() {
        return "JobInfo3{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", user='" + user + '\'' +
                ", value='" + value + '\'' +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +


                '}';
    }
}
