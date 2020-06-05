package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPojo {
	
	public JsonPojo() {
		
	}

	@Override
	public String toString() {
		return "JsonPojo [stargazersCount=" + stargazersCount + ", language=" + language + ", fullName="
				+ fullName + "]";
	}

	public int getStargazersCount() {
		return stargazersCount;
	}

	public void setStargazersCount(int stargazersCount) {
		this.stargazersCount = stargazersCount;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		
		if(language==null || language.isEmpty() || language.length()<1 )
			this.language="undefined";
		else
			this.language = language;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	//@JsonProperty(value = "id")
	//public String id;
	
	@JsonProperty(value="stargazers_count")
	private int stargazersCount;
	
	@JsonProperty(value="language")
	private String language="undefined";
	
	@JsonProperty(value="full_name")
	private String fullName;
	
	@JsonIgnore
	private String color;
	
	@JsonIgnore
	private String msg;
}
