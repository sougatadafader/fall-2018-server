package com.example.whiteboardfall2018serverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class WidgetService {
	
	@Autowired
	WidgetRepository wr;
	
	@GetMapping("api/widget")
public List<Widget> findAllWidgets(){
		return (List<Widget>)wr.findAll();
	}
}
