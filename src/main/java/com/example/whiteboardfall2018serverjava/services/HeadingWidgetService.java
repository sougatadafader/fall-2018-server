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

import com.example.whiteboardfall2018serverjava.models.HeadingWidget;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.HeadingWidgetRepository;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*", allowCredentials = "true" , allowedHeaders = "*")
public class HeadingWidgetService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	HeadingWidgetRepository headingWidgetRepository;
	@PostMapping("/api/topic/{topicId}/widget/heading")
	
	public List<Widget> createHeadingWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody HeadingWidget headingWidget) {
		headingWidget.setWidgetType("HEADING");
		Topic topic = topicRepository.findById(topicId).get();
		headingWidget.setTopic(topic);
		headingWidget = headingWidgetRepository.save(headingWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/heading/widget/{wid}")
	public HeadingWidget findWidgetById(@PathVariable("wid") int wid,HttpSession session) {
		HeadingWidget hWidget = null;
		Optional<HeadingWidget> reqdWidget = headingWidgetRepository.findById(wid);
		if(reqdWidget.isPresent())
		{
			hWidget =  reqdWidget.get();
		}
		
		return hWidget;
	}

	@PutMapping("/api/heading/widget/{wid}")
	public HeadingWidget updateHeadingWidget(
			@PathVariable("wid") int wid,
			@RequestBody HeadingWidget headingWidget,
			HttpSession session) {
		
		HeadingWidget hw =  findWidgetById(wid,session);
		hw.update(headingWidget);
		return headingWidgetRepository.save(hw);
	}
	
	@DeleteMapping("/api/HEADING/widget/{wid}")
	public void deleteHeadingWidget(
			@PathVariable("wid") int wid,
			HttpSession session) {
		headingWidgetRepository.deleteById(wid);
	}
}
