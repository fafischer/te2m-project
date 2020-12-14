package de.te2m.project.service.resources.service;

import java.util.List;
import java.util.stream.Collectors;

import de.te2m.project.api.impl.model.ProjectDataView;
import de.te2m.project.api.impl.model.ProjectEditCommand;
import de.te2m.project.api.impl.model.ProjectInstanceCommand;
import de.te2m.project.service.core.entity.CommandLog;
import de.te2m.project.service.core.entity.project.Project;
import de.te2m.project.service.resources.exception.EntityNotFoundException;
import de.te2m.project.service.resources.exception.InvalidCommandException;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import static de.te2m.project.service.resources.MappingUtils.toJsonString;
import static de.te2m.project.service.resources.MappingUtils.transformProjectToDataView;

@ApplicationScoped
public class ProjectEntityService extends BaseService {

	@Transactional
	public Project updateProjectFromCommand(Project project, ProjectInstanceCommand command) {

		String commandID = command.getMessageId();

		if (getCommandLogger().isCommandAlreadyProcessed(commandID)) {
			throw new EntityNotFoundException("Command with ID " + commandID + " is already processed");
		}

		if (project.isLocked()) {
			throw new InvalidCommandException("Project is locked");
		}

		project.setName(command.getName());
		project.setDescription(command.getDescription());
		CommandLog log = new CommandLog();
		log.setMessageId(command.getMessageId());

		log.setRawData(toJsonString(command));

		getCommandLogger().create(log);
		getProjectFacade().edit(project);
		return project;
	}

	@Transactional
	public Project createProjectFromCommand(ProjectEditCommand projectEditCommand) {

		String commandID = projectEditCommand.getMessageId();

		if (getCommandLogger().isCommandAlreadyProcessed(commandID)) {
			throw new EntityNotFoundException("Command with ID " + commandID + " is already processed");
		}
		Project project = new Project();
		project.setName(projectEditCommand.getName());
		project.setDescription(projectEditCommand.getDescription());
		CommandLog log = new CommandLog();
		log.setMessageId(projectEditCommand.getMessageId());

		log.setRawData(toJsonString(projectEditCommand));

		getCommandLogger().create(log);
		getProjectFacade().create(project);
		return project;
	}

	public List<ProjectDataView> findAllProjects() {
		return getProjectFacade().findAll().stream()
				.map(project -> transformProjectToDataView(project))
				.collect(Collectors.toList());
	}

	public Project getProjectEntityByID(String projectID) {
		Project project = getProjectFacade().find(projectID);
		if (null == project) {
			throw new EntityNotFoundException();
		}
		return project;
	}

	public Project executeCommandOnProject(String projectID, ProjectInstanceCommand command) {
		Project project = getProjectEntityByID(projectID);

		switch (command.getCommandType()) {
		case "ProjectEdit":
			updateProjectFromCommand(project, command);
			break;
		case "ProjectLock":
			System.out.println(command.getCommandType());
			break;
		case "ProjectUnlock":
			System.out.println(command.getCommandType());
			break;
		case "ProjectAddComment":
			System.out.println(command.getCommandType());
			break;
		default:
			throw new InvalidCommandException("Command with unknown command type received");
		}
		return project;
	}

}
