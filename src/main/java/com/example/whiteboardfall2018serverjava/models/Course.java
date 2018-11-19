package com.example.whiteboardfall2018serverjava.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	public Date getCreated() {
		return created;
	}
	
	@OneToMany(mappedBy="course",cascade=CascadeType.ALL,orphanRemoval=true)
	  private List<Module> modules = new ArrayList<Module>();
	  
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course(String string) {
		this.title = string;
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
	public void update(Course course) {
		this.title = course.title != null ? course.title : this.title;
		this.modules = course.modules != null ? course.modules : this.modules;
	}
	  
}


