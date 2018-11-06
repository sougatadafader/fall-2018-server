package com.example.whiteboardfall2018serverjava.models;

import javax.persistence.Entity;

@Entity
public class ListWidget extends Widget{
	private String items;
	private String listType;

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
