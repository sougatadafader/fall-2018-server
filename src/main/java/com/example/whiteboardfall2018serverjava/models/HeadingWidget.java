package com.example.whiteboardfall2018serverjava.models;

import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget{
	private String size;

	public String Size() {
		return size;
	}

	public void setOptions(String size) {
		this.size = size;
	}

}
