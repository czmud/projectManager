package com.czmud.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.czmud.projectmanager.models.Task;
import com.czmud.projectmanager.services.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/create-new-task")
	public String createNewTask( @ModelAttribute("newTask") Task newTask ) {
		
		
		Long projectId = taskService.createNewTask( newTask ).getProject().getId();
		
		return "redirect:/projects/"+projectId+"/tasks";
	}
	
}
