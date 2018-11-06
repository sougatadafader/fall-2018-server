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
	
	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	@OneToMany(mappedBy="topic",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Widget> widget;

	public Topic(String string) {
		this.title = string;
	}

	public Topic() {
	}
	
	public List<Widget> getWidgets() {
		return widget;
	}

	public void setWidgets(List<Widget> widget) {
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
	public void update(Topic topic) {
		this.title = topic.title != null ? topic.title : this.title;
		this.widget = topic.widget != null ? topic.widget : this.widget;
	}

}
