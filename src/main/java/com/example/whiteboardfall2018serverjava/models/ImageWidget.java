package com.example.whiteboardfall2018serverjava.models;

import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget{
	private String src;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
	public void update(ImageWidget imageWidget) {
		this.title = imageWidget.title != null ? imageWidget.title : this.title;
		this.src = imageWidget.src != null ? imageWidget.src : this.src;
	}

}
