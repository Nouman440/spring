package com.hiwi.noman.Components;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.mongodb.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nomi on 11.10.18.
 */

@Component
public class FileLoader {
    private static final String UPLOAD_FOLDER = "/opt/tomcat/uploads/";
    String name;

    public FileLoader()
    {
        name = "data";

    }

    public String getName()
    {
        return name;
    }


    private static void writeBytesToFileClassic(byte[] bFile, String fileDest) {

        FileOutputStream fileOuputStream = null;

        try {
            fileOuputStream = new FileOutputStream(fileDest);
            fileOuputStream.write(bFile);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOuputStream != null) {
                try {
                    fileOuputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }





    //Since JDK 7 - try resources
    private static void writeBytesToFile(byte[] bFile, String fileDest) {

        try (FileOutputStream fileOuputStream = new FileOutputStream(fileDest)) {
            fileOuputStream.write(bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Since JDK 7, NIO
    private static void writeBytesToFileNio(byte[] bFile, String fileDest) {

        try {
            Path path = Paths.get(fileDest);
            Files.write(path, bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String savefile(byte[] filebytes,String name) throws IOException {
        FileInputStream fileInputStream = null;

        try {



            writeBytesToFile(filebytes, UPLOAD_FOLDER + name);


           // writeBytesToFileClassic(filebytes, UPLOAD_FOLDER + "2.xlsx");
            //writeBytesToFileNio(filebytes, UPLOAD_FOLDER + "3.xlsx");
            System.out.println("Done");

        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }




        return name;
    }
}
