package com.example.whiteboardfall2018serverjava.services;

import java.util.List;
import java.util.Optional;

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

import com.example.whiteboardfall2018serverjava.models.LinkWidget;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.LinkWidgetRepository;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*")
public class LinkWidgetService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	LinkWidgetRepository linkWidgetRepository;
	@PostMapping("/api/topic/{topicId}/widget/link")
	
	public List<Widget> createLinkWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody LinkWidget linkWidget) {
		linkWidget.setWidgetType("LINK");
		Topic topic = topicRepository.findById(topicId).get();
		linkWidget.setTopic(topic);
		linkWidget = linkWidgetRepository.save(linkWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/link/widget/{wid}")
	public LinkWidget findWidgetById(@PathVariable("wid") int wid,HttpSession session) {
		LinkWidget lWidget = null;
		Optional<LinkWidget> reqdWidget = linkWidgetRepository.findById(wid);
		if(reqdWidget.isPresent())
		{
			lWidget =  reqdWidget.get();
		}
		
		return lWidget;
	}

	@PutMapping("/api/link/widget/{wid}")
	public LinkWidget updateLinkWidget(
			@PathVariable("wid") int wid,
			@RequestBody LinkWidget linkWidget,
			HttpSession session) {
		
		LinkWidget lw =  findWidgetById(wid,session);
		lw.update(linkWidget);
		return linkWidgetRepository.save(lw);
	}
	
	@DeleteMapping("/api/link/widget/{wid}")
	public void deleteLinkWidget(
			@PathVariable("wid") int wid,
			HttpSession session) {
		
	 linkWidgetRepository.deleteById(wid);
	}
	
	
	
}
