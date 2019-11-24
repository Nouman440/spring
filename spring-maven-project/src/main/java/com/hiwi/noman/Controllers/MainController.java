package com.hiwi.noman.Controllers;

import com.hiwi.noman.Components.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Created by nomi on 11.10.18.
 */

@Controller
public class MainController {
    private static final String UPLOAD_FOLDER = "/opt/tomcat/uploads/";
    private final FileLoader fileLoader;
    private static final DBAccessInterface db = new Database();

    @Autowired
    MainController(FileLoader fileLoader)
    {
        this.fileLoader = fileLoader;
    }

    @RequestMapping(value = "noman-0.0.1-SNAPSHOT/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        String heading = "Spring MVC: How to Access ModelMap Values in a JSP?";
        String result1 = "Hey.. I'm result1.. You are seeing me on welcome page.";
        String result2 = "Hey.. I'm result2.. ";

        String credit = "Demo by <a href='https://crunchify.com'>Crunchify</a>. Click <a href='https://crunchify.com/category/java-tutorials/'>here</a> for more than 350 Java Examples.";

        // you can add any Collection Objects to ModelMap
        // including JSON, String, Array, Map, List, etc...
        model.addAttribute("heading", heading);
        model.addAttribute("result1", result1);
        model.addAttribute("result2", result2);
        model.addAttribute("credit", credit);
        return "welcome";
    }

    @RequestMapping(value = "noman-0.0.1-SNAPSHOT/submitJob", method = RequestMethod.POST ,
            produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<Jobinfo> submitJob(Jobinfo jobInfoRequest) throws Exception {

        // logger.info("Request Job : {}",jobInfoRequest);
        jobInfoRequest = db.addJobInfo(jobInfoRequest);
        // System.out.println("my print job is" +jobInfoRequest.toString());
        ResponseEntity<Jobinfo> responseEntity = new ResponseEntity <Jobinfo>(jobInfoRequest, HttpStatus.OK);

        return  responseEntity;
    }

    @RequestMapping(value = "noman-0.0.1-SNAPSHOT/submitJob2", method =  RequestMethod.POST  ,
            produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<Jobinfo2> submitJob2(Jobinfo2 jobInfoRequest) throws Exception {

        // logger.info("Request Job : {}",jobInfoRequest);
        jobInfoRequest = db.addJobInfo2(jobInfoRequest);
        // System.out.println("my print job is" +jobInfoRequest.toString());
        ResponseEntity<Jobinfo2> responseEntity = new ResponseEntity<Jobinfo2>(jobInfoRequest, HttpStatus.OK);
        return responseEntity;

    }
    @RequestMapping(value = "noman-0.0.1-SNAPSHOT/submitJob3", method =  { RequestMethod.GET, RequestMethod.POST }  ,
            produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<Jobinfo3> submitJob3(Jobinfo3 jobInfoRequest) throws Exception {

        // logger.info("Request Job : {}",jobInfoRequest);
        jobInfoRequest = db.addJobInfo3(jobInfoRequest);
        // System.out.println("my print job is" +jobInfoRequest.toString());
        ResponseEntity<Jobinfo3> responseEntity = new ResponseEntity<Jobinfo3>(jobInfoRequest, HttpStatus.OK);
        return responseEntity;

    }
    @RequestMapping(value = "noman-0.0.1-SNAPSHOT/login", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<String> login(user userRequest) throws Exception {

        if (db.authenticate(userRequest) == false){

            System.out.println("demo id:"+userRequest.getUsername());
            System.out.println("demo name:"+userRequest.getPassword());

            return new ResponseEntity<String>("login failed",HttpStatus.UNAUTHORIZED);
        }
     else
        {
            System.out.println("demo id:"+userRequest.getUsername());
            System.out.println("demo name:"+userRequest.getPassword());
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        }




    }



    @RequestMapping(value="noman-0.0.1-SNAPSHOT/postdata", method=RequestMethod.POST)
    public ResponseEntity<Object> postData(@RequestBody Demo demo) {
        System.out.println("demo id:"+demo.getId());
        System.out.println("demo name:"+demo.getName());
        if(demo.getId().equals("1")&& demo.getName().equals("nouman"))
        {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        else
            return new ResponseEntity<Object>("error",HttpStatus.UNAUTHORIZED);
    }



    @RequestMapping(value = "noman-0.0.1-SNAPSHOT/listJobs", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<Object> listJobs() throws Exception {

        List<Document> jobInfos = db.getAllJobInfo();





        ResponseEntity<List<String>> responseEntity;
        return new ResponseEntity<>( jobInfos, HttpStatus.OK);
    }
    @RequestMapping(value = "listJobs", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<Object> listJob() throws Exception {

        List<Document> jobInfos = db.getAllJobInfo();





        ResponseEntity<List<String>> responseEntity;
        return new ResponseEntity<>( jobInfos, HttpStatus.OK);
    }
    @RequestMapping(value = "noman-0.0.1-SNAPSHOT/listJobs1", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<Object> listJobs1() throws Exception {


        return new ResponseEntity<>( db.getAllJobInfo1(), HttpStatus.OK);
    }

    @RequestMapping(value = "noman-0.0.1-SNAPSHOT/listJobs2", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<Object> listJobs2() throws Exception {


        return new ResponseEntity<>( db.getAllJobInfo2(), HttpStatus.OK);
    }
    @RequestMapping(value = "/listJobs1", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<Object> listJob1() throws Exception {


        return new ResponseEntity<>( db.getAllJobInfo1(), HttpStatus.OK);
    }

    @RequestMapping(value = "/listJobs2", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<Object> listJob2() throws Exception {


        return new ResponseEntity<>( db.getAllJobInfo2(), HttpStatus.OK);
    }

    @RequestMapping(value = "/listJobs3", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<Object> listJob3(Jobinfo3 jobInfo) throws Exception {


        return new ResponseEntity<>( db.getAllJobInfo3(jobInfo), HttpStatus.OK);
    }



    @RequestMapping(value = "/getFileName", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getFileName() {
        return ResponseEntity.ok()
                .body(fileLoader.getName());
    }
    @RequestMapping(value = "up/uploadfile", method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception{

        fileLoader.savefile(file.getBytes(),file.getOriginalFilename());

        String p=UPLOAD_FOLDER + file.getOriginalFilename() ;
        db.loaddb(p);


        System.out.println(file.getName());

        return ResponseEntity.ok()
                .body("You have successfully uploaded " + file.getOriginalFilename() + "   to the database.....Congrates!");
    }

    @RequestMapping(value = "/up/uploadfile1", method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUpload1(@RequestParam("file") MultipartFile file) throws Exception{

        fileLoader.savefile(file.getBytes(),file.getOriginalFilename());

        String p=UPLOAD_FOLDER + file.getOriginalFilename() ;
        db.loaddb1(p);


        System.out.println(file.getName());

        return ResponseEntity.ok()
                .body("You have successfully uploaded " + file.getOriginalFilename() + "   to the database.....Congrates!");
    }

    @RequestMapping(value = "/up/uploadfile2", method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUpload2(@RequestParam("file") MultipartFile file) throws Exception{

        fileLoader.savefile(file.getBytes(),file.getOriginalFilename());

        String p=UPLOAD_FOLDER + file.getOriginalFilename() ;
        db.loaddb2(p);


        System.out.println(file.getName());

        return ResponseEntity.ok()
                .body("You have successfully uploaded " + file.getOriginalFilename() + "   to the database.....Congrates!");
    }
}
