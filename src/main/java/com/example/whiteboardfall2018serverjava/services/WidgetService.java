package com.example.whiteboardfall2018serverjava.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*", allowCredentials = "true" , allowedHeaders = "*")
public class WidgetService {
	
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	TopicService topicService;
	
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findWidgetsForTopic(@PathVariable("topicId") int topicId,HttpSession session){
		Topic t = topicService.findTopicById(topicId);
		return t.getWidgets();
	}
	
	@GetMapping("/api/widget/{wid}")
	public Optional<Widget> findWidgetById(@PathVariable("wid") int wid,HttpSession session){
		return widgetRepository.findById(wid);
	}
	
	@GetMapping("/api/widgets")
	public List<Widget> findAllWidgets(){
		return (List<Widget>) widgetRepository.findAll();
	}
}
