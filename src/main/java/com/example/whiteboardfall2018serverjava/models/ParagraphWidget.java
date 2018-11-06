package com.example.whiteboardfall2018serverjava.models;

import javax.persistence.Entity;

@Entity
public class ParagraphWidget extends Widget{
	private String text;
	
	public void update(ParagraphWidget paragraphWidget) {
		this.title = paragraphWidget.title != null ? paragraphWidget.title : this.title;
		this.text = paragraphWidget.text != null ? paragraphWidget.text : this.text;
	}

}
