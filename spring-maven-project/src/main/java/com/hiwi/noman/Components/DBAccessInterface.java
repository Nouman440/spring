package com.hiwi.noman.Components;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.bson.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by nomi on 03.08.17.
 */
public interface DBAccessInterface  {
    public enum Usertype { normaluser, unkownuser};
    Jobinfo addJobInfo(Jobinfo jobInfo) ;
    Jobinfo2 addJobInfo2(Jobinfo2 jobInfo) ;
    Jobinfo3 addJobInfo3(Jobinfo3 jobInfo) ;
    Document login(user userinfo );


    List<Document> getAllJobInfo () ;
    List<Document> getAllJobInfo1 () ;
    List<Document> getAllJobInfo2 () ;
    List<Document> getAllJobInfo3 (Jobinfo3 jobInfo) ;
    Jobinfo getJobById(Integer jobId) ;
    Jobinfo getJobByModelName(String modelName) ;
    Jobinfo getJobByScenario(String scenario) ;
    boolean authenticate(user ji)throws Exception;

    default void loaddb(String path) throws IOException, InvalidFormatException {

    }

    default void loaddb1(String path) throws IOException, InvalidFormatException {

    }

    default void loaddb2(String path) throws IOException, InvalidFormatException {

    }

}