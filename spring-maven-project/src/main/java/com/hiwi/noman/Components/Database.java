package com.hiwi.noman.Components;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by nomi on 03.08.17.
 */
public class Database implements DBAccessInterface {

     MongoClient c = new MongoClient("localhost",27017);
     DB db = c.getDB("mytest");



    @Override
    public Jobinfo addJobInfo(Jobinfo jobInfo) {
        try {
            DBCollection collection1 = db.getCollection("model1");
            BasicDBObject document = new BasicDBObject();
            document.put("Model", jobInfo.getModel());
            document.put("Scenario", jobInfo.getScenario());
            document.put("Region", jobInfo.getRegion());
            document.put("Variable", jobInfo.getVariable());
            document.put("unit", jobInfo.getUnit());
            document.put("mostreleventtodata", jobInfo.getMostreleventtodata());




           List<BasicDBObject> a = new ArrayList<BasicDBObject>();
            BasicDBObject documentDetail = new BasicDBObject();
            documentDetail.put("year",jobInfo.getYear());
            documentDetail.put("value", jobInfo.getValue());
            a.add(documentDetail);
            BasicDBObject documentDetail1 = new BasicDBObject();
            documentDetail1.put("year",jobInfo.getYear1());
            documentDetail1.put("value", jobInfo.getValue1());
            a.add(documentDetail1);
            BasicDBObject documentDetail2 = new BasicDBObject();
            documentDetail2.put("year",jobInfo.getYear2());
            documentDetail2.put("value", jobInfo.getValue2());
            a.add(documentDetail2);

            BasicDBObject documentDetail3 = new BasicDBObject();
            documentDetail3.put("year",jobInfo.getYear3());
            documentDetail3.put("value", jobInfo.getValue3());
            a.add(documentDetail3);
            BasicDBObject documentDetail4 = new BasicDBObject();
            documentDetail4.put("year",jobInfo.getYear4());
            documentDetail4.put("value", jobInfo.getValue4());
            a.add(documentDetail4);
            BasicDBObject documentDetail5 = new BasicDBObject();
            documentDetail5.put("year",jobInfo.getYear5());
            documentDetail5.put("value", jobInfo.getValue5());
            a.add(documentDetail5);

            BasicDBObject documentDetail6 = new BasicDBObject();
            documentDetail6.put("year",jobInfo.getYear6());
            documentDetail6.put("value", jobInfo.getValue6());
            a.add(documentDetail);
            BasicDBObject documentDetail7 = new BasicDBObject();
            documentDetail7.put("year",jobInfo.getYear7());
            documentDetail7.put("value", jobInfo.getValue7());
            a.add(documentDetail7);
            BasicDBObject documentDetail8 = new BasicDBObject();
            documentDetail8.put("year",jobInfo.getYear8());
            documentDetail8.put("value", jobInfo.getValue8());
            a.add(documentDetail8);

            document.put("Amount", a);

            collection1.insert(document);




            return jobInfo;
        }
        catch (MongoException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Jobinfo2 addJobInfo2(Jobinfo2 jobInfo) {

        MongoClient c = new MongoClient("localhost",27017);
        DB db = c.getDB("mytest");
        DBCollection collection = db.getCollection("model4");



        try {
            BasicDBObject document = new BasicDBObject();
            document.put("technk",jobInfo.getTechnik() );
            document.put("unit", jobInfo.getUnit());
            document.put("min", jobInfo.getMin());
            document.put("type", jobInfo.getType());
            document.put("max", jobInfo.getMax());
            document.put("year", jobInfo.getYear());
            document.put("source", jobInfo.getSource());
            document.put("comment", jobInfo.getComment());




            collection.insert(document);

        }
        catch (MongoException e) {
            e.printStackTrace();

        }
        c.close();



            return jobInfo;
        }


    @Override
    public Jobinfo3 addJobInfo3(Jobinfo3 jobInfo) {
        Date date = new Date();
        try {

            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("mytest");
            DBCollection collection = db.getCollection("test");
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mytest");
            MongoCollection<Document> entityCollection = mongoDatabase.getCollection("test");
            Document  queryDocument =  new Document("value1", jobInfo.getValue1()).append("value2",jobInfo.getValue2());
            Document sourceDocument = entityCollection.find(queryDocument).first();






            if (sourceDocument == null) {
                BasicDBObject document = new BasicDBObject();
                document.put("value1",jobInfo.getValue1() );
                document.put("value2", jobInfo.getValue2());


                List<BasicDBObject> a = new ArrayList<BasicDBObject>();
                BasicDBObject documentDetail = new BasicDBObject();
                documentDetail.put("user",jobInfo.getUser());
                documentDetail.put("value", jobInfo.getValue());
                documentDetail.put("comment", jobInfo.getComment());
                documentDetail.put("date", date.toString());
                a.add(documentDetail);
                document.put("info", a);

                collection.insert(document);







            }
            else{

                Document elementToArray = new Document()
                        .append("user",jobInfo.getUser())
                        .append("value", jobInfo.getValue())
                        .append("comment", jobInfo.getComment())
                        .append("date",date.toString());

                Document pushElement = new Document("$push", new BasicDBObject("info", elementToArray));
                entityCollection.updateOne(queryDocument, pushElement);
            }






            return jobInfo;
        }
        catch (MongoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Document login(user userinfo) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("mytest");
        MongoCollection<Document> collection = database
                .getCollection("user");

          try {



              return collection.find(eq("username",userinfo.getUsername())).first();
        }
        catch (MongoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean authenticate(user ji)throws Exception{
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("mytest");
        DBCollection dbCollection = db.getCollection("user");

        BasicDBObject andQuery = new BasicDBObject("username",ji.getUsername()).append("passward",ji.getPassword());



        Cursor myCursor = dbCollection.find(andQuery);

        int count=0;

System.out.println(ji.getUsername() + ji.getPassword());
        while(myCursor.hasNext())
        {


            count =1;
            break;
        }
        myCursor.close();

         if(count==1){
             System.out.println(count);

            return true ;
        }


        else
            {

            return false;
        }
    }


    public void loaddb(String path) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(path));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        Sheet sheet=workbook.getSheetAt(0);;
        System.out.println(sheet.getSheetName());

        Row row;
        for(int i=1; i<=sheet.getLastRowNum(); i++) {  //points to the starting of excel i.e excel first row
            row = (Row) sheet.getRow(i);  //sheet number


            String Model;
            if (row.getCell(0) == null) {
                Model = "0";
            } else Model = row.getCell(0).toString();

            String Scenario;
            if (row.getCell(1) == null) {
                Scenario = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else Scenario = row.getCell(1).toString();   //else copies cell data to name variable

            String Region;
            if (row.getCell(2) == null) {
                Region= "null";
            } else Region = row.getCell(2).toString();


            String Variable;
            if (row.getCell(3) == null) {
                Variable = "0";
            } else Variable = row.getCell(3).toString();

            String unit;
            if (row.getCell(4) == null) {
                unit = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else unit= row.getCell(4).toString();   //else copies cell data to name variable

            String check;
            if (row.getCell(5) == null) {
                check= "null";
            } else check = row.getCell(5).toString();





            try {
                DBCollection collection1 = db.getCollection("model1");
                BasicDBObject document = new BasicDBObject();
                document.put("Model",Model );
                document.put("Scenario", Scenario);
                document.put("Region", Region);
                document.put("Variable", Variable);
                document.put("unit", unit);
                document.put("mostreleventtodata", check);

                List<BasicDBObject> a = new ArrayList<BasicDBObject>();
                BasicDBObject documentDetail = new BasicDBObject();
                documentDetail.put("year","2010");
                documentDetail.put("value", row.getCell(6).toString());
                a.add(documentDetail);
                BasicDBObject documentDetail1 = new BasicDBObject();
                documentDetail1.put("year","2015");
                documentDetail1.put("value", row.getCell(7).toString());
                a.add(documentDetail1);
                BasicDBObject documentDetail2 = new BasicDBObject();
                documentDetail2.put("year","2020");
                documentDetail2.put("value", row.getCell(8).toString());
                a.add(documentDetail2);

                BasicDBObject documentDetail3 = new BasicDBObject();
                documentDetail3.put("year","2025");
                documentDetail3.put("value", row.getCell(9).toString());
                a.add(documentDetail3);
                BasicDBObject documentDetail4 = new BasicDBObject();
                documentDetail4.put("year","2030");
                documentDetail4.put("value", row.getCell(10).toString());
                a.add(documentDetail4);
                BasicDBObject documentDetail5 = new BasicDBObject();
                documentDetail5.put("year","2035");
                documentDetail5.put("value", row.getCell(11).toString());
                a.add(documentDetail5);

                BasicDBObject documentDetail6 = new BasicDBObject();
                documentDetail6.put("year","2040");
                documentDetail6.put("value", row.getCell(12).toString());
                a.add(documentDetail);
                BasicDBObject documentDetail7 = new BasicDBObject();
                documentDetail7.put("year","2045");
                documentDetail7.put("value", row.getCell(13).toString());
                a.add(documentDetail7);
                BasicDBObject documentDetail8 = new BasicDBObject();
                documentDetail8.put("year","2050");
                documentDetail8.put("value", row.getCell(14).toString());
                a.add(documentDetail8);

                document.put("Amount", a);

                collection1.insert(document);




            }
            catch (MongoException e) {
                e.printStackTrace();

            }







            // System.out.println(Model+" "+Scenario +" "+Region + " " + Variable+" "+ unit+ " "+ check);
        }



    }


    public void loaddb1(String path) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(path));

        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        Sheet sheet=workbook.getSheetAt(0);;
        System.out.println(sheet.getSheetName());

        Row row;
        for(int i=2; i<=sheet.getLastRowNum(); i++) {  //points to the starting of excel i.e excel first row for(int i=2; i<=sheet.getLastRowNum(); i++)
            row = (Row) sheet.getRow(i);  //sheet number


            String InvOption;
            if (row.getCell(0) == null) {
                InvOption = "null";
            } else InvOption = row.getCell(0).toString();

            String InvOptionYear;
            if (row.getCell(1) == null) {
                InvOptionYear = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else InvOptionYear= row.getCell(1).toString();   //else copies cell data to name variable

            String TechType;
            if (row.getCell(2) == null) {
                TechType = "null";
            } else TechType = row.getCell(2).toString();

            String SubType;
            if (row.getCell(3) == null) {
                SubType = "null";
            } else SubType = row.getCell(3).toString();

            String PrimaryEnergy;
            if (row.getCell(4) == null) {
                PrimaryEnergy= "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else PrimaryEnergy = row.getCell(4).toString();   //else copies cell data to name variable

            String InvFromYear;
            if (row.getCell(5) == null) {
                InvFromYear = "null";
            } else InvFromYear = row.getCell(5).toString();

            String InvToYear;
            if (row.getCell(6) == null) {
                InvToYear = "null";
            } else InvToYear = row.getCell(6).toString();
            String LifeTime;
            if (row.getCell(7) == null) {
                LifeTime = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else LifeTime= row.getCell(7).toString();   //else copies cell data to name variable

            String DeprTime;
            if (row.getCell(8) == null) {
                DeprTime = "null";
            } else DeprTime = row.getCell(8).toString();
            String MaxPower;
            if (row.getCell(9) == null) {
                MaxPower = "null";
            } else MaxPower = row.getCell(9).toString();

            String MinPower;
            if (row.getCell(10) == null) {
                MinPower= "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else MinPower= row.getCell(10).toString();   //else copies cell data to name variable

            String MaxEff;
            if (row.getCell(11) == null) {
                MaxEff = "null";
            } else MaxEff = row.getCell(11).toString();
            String PartEff;
            if (row.getCell(12) == null) {
                PartEff = "null";
            } else PartEff = row.getCell(12).toString();

            String CHP_CB;
            if (row.getCell(13) == null) {
                CHP_CB = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else CHP_CB= row.getCell(13).toString();   //else copies cell data to name variable

            String Ext_CV;
            if (row.getCell(14) == null) {
                Ext_CV = "null";
            } else Ext_CV = row.getCell(14).toString();
            String CHP_MaxHeat;
            if (row.getCell(15) == null) {
                CHP_MaxHeat = "null";
            } else CHP_MaxHeat = row.getCell(15).toString();
            String Reliab;
            if (row.getCell(16) == null) {
                Reliab= "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else Reliab = row.getCell(16).toString();   //else copies cell data to name variable
            String RunUpRate;
            if (row.getCell(17) == null) {
                RunUpRate = "null";
            } else RunUpRate = row.getCell(17).toString();

            String RampRateUp;
            if (row.getCell(18) == null) {
                RampRateUp = "null";
            } else RampRateUp = row.getCell(18).toString();

            String RampRateDown;
            if (row.getCell(19) == null) {
                RampRateDown = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else RampRateDown= row.getCell(19).toString();   //else copies cell data to name variable

            String MinOperTime;
            if (row.getCell(20) == null) {
                MinOperTime = "null";
            } else MinOperTime = row.getCell(20).toString();
            String MinDownTime;
            if (row.getCell(21) == null) {
                MinDownTime = "null";
            } else MinDownTime = row.getCell(21).toString();

            String SyncTimeHot;
            if (row.getCell(22) == null) {
                SyncTimeHot= "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else SyncTimeHot= row.getCell(22).toString();   //else copies cell data to name variable
            String InvestCosts;
            if (row.getCell(23) == null) {
                InvestCosts = "null";
            } else InvestCosts = row.getCell(23).toString();
            String VarOaMcosts;
            if (row.getCell(24) == null) {
                VarOaMcosts = "null";
            } else VarOaMcosts= row.getCell(24).toString();

            String AnnualOaMcosts;
            if (row.getCell(25) == null) {
                AnnualOaMcosts = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else AnnualOaMcosts= row.getCell(25).toString();   //else copies cell data to name variable
            String StartUpFuel;
            if (row.getCell(26) == null) {
                StartUpFuel = "null";
            } else StartUpFuel = row.getCell(26).toString();
            String StartUpFuelConsCold;
            if (row.getCell(27) == null) {
                StartUpFuelConsCold = "null";
            } else StartUpFuelConsCold = row.getCell(27).toString();

            String StartUpFuelConsWarm;
            if (row.getCell(28) == null) {
                StartUpFuelConsWarm= "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else StartUpFuelConsWarm = row.getCell(28).toString();   //else copies cell data to name variable
            String StartUpFuelConsHot;
            if (row.getCell(29) == null) {
                StartUpFuelConsHot = "null";
            } else StartUpFuelConsHot = row.getCell(29).toString();
            String StartUpVarCosts;
            if (row.getCell(30) == null) {
                StartUpVarCosts = "null";
            } else StartUpVarCosts = row.getCell(30).toString();
            String Sto_MaxContent;
            if (row.getCell(31) == null) {
                Sto_MaxContent = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else Sto_MaxContent= row.getCell(31).toString();   //else copies cell data to name variable
            String Sto_MinContent;
            if (row.getCell(32) == null) {
                Sto_MinContent = "null";
            } else Sto_MinContent = row.getCell(32).toString();
            String Sto_MaxCharging;
            if (row.getCell(33) == null) {
                Sto_MaxCharging = "null";
            } else Sto_MaxCharging = row.getCell(33).toString();

            String Sto_MinCharging;
            if (row.getCell(34) == null) {
                Sto_MinCharging= "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else Sto_MinCharging= row.getCell(34).toString();   //else copies cell data to name variable
            String Sto_LoadEff;
            if (row.getCell(35) == null) {
                Sto_LoadEff = "null";
            } else Sto_LoadEff = row.getCell(35).toString();
            String Sto_Loss;
            if (row.getCell(36) == null) {
                Sto_Loss = "null";
            } else Sto_Loss = row.getCell(36).toString();
            String CAES_Eff;
            if (row.getCell(37) == null) {
                CAES_Eff = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else CAES_Eff= row.getCell(37).toString();   //else copies cell data to name variable
            String MaxSpinResPri;
            if (row.getCell(38) == null) {
                MaxSpinResPri= "null";
            } else MaxSpinResPri = row.getCell(38).toString();




            String MaxSpinResSec;
            if (row.getCell(39) == null) {
                MaxSpinResSec = "null";
            } else MaxSpinResSec = row.getCell(39).toString();
            String MaxSpinResTer;
            if (row.getCell(40) == null) {
                MaxSpinResTer= "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else MaxSpinResTer = row.getCell(40).toString();   //else copies cell data to name variable
            String SpinningReserve;
            if (row.getCell(41) == null) {
                SpinningReserve = "null";
            } else SpinningReserve = row.getCell(41).toString();

            String NonSpinningReserve;
            if (row.getCell(42) == null) {
                NonSpinningReserve = "null";
            } else NonSpinningReserve = row.getCell(42).toString();

            String CO2CaptureRate;
            if (row.getCell(43) == null) {
                CO2CaptureRate = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else CO2CaptureRate= row.getCell(43).toString();   //else copies cell data to name variable

            String TGexpandCost;
            if (row.getCell(44) == null) {
                TGexpandCost = "null";
            } else TGexpandCost = row.getCell(44).toString();
            String DGexpandCost;
            if (row.getCell(45) == null) {
                DGexpandCost = "null";
            } else DGexpandCost = row.getCell(45).toString();

            String ICTexpandCost;
            if (row.getCell(46) == null) {
                ICTexpandCost= "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else ICTexpandCost= row.getCell(46).toString();   //else copies cell data to name variable
            String OffsConnectCost;
            if (row.getCell(47) == null) {
                OffsConnectCost = "null";
            } else OffsConnectCost = row.getCell(47).toString();
            String LifeTimeGrid;
            if (row.getCell(48) == null) {
                LifeTimeGrid = "null";
            } else LifeTimeGrid = row.getCell(48).toString();
            String LifeTimeICT;
            if (row.getCell(49) == null) {
                LifeTimeICT = "null";
            } else LifeTimeICT = row.getCell(49).toString();



         /*   System.out.println(InvOption + "  " + InvOptionYear + "  " + TechType + "  "
                    + SubType + "  " + PrimaryEnergy + "  " + InvFromYear+"  "+InvToYear+"  "+LifeTime+"  "
                    +DeprTime+"  "+MaxPower+" "+MinPower+" "+MaxEff+" "+PartEff+" "+CHP_CB+" "+Ext_CV+" "+CHP_MaxHeat+" "
                    +Reliab+" "+RunUpRate+" "+RampRateUp+" "+RampRateDown+" "+MinOperTime+""+MinDownTime+" "+SyncTimeHot+" "+InvestCosts+" "+VarOaMcosts+" "
                    +AnnualOaMcosts+" "+StartUpFuel+" "+StartUpFuelConsCold+" "+StartUpFuelConsWarm+" "+StartUpFuelConsHot+" "+StartUpVarCosts+" "
                    +Sto_MaxContent+" "+Sto_MinContent+" "+Sto_MaxCharging+" "+Sto_MinCharging+" "+Sto_LoadEff+" "
                    +Sto_Loss+" "+CAES_Eff+" "+MaxSpinResPri+" "+MaxSpinResSec+" "+MaxSpinResTer+" "+SpinningReserve+" "+NonSpinningReserve+" "
                    +CO2CaptureRate+" "+TGexpandCost+" "+DGexpandCost+" "+ICTexpandCost+" "+OffsConnectCost+" "+LifeTimeGrid+" "+LifeTimeICT);

*/

            MongoClient c = new MongoClient("localhost",27017);
            DB db = c.getDB("mytest");
            DBCollection collection = db.getCollection("model2.1");
            try {
                BasicDBObject document = new BasicDBObject();

                BasicDBObject doc1 = new BasicDBObject();
                doc1.put("InvOption", InvOption);
                doc1.put("InvOptionYear", InvOptionYear);
                document.put("Inv", doc1);
                BasicDBObject doc2 = new BasicDBObject();
                doc2.put("TechType", TechType);
                doc2.put("SubType", SubType);
                document.put("Type", doc2);
                document.put("PrimaryEnergy", PrimaryEnergy);

                BasicDBObject doc3 = new BasicDBObject();
                doc3.put("InvFromYear", InvFromYear);
                doc3.put("InvToYear", InvToYear);
                document.put("InvYear", doc3);

                BasicDBObject doc4 = new BasicDBObject();
                doc4.put("LifeTime", LifeTime);
                doc4.put("DeprTime", DeprTime);
                document.put("Time1", doc4);

                BasicDBObject doc5 = new BasicDBObject();
                doc5.put("MaxPower", MaxPower);
                doc5.put("MinPower", MinPower);
                document.put("Power", doc5);

                BasicDBObject doc6 = new BasicDBObject();
                doc6.put("MaxEff", MaxEff);
                doc6.put("PartEff", PartEff);
                document.put("Eff", doc6);

                document.put("CHP_CB", CHP_CB);
                document.put("Ext_CV", Ext_CV);
                document.put("Reliab", Reliab);
                BasicDBObject doc7 = new BasicDBObject();
                doc7.put("RunUpRate", RunUpRate);
                doc7.put("RampRateUp", RampRateUp);
                doc7.put("RampRateDown", RampRateDown);
                document.put("Rate", doc7);

                BasicDBObject doc8 = new BasicDBObject();
                doc8.put("MinOperTime", MinOperTime);
                doc8.put("MinDownTime", MinDownTime);
                doc8.put("SyncTimeHot", SyncTimeHot);
                document.put("Time", doc8);

                BasicDBObject doc9 = new BasicDBObject();
                doc9.put("unit", "euro");
                doc9.put("Investcosts", InvestCosts);
                doc9.put("VarOaMcosts", VarOaMcosts);
                doc9.put("AnnualOaMcosts", AnnualOaMcosts);

                document.put("costs", doc9);



                BasicDBObject doc10 = new BasicDBObject();
                doc10.put("StartUpFuel", StartUpFuel);
                doc10.put("StartUpFuelConsCold", StartUpFuelConsCold);
                doc10.put("StartUpFuelConsWarm", StartUpFuelConsWarm);
                doc10.put("StartUpFuelConsHot", StartUpFuelConsHot);
                doc10.put("StartUpVarCosts", StartUpVarCosts);
                document.put("StartUp",doc10);

                BasicDBObject doc11 = new BasicDBObject();
                doc11.put("Sto_MaxContent", Sto_MaxContent);
                doc11.put("Sto_MinContent", Sto_MinContent);
                doc11.put("Sto_MaxCharging", Sto_MaxCharging);
                doc11.put("Sto_LoadEff", Sto_LoadEff);
                doc11.put("Sto_Loss", Sto_Loss);
                document.put("Sto",doc11);

                document.put("CAES_Eff",CAES_Eff);

                BasicDBObject doc12 = new BasicDBObject();
                doc12.put("MaxSpinResPri", MaxSpinResPri);
                doc12.put("MaxSpinResSec", MaxSpinResSec);
                doc12.put("MaxSpinResTer", MaxSpinResTer);
                doc12.put("SpinningReserve", SpinningReserve);
                doc12.put("NonSpinningReserve", NonSpinningReserve);
                document.put("Spin",doc12);


                document.put("CO2CaptureRate",CO2CaptureRate);


                BasicDBObject doc13 = new BasicDBObject();
                doc13.put("TGexpandCost", TGexpandCost);
                doc13.put("DGexpandCost", DGexpandCost);
                doc13.put("ICTexpandCost", ICTexpandCost);
                doc13.put("OffsConnectCost", OffsConnectCost);
                document.put("Cost",doc13);

                document.put("LifeTimeGrid",LifeTimeGrid);
                document.put("LifeTimeICT",LifeTimeICT);



                collection.insert(document);




            }
            catch (MongoException e) {
                e.printStackTrace();

            }
            c.close();








        }



    }



    public void loaddb2(String path) throws IOException, InvalidFormatException {




        try {
            // Creating a Workbook from an Excel file (.xls or .xlsx)
            Workbook workbook = WorkbookFactory.create(new File(path));

            // Retrieving the number of sheets in the Workbook
            System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

            // 1. You can obtain a sheetIterator and iterate over it
			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
			System.out.println("Retrieving Sheets using Iterator");
			Sheet sheet = workbook.getSheetAt(0);
			;
			System.out.println(sheet.getSheetName());

			Row row;
			for (int i = 4; i <= sheet.getLastRowNum(); i++) {  //points to the starting of excel i.e excel first row sheet.getLastRowNum()
				row = (Row) sheet.getRow(i);  //sheet number


            String tecknik;
            if (row.getCell(1) == null) {
                tecknik = "0";
            } else tecknik = row.getCell(1).toString();
          //  System.out.println(tecknik +"...");

            String unit;
            if (row.getCell(2) == null) {
                unit = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else unit = row.getCell(2).toString();   //else copies cell data to name variable
			//	System.out.println(unit + "...");

				String min;
				if (row.getCell(3) == null) {
					min = "null";
				}  //suppose excel cell is empty then its set to 0 the variable
				else min = row.getCell(3).toString();   //else copies cell data to name variable
				//	System.out.println(min + "...");

            String type;
            if (row.getCell(4) == null) {
                type= "null";
            } else type = row.getCell(4).toString();
			//	System.out.println(type + "...");

            String max;
            if (row.getCell(5) == null) {
                max = "0";
            } else max = row.getCell(5).toString();
			//	System.out.println(max + "...");


            String year;
            if (row.getCell(6) == null) {
                year = "null";
            }  //suppose excel cell is empty then its set to 0 the variable
            else year= row.getCell(6).toString();   //else copies cell data to name variable
				// System.out.println(year + "...");

            String source;
            if (row.getCell(7) == null) {
                source= "null";
            } else source = row.getCell(7).toString();
				// System.out.println(source + "...");

				String comment;
				if (row.getCell(8) == null) {
					comment= "null";
				} else comment = row.getCell(8).toString();
				//System.out.println(comment + "...");
            MongoClient c = new MongoClient("localhost",27017);
            DB db = c.getDB("mytest");
            DBCollection collection = db.getCollection("model4");



            try {
                BasicDBObject document = new BasicDBObject();
                document.put("technk",tecknik );
                document.put("unit", unit);
                document.put("min", min);
                document.put("type", type);
                document.put("max", max);
                document.put("year", year);
				document.put("source", source);
				document.put("comment", comment);




                collection.insert(document);

			}
            catch (MongoException e) {
                e.printStackTrace();

            }
            c.close();


				// System.out.println(Model+" "+Scenario +" "+Region + " " + Variable+" "+ unit+ " "+ check);


			}
		}

        catch (Exception e) {

			}





















            // System.out.println(Model+" "+Scenario +" "+Region + " " + Variable+" "+ unit+ " "+ check);




    }





    // @Override
    //public List<JobInfo> getAllJobInfo() {
    //  try {
    //    return SqlStatement.select(JobInfo.class).getList();
    //} catch (DataConnectionException e) {
    //  e.printStackTrace();
    //return null;
    //}
    //}
    @Override
    public List<Document> getAllJobInfo() {
        List<DBObject> res = new ArrayList<>() ;
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("mytest");
        MongoCollection<Document> collection = database
                .getCollection("model1");

        List<Document> documents = (List<Document>) collection.find().into(
                new ArrayList<Document>());
        client.close();

       return documents;







    }

    @Override
    public List<Document> getAllJobInfo1() {

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("mytest");
        MongoCollection<Document> collection = database
                .getCollection("model2.1");

        List<Document> documents = (List<Document>) collection.find().into(
                new ArrayList<Document>());
        client.close();

        return documents;

    }

    @Override
    public List<Document> getAllJobInfo2() {

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("mytest");
        MongoCollection<Document> collection = database
                .getCollection("model4");

        List<Document> documents = (List<Document>) collection.find().into(
                new ArrayList<Document>());
        client.close();

        return documents;

    }

    @Override
    public List<Document> getAllJobInfo3(Jobinfo3 jobInfo) {

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("mytest");
        MongoCollection<Document> collection = database
                .getCollection("test");
        BasicDBObject inQuery = new BasicDBObject();

        inQuery.put("value1",jobInfo.getValue1());
        inQuery.put("value2", jobInfo.getValue2());



        List<Document> documents = (List<Document>) collection.find(inQuery).into(
                new ArrayList<Document>());
        client.close();

        return documents;

    }


    @Override
    public Jobinfo getJobById(Integer jobId) {
        return null;
    }


  /*  @Override
    public Jobinfo getJobById(Integer jobId) {
        try {
            return SqlStatement.select(JobInfo.class).where("jobId").eq(jobId).getFirst();
        } catch (DataConnectionException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    @Override
    public Jobinfo getJobByModelName(String modelName) {
        return null;
    }

    @Override
    public Jobinfo getJobByScenario(String scenario) {
        return null;
    }
}

