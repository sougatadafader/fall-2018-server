package com.example.whiteboardfall2018serverjava.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	@OneToMany(mappedBy="topic",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Widget> widget;

	public Topic(int i, String string) {
		id = i;
		title = string;
	}

	public Topic() {
	}
	
	public List<Widget> getWidget() {
		return widget;
	}

	public void setWidget(List<Widget> widget) {
		this.widget = widget;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
