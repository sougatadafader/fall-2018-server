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

import com.example.whiteboardfall2018serverjava.models.ImageWidget;
import com.example.whiteboardfall2018serverjava.models.ParagraphWidget;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.ImageWidgetRepository;
import com.example.whiteboardfall2018serverjava.repositories.ParagraphWidgetRepository;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*", allowCredentials = "true" , allowedHeaders = "*")
public class ImageWidgetService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	ImageWidgetRepository imageWidgetRepository;
	@PostMapping("/api/topic/{topicId}/widget/IMAGE")
	
	public List<Widget> createImageWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody ImageWidget iWidget) {
		iWidget.setWidgetType("IMAGE");
		Topic topic = topicRepository.findById(topicId).get();
		iWidget.setTopic(topic);
		iWidget = imageWidgetRepository.save(iWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/IMAGE/widget/{wid}")
	public ImageWidget findWidgetById(@PathVariable("wid") int wid,HttpSession session) {
		ImageWidget iWidget = null;
		Optional<ImageWidget> reqdWidget = imageWidgetRepository.findById(wid);
		if(reqdWidget.isPresent())
		{
			iWidget =  reqdWidget.get();
		}
		return iWidget;
	}

	@PutMapping("/api/IMAGE/widget/{wid}")
	public ImageWidget updateImageWidget(
			@PathVariable("wid") int wid,
			@RequestBody ImageWidget imageWidget,
			HttpSession session) {
		
		ImageWidget i =  findWidgetById(wid,session);
		i.update(imageWidget);
		return imageWidgetRepository.save(i);
	}
	
	@DeleteMapping("/api/IMAGE/widget/{wid}")
	public void deleteImageWidget(
			@PathVariable("wid") int wid,
			HttpSession session) {
		imageWidgetRepository.deleteById(wid);
	}
}
