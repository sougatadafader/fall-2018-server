package com.example.whiteboardfall2018serverjava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.whiteboardfall2018serverjava.models.Course;
import com.example.whiteboardfall2018serverjava.models.Lesson;
import com.example.whiteboardfall2018serverjava.models.Module;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.repositories.LessonRepository;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*" , allowCredentials = "true" , allowedHeaders = "*")
public class TopicService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	LessonService lessonService;
	
	@GetMapping("/api/lesson/{lid}/topic")
	public List<Topic> findTopicsForLesson(@PathVariable("lid") int lessonId) {
		return (List<Topic>) lessonRepository.findById(lessonId).get().getTopics();
	}
	
	@GetMapping("/api/topic")
	public List<Topic> findAllTopics() {
		return (List<Topic>) topicRepository.findAll();
	}
	
	@PostMapping("/api/lesson/{lid}/topic")
	public Topic createTopic(@RequestBody Topic topic, @PathVariable("lid") int lId,HttpSession session) {
		topic.setLesson(lessonService.findLessonById(lId, session));
		return topicRepository.save(topic);
	}
	
	@GetMapping("/api/topic/{tid}")
	public Topic findTopicById(@PathVariable("tid") int tId) {
		Topic topic = null;
		Optional<Topic> reqdTopic = topicRepository.findById(tId);
		if(reqdTopic.isPresent())
		{
			topic =  reqdTopic.get();
		}
		return topic;
	}
	
	@PutMapping("/api/topic/{tid}")
	public Topic updateTopic(@PathVariable("tid") int tId, @RequestBody Topic topic, HttpSession session) {
		Topic t =  findTopicById(tId);
		t.update(topic);
		return topicRepository.save(t);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//  private List<Topic> topics = new ArrayList<Topic>();
//	@GetMapping("/api/lesson/{lid}/topic")
//	public List<Topic> findAllTopics(@PathVariable("lid") int lId, HttpSession session) {
//		Lesson lesson = lessonService.findLessonById(lId, session);
//		topics = lesson.getTopics();
//		if(topics.isEmpty())
//		{
//			Topic t1 = new Topic((int) System.currentTimeMillis(),"Topic a");
//			Topic t2 = new Topic((int) System.currentTimeMillis(),"Topic b");
//			topics.add(t1);
//			topics.add(t2);
//		}
//		return topics;
//	}
//
//	@PostMapping("/api/lesson/{lid}/topic")
//	public List<Topic> createTopic(@RequestBody Topic topic, @PathVariable("lid") int lId, HttpSession session) {
//		Lesson lesson = lessonService.findLessonById(lId, session);
//		topics = lesson.getTopics();
//		topic.setId((int)System.currentTimeMillis());
//		topics.add(topic);
//		return topics;
//	}
//
//	/* Retrieves Lesson whose id is lid */
//	@GetMapping("/api/topic/{tid}")
//	public Topic findTopicById(@PathVariable("tid") int tId, HttpSession session) {
//		
//		List<Course> courses = courseService.findAllCourses(session);
//		List<Module> modules = new ArrayList<Module>();
//		List<Lesson> lessons = new ArrayList<Lesson>();
//		
//		Topic reqdTopic = null;
//		for (Course c : courses) {
//			modules = c.getModules();
//			for (Module m : modules) {
//				lessons = m.getLessons();
//				for (Lesson l : lessons) {
//					topics = l.getTopics();
//					{
//						for(Topic t: topics)
//						{
//							if(t.getId() == tId)
//							{
//								reqdTopic = t;
//								return reqdTopic;
//							}
//						}
//					}
//				}
//			}
//		}
//		return null;
//	}
//
//	@PutMapping("/api/topic/{tid}")
//	public Topic updateTopic(@PathVariable("tid") int tId, @RequestBody Topic topic, HttpSession session) {
//		List<Course> courses = new ArrayList<Course>(courseService.findAllCourses(session));
//		List<Module> modules = new ArrayList<Module>();
//		List<Lesson> lessons = new ArrayList<Lesson>();
//		
//		for (Course c : courses) {
//			modules = c.getModules();
//			for (Module m : modules) {
//				lessons = m.getLessons();
//				int ctr = 0;
//				for (Lesson l : lessons) {
//					topics = l.getTopics();
//					for(Topic t: topics)
//					{
//						if(t.getId()==tId)
//						{
//							topic.setId(tId);
//							topics.set(ctr, topic);
//							return topics.get(ctr);
//						}
//						ctr++;
//					}
//				}
//			}
//		}
//		return null;
//	}
//
//	/* Deletes Lesson whose id is lid */
//	@DeleteMapping("/api/topic/{tid}")
//	public void deleteTopic(@PathVariable("tid") int tId, HttpSession session) {
//		List<Course> courses = courseService.findAllCourses(session);
//		List<Module> modules = new ArrayList<Module>();
//		List<Lesson> lessons = new ArrayList<Lesson>();
//		
//		Topic reqdTopic = null;
//		for (Course c : courses) {
//			modules = c.getModules();
//			for (Module m : modules) {
//				lessons = m.getLessons();
//				for (Lesson l : lessons) {
//					topics = l.getTopics();
//					for(Topic t: topics)
//					{
//						if(t.getId()==tId)
//						{
//							reqdTopic = t;
//							break;
//						}
//					}
//				}
//
//			}
//		}
//		if (reqdTopic != null) {
//			for (Course c : courses) {
//				modules = c.getModules();
//				{
//					for (Module m : modules) {
//						lessons = m.getLessons();
//						for(Lesson l: lessons)
//						{
//							topics = l.getTopics();
//							List<Topic> resultTopics = (List<Topic>) topics.stream().filter(x -> x.getId() != tId)
//									.collect(Collectors.toList());
//							l.setTopics(resultTopics);
//						}
//					}
//				}
//			}
//		}
//	}
}
