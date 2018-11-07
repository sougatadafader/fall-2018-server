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

import com.example.whiteboardfall2018serverjava.models.ListWidget;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.ListWidgetRepository;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*")
public class ListWidgetService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	ListWidgetRepository listWidgetRepository;
	@PostMapping("/api/topic/{topicId}/widget/list")
	
	public List<Widget> createListWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody ListWidget listWidget) {
		listWidget.setWidgetType("LIST");
		Topic topic = topicRepository.findById(topicId).get();
		listWidget.setTopic(topic);
		listWidget = listWidgetRepository.save(listWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/list/widget/{wid}")
	public ListWidget findWidgetById(@PathVariable("wid") int wid,HttpSession session) {
		ListWidget lWidget = null;
		Optional<ListWidget> reqdWidget = listWidgetRepository.findById(wid);
		if(reqdWidget.isPresent())
		{
			lWidget =  reqdWidget.get();
		}
		
		return lWidget;
	}

	@PutMapping("/api/list/widget/{wid}")
	public ListWidget updateListWidget(
			@PathVariable("wid") int wid,
			@RequestBody ListWidget listWidget,
			HttpSession session) {
		
		ListWidget lw =  findWidgetById(wid,session);
		lw.update(listWidget);
		return listWidgetRepository.save(lw);
	}
	
	@DeleteMapping("/api/list/widget/{wid}")
	public void deleteListWidget(
			@PathVariable("wid") int wid,
			HttpSession session) {
		
	 listWidgetRepository.deleteById(wid);
	}
	
	
	
}
