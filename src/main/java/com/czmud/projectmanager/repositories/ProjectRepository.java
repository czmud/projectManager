package com.czmud.projectmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czmud.projectmanager.models.Project;
import com.czmud.projectmanager.models.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	List<Project> findAll();
	
	List<Project> findByTeamMembersNotContains( User user );
	
	Optional<Project> findById( Long id );

}
