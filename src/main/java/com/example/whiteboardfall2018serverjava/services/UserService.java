package com.example.whiteboardfall2018serverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.User;


import com.example.whiteboardfall2018serverjava.models.Course;
import com.example.whiteboardfall2018serverjava.models.Lesson;
import com.example.whiteboardfall2018serverjava.models.Module;
import com.example.whiteboardfall2018serverjava.models.Topic;

import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*" , allowCredentials = "true" , allowedHeaders = "*")
public class UserService {
	
	List<User> users = new ArrayList<User>();
	@Autowired
	UserRepository userRepository;
	
	static String[] usernames    = {"alice", "bob", "charlie"};
	static String[] courseTitles = {"cs5200", "cs5610", "cs5500"};
	static String[] moduleTitles = {"Module 1", "Module 2"};
	static String[] lessonTitles = {"lesson 1", "lesson 2"};
	static String[] topicTitles = {"topic 1", "topic 2"};
	static String[] widgetTitles = {"widget 1", "widget 2"};
	/*{
		List<Widget> widgets = new ArrayList<Widget>();
		for(String widgetTitle : widgetTitles) {
			Widget widget = new Widget(widgetTitle);
			widget.setId((int)(Math.random() * Integer.MAX_VALUE));
			widgets.add(widget);
		}
		List<Topic> topics = new ArrayList<Topic>();
		for(String topicTitle : topicTitles) {
			Topic topic = new Topic(topicTitle);
			topic.setId((int)(Math.random() * Integer.MAX_VALUE));
			if(topicTitle.equals("topic 1")) {
				topic.setWidgets(widgets);
			}
			topics.add(topic);
		}
		List<Lesson> lessons = new ArrayList<Lesson>();
		for(String lessonTitle : lessonTitles) {
			Lesson lesson = new Lesson(lessonTitle);
			lesson.setId((int)(Math.random() * Integer.MAX_VALUE));
			if(lessonTitle.equals("lesson 1")) {
				lesson.setTopics(topics);
			}
			lessons.add(lesson);
		}
		List<Module> modules = new ArrayList<Module>();
		for(String moduleTitle: moduleTitles) {
			Module module = new Module(moduleTitle);
			module.setId((int)(Math.random() * Integer.MAX_VALUE));
			if(moduleTitle.equals("Module 1")) {
				module.setLessons(lessons);
			}
			modules.add(module);
		}
		List<Course> courses = new ArrayList<Course>();
		for(String courseTitle: courseTitles) {
			Course course = new Course(courseTitle);
			course.setId((int)(Math.random() * Integer.MAX_VALUE));
			if(courseTitle.equals("cs5200")) {
				course.setModules(modules);
			}
			courses.add(course);
		}
		for(String username: usernames) {
			User u = new User();
			u.setUserId((int)(Math.random() * Integer.MAX_VALUE));
			u.setUsername(username);
			u.setPassword("1234");
			if(!username.isEmpty())
			{
				u.setCourses(courses);
			}
			users.add(u);
		}
	}*/
	
	
	
	/*Return a list of all the users*/
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>)userRepository.findAll();
		
	}
	
	/*Return a user with user.id equal to id parameter*/
	@GetMapping("/api/user/{id}")
	public User findUserById(@PathVariable ("id") int id) {
		User reqdUser = null;
		for(User u : users)
		{
			if(u.getUserId()==(id))
			{
				reqdUser = u;
			}
		}
		return reqdUser;
	}
	
	/*
	If user.username does not already exist, 
	then add the user to the list of registered users 
	and make the user the current user */
	
	@PostMapping("/api/register")
	public User register( @RequestBody User user,HttpSession session) {
		
	
		for(User u : users)
		{
			System.out.println(user.getFirstName());
			if(u.getUsername().equals(user.getUsername()))
			{
				return null;
			}
		}
		session.setAttribute("currentUser", user);
		userRepository.save(user);
		users.add(user);
		return user;
	}
	
	/* Return the current user */
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");	
		return currentUser;
	}
	
	/* Remove the current user from the session */
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	/* If user.username and password exist
	  then make the user the current user */
	@PostMapping("/api/login")
	public User login(
			@RequestBody User credentials,
			HttpSession session) {
		users= (List<User>) userRepository.findAll();
	 for (User user : users) {
	  if( user.getUsername().equalsIgnoreCase(credentials.getUsername())
	   && user.getPassword().equalsIgnoreCase(credentials.getPassword())) {
	    session.setAttribute("currentUser", user);
	    return user;
	  }
	 }
	 return null;
	}
}
