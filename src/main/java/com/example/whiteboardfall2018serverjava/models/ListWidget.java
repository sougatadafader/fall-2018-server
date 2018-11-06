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
	
	public void update(ListWidget listWidget) {
		this.title = listWidget.title != null ? listWidget.title : this.title;
		this.listType = listWidget.listType != null ? listWidget.listType : this.listType;
	}
}
