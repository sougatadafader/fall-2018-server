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

import com.example.whiteboardfall2018serverjava.models.ParagraphWidget;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.ParagraphWidgetRepository;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*")
public class ImageWidgetService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	ParagraphWidgetRepository paragraphWidgetRepository;
	@PostMapping("/api/topic/{topicId}/widget/image")
	
	public List<Widget> createParagraphWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody ParagraphWidget paragraphWidget) {
		paragraphWidget.setWidgetType("PARAGRAPH");
		Topic topic = topicRepository.findById(topicId).get();
		paragraphWidget.setTopic(topic);
		paragraphWidget = paragraphWidgetRepository.save(paragraphWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/image/widget/{wid}")
	public ParagraphWidget findWidgetById(@PathVariable("wid") int wid,HttpSession session) {
		ParagraphWidget pWidget = null;
		Optional<ParagraphWidget> reqdWidget = paragraphWidgetRepository.findById(wid);
		if(reqdWidget.isPresent())
		{
			pWidget =  reqdWidget.get();
		}
		return pWidget;
	}

	@PutMapping("/api/image/widget/{wid}")
	public ParagraphWidget updateParagraphWidget(
			@PathVariable("wid") int wid,
			@RequestBody ParagraphWidget paragraphWidget,
			HttpSession session) {
		
		ParagraphWidget pw =  findWidgetById(wid,session);
		pw.update(paragraphWidget);
		return paragraphWidgetRepository.save(pw);
	}
	
	@DeleteMapping("/api/image/widget/{wid}")
	public void deleteLinkWidget(
			@PathVariable("wid") int wid,
			HttpSession session) {
		paragraphWidgetRepository.deleteById(wid);
	}
}
