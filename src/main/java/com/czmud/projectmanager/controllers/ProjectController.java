package com.czmud.projectmanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.czmud.projectmanager.models.Project;
import com.czmud.projectmanager.models.Task;
import com.czmud.projectmanager.services.ProjectService;
import com.czmud.projectmanager.services.UserService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/dashboard")
	public String projectDashboard( Model model, HttpSession session ) {
		
		if( session.getAttribute("userId") == null ) {
			session.invalidate();
			return "redirect:/";
		}
		
		model.addAttribute("user", userService.getUserById( (Long) session.getAttribute("userId") ));
		model.addAttribute("projectsUserNotOn", projectService.getAllProjectsNotUser( (Long) session.getAttribute( "userId" )) );
		
		return "dashboard.jsp";
	}
	
	@GetMapping("/new")
	public String newProjectForm( Model model, HttpSession session) {
		
		if( session.getAttribute("userId") == null ) {
			session.invalidate();
			return "redirect:/";
		}
		
		model.addAttribute("user", userService.getUserById( (Long) session.getAttribute("userId") ));
		model.addAttribute("newProject", new Project() );
		
		return "projectnew.jsp";
	}
	
	@PostMapping("/create-new-project")
	public String createNewProject( @Valid @ModelAttribute("newProject") Project newProject,
								 BindingResult bindingResult ) {
		
		if( bindingResult.hasErrors() ) {
			return "projectnew.jsp";
		}
		
		String projectId = projectService.createNewProject( newProject ).getId().toString();
		
		return "redirect:/projects/"+projectId;
	}
	
	@GetMapping("/{projectId}")
	public String viewProject( @PathVariable("projectId") Long projectId,
							Model model, HttpSession session ) {
		
		if( session.getAttribute("userId") == null ) {
			session.invalidate();
			return "redirect:/";
		}
		
		model.addAttribute("user", userService.getUserById( (Long) session.getAttribute("userId") ));
		model.addAttribute("project", projectService.getProjectById( projectId ));
		
		return "projectview.jsp";
	}
	
	@GetMapping("/{projectId}/tasks")
	public String viewProjectWithTasks( @PathVariable("projectId") Long projectId,
										Model model, HttpSession session ) {
		
		if( session.getAttribute("userId") == null ) {
			session.invalidate();
			return "redirect:/";
		}
		
		model.addAttribute("user", userService.getUserById( (Long) session.getAttribute("userId") ));
		model.addAttribute("project", projectService.getProjectById( projectId ));
		model.addAttribute("newTask", new Task() );
		
		return "projecttasks.jsp";
	}
	
	
	@GetMapping("/edit/{projectId}")
	public String editProjectForm( @PathVariable("projectId") Long projectId,
								Model model, HttpSession session ) {
		
		if( session.getAttribute("userId") == null ) {
			session.invalidate();
			return "redirect:/";
		}
		
		Project editProject = projectService.getProjectById( projectId );
		
		
		if( !session.getAttribute("userId").equals( editProject.getOwner().getId()) ) {
			return "redirect:/projects/"+projectId;
		}
		
		model.addAttribute("editProject", editProject);
		
		return "projectedit.jsp";
	}
	
	@PutMapping("/edit-project/{id}")
	public String updateProject( @Valid @ModelAttribute("editProject") Project editProject,
							BindingResult bindingResult,
							Model model, HttpSession session,
							@PathVariable("id") Long projectId) {
				
		if( !session.getAttribute("userId").equals( editProject.getOwner().getId()) ) {
			return "redirect:/projects/"+projectId;
		}
		
		if( bindingResult.hasErrors() ) {
			return "projectedit.jsp";
		}
		
		projectService.updateProject( editProject, bindingResult );
		
		if( bindingResult.hasFieldErrors( "owner" ) ) {
			return "redirect:/users/log-user-out";
		}
		
		if( bindingResult.hasFieldErrors( "id" ) ) {
			return "redirect:/projects";
		}
		
		
		return "redirect:/projects/"+projectId;
	}
	
	@PostMapping("/join-team/{projectId}")
	public String addUserToProject( HttpSession session,
									@PathVariable("projectId") Long projectId ) {
		
		
		
		projectService.addUserToProject( projectId, (Long) session.getAttribute("userId") );
		
		
		return "redirect:/projects/dashboard";
	}
	
	@DeleteMapping("/leave-team/{projectId}")
	public String removeUserFromProject( HttpSession session,
										 @PathVariable("projectId") Long projectId ) {
		
		
		
		projectService.removeUserFromProject( projectId, (Long) session.getAttribute("userId") );
		
		
		return "redirect:/projects/dashboard";
	}
	
	
	
	@DeleteMapping("/delete-project/{projectId}")
	public String destroyProject( @PathVariable("projectId") Long projectId,
							   HttpSession session ) {
		
		projectService.deleteProjectById(projectId, (Long) session.getAttribute("userId"));
		
		return "redirect:/projects/dashboard";
	}
	
	
	
	
	
	
}
