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
public class Module {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
	  private String title;
	  
	  @ManyToOne
	  @JsonIgnore
	  private Course course;
	  
	  @OneToMany(mappedBy="module",cascade=CascadeType.ALL,orphanRemoval=true)
	  private List<Lesson> lessons = new ArrayList<Lesson>();
	  
	public Module(String string) {
		this.title = string;
	}
	public Module() {}
	public int getId() {
		return id;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
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
	public void update(Module m) {
		this.title = m.title != null ? m.title : this.title;
		this.lessons = m.lessons != null ? m.lessons : this.lessons;
	}
	  
	  
}
