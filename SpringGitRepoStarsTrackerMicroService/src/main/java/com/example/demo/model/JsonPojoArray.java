package com.example.demo.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPojoArray {

	public JsonPojoArray(){
		
	}
	
	@JsonProperty("items")
	public List<JsonPojo> items = new ArrayList<>();
	
	public List<JsonPojo> getItems() {
		return items;
	}

	public void setItems(List<JsonPojo> items) {
		this.items = items;
	}
	
	
}
