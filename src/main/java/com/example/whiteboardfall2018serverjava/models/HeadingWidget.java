package com.example.whiteboardfall2018serverjava.models;

import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget{
	
	private String text;
	private String size;

	public String Size() {
		return size;
	}

	public void setOptions(String size) {
		this.size = size;
	}
	
	public void update(HeadingWidget headingWidget) {
		this.title = headingWidget.title != null ? headingWidget.title : this.title;
		this.size = headingWidget.size != null ? headingWidget.size : this.size;
		this.text = headingWidget.text != null ? headingWidget.text : this.text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}