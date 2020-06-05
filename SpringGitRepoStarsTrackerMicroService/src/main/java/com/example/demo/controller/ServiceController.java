package com.example.demo.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.JsonPojo;
import com.example.demo.model.JsonPojoArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
public class ServiceController {

  @Value("${eureka.instance.instance-id}")
  private String instanceId;

  @GetMapping("/getStats")
  public String getHello() throws Exception {
	  
	  JsonPojoArray reposList = null;
	  
	  LocalDate dt = LocalDate.now().minusMonths(1);
	  String str = "https://api.github.com/search/repositories?q=created:>" + dt.toString() + "&sort=stars&order=desc&per_page=100";
	  
	  System.out.println(str);
	  
	  URL url = new URL(str);
	  HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	  conn.setRequestMethod("GET");
	  conn.connect();
	  int responseCode = conn.getResponseCode();
		
	  if(responseCode!=200) {
			
	  }
	  else {
		  Scanner sc = new Scanner(url.openStream());
		  String data="";
		  while(sc.hasNext())
	      {
			  data+=sc.nextLine();
		  }
		  sc.close();
		  
		  ObjectMapper mapper = new ObjectMapper();
		  reposList = mapper.readValue(data, JsonPojoArray.class);
		 
		  String msg = "Hi there! This Microservice Instance Id is : " + instanceId + "\n Time now is "+ LocalDateTime.now();
		  reposList.items.get(0).setMsg(msg);
		  
	  }
	  return new Gson().toJson(reposList.items);
  }
}