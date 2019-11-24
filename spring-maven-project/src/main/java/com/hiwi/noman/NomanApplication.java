package com.hiwi.noman;

import com.mongodb.*;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class NomanApplication {
    public static final String SAMPLE_XLS_FILE_PATH = "/home/nomi/i3e1f-gw5j0.xls";

	public static void main(String[] args) throws IOException, InvalidFormatException {




		//SpringApplication.run(NomanApplication.class, args);
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("mytest");
		DBCollection collection = db.getCollection("test");
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mytest");
		MongoCollection<Document> entityCollection = mongoDatabase.getCollection("test");
		Document  queryDocument =  new Document("value1", "a").append("value2","a");
		Document sourceDocument = entityCollection.find(queryDocument).first();
		System.out.println(sourceDocument);





		if (sourceDocument == null) {
			BasicDBObject document = new BasicDBObject();
			document.put("value1","a" );
			document.put("value2", "a");


			List<BasicDBObject> a = new ArrayList<BasicDBObject>();
			BasicDBObject documentDetail = new BasicDBObject();
			documentDetail.put("user","a");
			documentDetail.put("value", "a");
			documentDetail.put("comment", "a");
			documentDetail.put("date", date.toString());
			a.add(documentDetail);
			document.put("info", a);

			collection.insert(document);







		}
		else{

			Document elementToArray = new Document()
					.append("user","a")
					.append("value", "a")
					.append("comment", "a")
					.append("date",date.toString());

			Document pushElement = new Document("$push", new BasicDBObject("info", elementToArray));
			entityCollection.updateOne(queryDocument, pushElement);
		}*/




	/*	BasicDBObject document = new BasicDBObject();
		document.put("value1","a" );
		document.put("value2", "b");


		List<BasicDBObject> a = new ArrayList<BasicDBObject>();
		BasicDBObject documentDetail = new BasicDBObject();
		documentDetail.put("user","2010");
		documentDetail.put("value", "23");
		documentDetail.put("comment", "i am cool");
		a.add(documentDetail);




		document.put("info", a);

		collection.insert(document);*/





	/*	try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("mytest");
			DBCollection dbcol = db.getCollection("model1");

			DBCursor cursor = dbcol.find();

			try {

				while (cursor.hasNext()) {

					DBObject Features = cursor.next();

					BasicDBList features = (BasicDBList) Features.get("features");

					BasicDBObject[] featuresArr = features.toArray(new BasicDBObject[0]);

					for (BasicDBObject dbobj : featuresArr) {

						BasicDBObject geometry = (BasicDBObject) dbobj.get("geometry");

						BasicDBList coordinates = (BasicDBList) geometry.get("coordinates"); // BasicDBList contains coordinates

						System.out.println(coordinates.get(0));

					}

				}
			} finally {
				cursor.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}*/


		// Creating a Workbook from an Excel file (.xls or .xlsx)
      Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLS_FILE_PATH));

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

            MongoClient c = new MongoClient("localhost",27017);
            DB db = c.getDB("mytest");
            DBCollection collection = db.getCollection("new");



            try {
                BasicDBObject document = new BasicDBObject();
                document.put("Model",Model );
                document.put("Scenario", Scenario);
                document.put("Region", Region);
                document.put("Variable", Variable);
                document.put("unit", unit);
                document.put("most relevant to check", check);

               /* List<BasicDBObject> a = new ArrayList<BasicDBObject>();
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

                document.put("Amount", a);*/

                collection.insert(document);




            }
            catch (MongoException e) {
                e.printStackTrace();

            }
            c.close();






            System.out.println(Model+" "+Scenario +" "+Region + " " + Variable+" "+ unit+ " "+ check);
        }





	}
}
