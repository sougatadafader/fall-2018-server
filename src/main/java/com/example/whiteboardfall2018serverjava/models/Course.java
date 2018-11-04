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
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
	  private String title;
	  
	  @ManyToOne
		@JsonIgnore
		private User user;
	  
	  @OneToMany(mappedBy="course",cascade=CascadeType.ALL,orphanRemoval=true)
	  private List<Module> modules = new ArrayList<Module>();
	  
	public Course(int i, String string) {
		setId(i);
		setTitle(string);
	}
	public Course() {}
	public long getId() {
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
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	  
}


