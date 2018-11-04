package com.example.whiteboardfall2018serverjava.models;

import java.util.ArrayList;
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
public class Lesson {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;

	@ManyToOne
	@JsonIgnore
	private Module module;
	
	@OneToMany(mappedBy="lesson",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Topic> topics = new ArrayList<Topic>();

	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Lesson(int i, String string) {
		id = i;
		title = string;
	}

	public Lesson() {
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
