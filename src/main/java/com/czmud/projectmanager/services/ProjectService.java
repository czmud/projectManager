package com.czmud.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.czmud.projectmanager.models.Project;
import com.czmud.projectmanager.models.User;
import com.czmud.projectmanager.repositories.ProjectRepository;
import com.czmud.projectmanager.repositories.UserRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserRepository userRepository;
	
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	public List<Project> getAllProjectsNotUser( Long userId ){
		Optional<User> checkForUser = userRepository.findById( userId );
		if( checkForUser.isEmpty() ) {
			return null;
		}
		
		return projectRepository.findByTeamMembersNotContains( checkForUser.get() ); 
		
	}
	
	public Project getProjectById( Long id ) {
		Optional<Project> checkForProject = projectRepository.findById( id );
		if( checkForProject.isEmpty() ) {
			return null;
		}
		return checkForProject.get();
	}
	
	public Project createNewProject( Project project ) {
		return projectRepository.save( project );
	}
	
	public Project updateProject( Project editProject, BindingResult bindingResult ) {
		
		Optional<Project> checkForProject = projectRepository.findById( (Long) editProject.getId() );
		if( checkForProject.isEmpty() ) {
			bindingResult.rejectValue("id", "BadProjectId", "Post id does not match existing project");
			return null;
		}
		
		Project oldProject = checkForProject.get();
		
		if ( !oldProject.getOwner().getId().equals(editProject.getOwner().getId()) ) {
			bindingResult.rejectValue("owner", "BadOwnerId", "User does not match user id for project");
			return null;
		}
		
		editProject.setTeamMembers(oldProject.getTeamMembers());
			
		return projectRepository.save( editProject );
	}
	
	public void addUserToProject( Long projectId, Long userId ) {
		
		Optional<Project> checkForProject = projectRepository.findById( projectId );
		if( checkForProject.isEmpty() ) {
			return;
		}
		Project updateProject = checkForProject.get();
		
		Optional<User> checkForUser = userRepository.findById( userId );
		if( checkForUser.isEmpty() ) {
			return;
		}		
		User addUser = checkForUser.get();
		
		if(updateProject.getTeamMembers().contains(addUser) ) {
			return;
		}
		
		updateProject.getTeamMembers().add( addUser );
		
		projectRepository.save( updateProject );
	}
	
	public void removeUserFromProject( Long projectId, Long userId ) {
		Optional<Project> checkForProject = projectRepository.findById( projectId );
		if( checkForProject.isEmpty() ) {
			return;
		}
		Project updateProject = checkForProject.get();
		
		Optional<User> checkForUser = userRepository.findById( userId );
		if( checkForUser.isEmpty() ) {
			return;
		}		
		User removeUser = checkForUser.get();
		
		if( ! updateProject.getTeamMembers().contains(removeUser) ) {
			return;
		}
		
		updateProject.getTeamMembers().remove(removeUser);
		
		projectRepository.save( updateProject );
	}
	
	
	public void deleteProjectById( Long projectId, Long userId ) {
		
		Optional<Project> checkForProject = projectRepository.findById( projectId );
		if( checkForProject.isEmpty() ) {
			return;
		}
		
		Project deleteProject = checkForProject.get();
		
		if( ! deleteProject.getOwner().getId().equals(userId) ) {
			return;
		}
		
		projectRepository.deleteById( projectId );
		
		return;
	}
		
	
}
