package de.te2m.project.service.resources.service;

import de.te2m.project.api.impl.model.RiskEditCommand;
import de.te2m.project.api.impl.model.RiskInstanceCommand;
import de.te2m.project.service.core.boundary.RiskFacade;
import de.te2m.project.service.core.entity.CommandLog;
import de.te2m.project.service.core.entity.project.Project;
import de.te2m.project.service.core.entity.risk.Risk;
import de.te2m.project.service.core.entity.usecase.UseCase;
import de.te2m.project.service.core.entity.usecase.UseCaseType;
import de.te2m.project.service.resources.exception.EntityNotFoundException;
import de.te2m.project.service.resources.exception.InvalidCommandException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static de.te2m.project.service.resources.MappingUtils.toJsonString;

@ApplicationScoped
public class RiskEntityService extends BaseService {
	@Inject
	RiskFacade riskFacade;

	public Risk findRiskByID(String projectID, String riskID) {
		Risk risk = riskFacade.find(riskID);
		if (null != risk && risk.getProject().getId().equals(projectID)) {
			return risk;
		}
		return null;
	}

	@Transactional
	public Risk createRiskFromCommand(String projectID, RiskEditCommand command) {

		validateCommandId(command.getMessageId());

		Project project = determineWriteableProject(projectID);

		Risk risk = new Risk();
		risk.setName(command.getName());
		risk.setDescription(command.getDescription());
		CommandLog log = new CommandLog();
		log.setMessageId(command.getMessageId());

		log.setRawData(toJsonString(command));

		project.addRisks(risk);
		getCommandLogger().create(log);
		riskFacade.create(risk);
		getProjectFacade().edit(project);
		return risk;
	}

	@Transactional
	private Risk updateRiskFromCommand(String projectID, Risk risk, RiskInstanceCommand command) {

		if (null== risk){
			throw new EntityNotFoundException();
		}
		risk.setName(command.getName());
		risk.setDescription(command.getDescription());
		CommandLog log = new CommandLog();
		log.setMessageId(command.getMessageId());

		log.setRawData(toJsonString(command));
		getCommandLogger().create(log);
		riskFacade.edit(risk);
		return risk;
	}

	public Risk executeCommandOnRisk(String projectID, String riskID, RiskInstanceCommand command) {

		validateCommandId(command.getMessageId());

		Project project = determineWriteableProject(projectID);

		Risk risk = findRiskByID(projectID, riskID);

		switch (command.getCommandType()) {
		case "RiskEdit":
			return updateRiskFromCommand(projectID,
					risk,
					command);
		case "RiskAddComment":
			System.out.println(command.getCommandType());
			break;
		case "RiskDelete":
			System.out.println(command.getCommandType());
			break;
		default:
			throw new InvalidCommandException("Command with unknown command type received");
		}
		return risk;

	}
}
