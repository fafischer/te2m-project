package de.te2m.project.service.resources.service;

import de.te2m.project.service.core.boundary.CommandLogFacade;
import de.te2m.project.service.core.boundary.ProjectFacade;
import de.te2m.project.service.core.entity.project.Project;
import de.te2m.project.service.resources.exception.DuplicateMessageIDException;
import de.te2m.project.service.resources.exception.EntityNotFoundException;
import de.te2m.project.service.resources.exception.InvalidCommandException;
import javax.inject.Inject;

public class BaseService {
	@Inject
	private CommandLogFacade commandLogger;

	@Inject
	private ProjectFacade projectFacade;

	public void validateCommandId(String messageId) {
		String commandID = messageId;

		if (commandLogger.isCommandAlreadyProcessed(commandID)) {
			throw new DuplicateMessageIDException("Command with ID " + commandID + " is already processed");
		}
	}

	public Project determineWriteableProject(String projectID) {
		Project project = projectFacade.find(projectID);

		if (null == project) {
			throw new EntityNotFoundException("Project with ID " + projectID + "not found");
		}
		if (project.isLocked()) {
			throw new InvalidCommandException("Project is locked");
		}
		return project;
	}

	public CommandLogFacade getCommandLogger() {
		return commandLogger;
	}

	protected ProjectFacade getProjectFacade() {
		return projectFacade;
	}
}
