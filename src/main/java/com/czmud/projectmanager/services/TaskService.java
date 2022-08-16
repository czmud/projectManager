package com.czmud.projectmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czmud.projectmanager.models.Task;
import com.czmud.projectmanager.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	public Task createNewTask( Task newTask ) {
		return taskRepository.save( newTask );
	}
	
}
