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
	
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	@OneToMany(mappedBy="lesson",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Topic> topics = new ArrayList<Topic>();

	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Lesson(String string) {
		this.title = string;
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
	public void update(Lesson lesson) {
		// TODO Auto-generated method stub
		this.title = lesson.title != null ? lesson.title : this.title;
		this.topics = lesson.topics != null ? lesson.topics : this.topics;
		
	}

}
