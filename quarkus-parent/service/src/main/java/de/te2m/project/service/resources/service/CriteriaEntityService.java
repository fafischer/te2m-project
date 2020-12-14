package de.te2m.project.service.resources.service;

import de.te2m.project.api.impl.model.CriteriaEditCommand;
import de.te2m.project.api.impl.model.CriteriaInstanceCommand;
import de.te2m.project.api.impl.model.RiskInstanceCommand;
import de.te2m.project.service.core.boundary.ClosingCrtiteriaFacade;
import de.te2m.project.service.core.entity.ClosingCriteria;
import de.te2m.project.service.core.entity.CommandLog;
import de.te2m.project.service.core.entity.project.Project;
import de.te2m.project.service.core.entity.risk.Risk;
import de.te2m.project.service.resources.exception.EntityNotFoundException;
import de.te2m.project.service.resources.exception.InvalidCommandException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static de.te2m.project.service.resources.MappingUtils.toJsonString;

@ApplicationScoped
public class CriteriaEntityService extends BaseService {

	@Inject
	ClosingCrtiteriaFacade criteriaFacade;

	public ClosingCriteria findClosingCriteriaByID(String projectID, String riskID) {
		ClosingCriteria ac = criteriaFacade.find(riskID);
		if (null != ac && ac.getProject().getId().equals(projectID)) {
			return ac;
		}
		return null;
	}

	@Transactional
	public ClosingCriteria createCriteriaFromCommand(String projectID, CriteriaEditCommand command) {

		validateCommandId(command.getMessageId());

		Project project = determineWriteableProject(projectID);

		ClosingCriteria useCase = new ClosingCriteria();
		useCase.setName(command.getName());
		useCase.setDescription(command.getDescription());
		CommandLog log = new CommandLog();
		log.setMessageId(command.getMessageId());

		log.setRawData(toJsonString(command));

		project.addClosingCriteria(useCase);
		getCommandLogger().create(log);
		criteriaFacade.create(useCase);
		getProjectFacade().edit(project);
		return useCase;
	}

	@Transactional
	private ClosingCriteria updateACFromCommand(String projectID, ClosingCriteria ac, CriteriaInstanceCommand command) {

		if (null== ac){
			throw new EntityNotFoundException();
		}
		ac.setName(command.getName());
		ac.setDescription(command.getDescription());
		CommandLog log = new CommandLog();
		log.setMessageId(command.getMessageId());

		log.setRawData(toJsonString(command));
		getCommandLogger().create(log);
		criteriaFacade.edit(ac);
		return ac;
	}

	public ClosingCriteria executeCommandOnCriteria(String projectID, String criteriaID, CriteriaInstanceCommand command) {

		validateCommandId(command.getMessageId());

		Project project = determineWriteableProject(projectID);

		ClosingCriteria ac = findClosingCriteriaByID(projectID, criteriaID);

		switch (command.getCommandType()) {
		case "CriteriaEdit":
			return updateACFromCommand(projectID,
					ac,
					command);
		case "CriteriaAddComment":
			System.out.println(command.getCommandType());
			break;
		case "CriteriaDelete":
			System.out.println(command.getCommandType());
			break;
		default:
			throw new InvalidCommandException("Command with unknown command type received");
		}
		return ac;

	}
}
