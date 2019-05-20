package edu.cczu.makedecision.data;

import java.util.UUID;

public class Choice {
	
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Choice (String id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	public Choice() {
		id = UUID.randomUUID().toString();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}