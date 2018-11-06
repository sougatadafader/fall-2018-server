package com.example.whiteboardfall2018serverjava.models;

import javax.persistence.Entity;

@Entity
public class LinkWidget extends Widget{
	private String src;
	private String linkText;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}
}
