package de.te2m.project.service.resources.service;

import java.util.List;
import java.util.stream.Collectors;

import de.te2m.project.api.impl.model.*;
import de.te2m.project.service.core.boundary.UseCaseFacade;
import de.te2m.project.service.core.entity.CommandLog;
import de.te2m.project.service.core.entity.project.Project;
import de.te2m.project.service.core.entity.usecase.UseCase;
import de.te2m.project.service.core.entity.usecase.UseCaseType;
import de.te2m.project.service.resources.exception.EntityNotFoundException;
import de.te2m.project.service.resources.exception.InvalidCommandException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static de.te2m.project.service.resources.MappingUtils.*;

@ApplicationScoped
public class UseCaseEntityService extends BaseService {
	@Inject
	UseCaseFacade useCaseFacade;

	protected UseCase updateUseCaseEntity(String projectID, UseCase useCase, String messageID, String name, String description, UseCaseType service, String messageId, String rawContent, boolean create) {
		validateCommandId(messageID);

		Project project = determineWriteableProject(projectID);

		useCase.setName(name);
		useCase.setDescription(description);
		useCase.setUsecaseType(service);
		CommandLog log = new CommandLog();
		log.setMessageId(messageId);
		log.setRawData(rawContent);
		project.addUsecase(useCase);
		getCommandLogger().create(log);
		if (create) {
			useCaseFacade.create(useCase);
		} else {
			useCaseFacade.edit(useCase);
		}
		getProjectFacade().edit(project);
		return useCase;
	}

	@Transactional
	public UseCase createUseCaseFromCommand(String projectID, UseCaseEditCommand command) {
		return updateUseCaseEntity(projectID,
				new UseCase(),
				command.getMessageId(),
				command.getName(),
				command.getDescription(),
				UseCaseType.USECASE,
				command.getMessageId(),
				toJsonString(command),
				true);

	}

	@Transactional
	public UseCase createActorFromCommand(String projectID, ActorEditCommand command) {

		return updateUseCaseEntity(projectID,
				new UseCase(),
				command.getMessageId(),
				command.getName(),
				command.getDescription(),
				UseCaseType.PERSONA,
				command.getMessageId(),
				toJsonString(command),
				true);
	}

	@Transactional
	public UseCase createComponentFromCommand(String projectID, ComponentEditCommand command) {

		return updateUseCaseEntity(projectID,
				new UseCase(),
				command.getMessageId(),
				command.getName(),
				command.getDescription(),
				UseCaseType.SERVICE,
				command.getMessageId(),
				toJsonString(command),
				true);
	}

	public List<UseCaseDataView> findAllUseCases(String projectID) {
		return useCaseFacade.getFilteredList(getProjectFacade().find(projectID), UseCaseType.USECASE).stream()
				.map(uc -> transformUsecaseToDataView((UseCase) uc))
				.collect(Collectors.toList());
	}

	public List<ComponentDataView> findAllComponents(String projectID) {
		return useCaseFacade.getFilteredList(getProjectFacade().find(projectID), UseCaseType.SERVICE).stream()
				.map(uc -> transformComponentToDataView((UseCase) uc))
				.collect(Collectors.toList());
	}

	public List<ActorDataView> findAllActors(String projectID) {
		return useCaseFacade.getFilteredList(getProjectFacade().find(projectID), UseCaseType.PERSONA).stream()
				.map(uc -> transformActorToDataView((UseCase) uc))
				.collect(Collectors.toList());
	}

	public UseCase findActorByID(String projectID, String actorID) {
		return getUseCaseByType(projectID, actorID, UseCaseType.PERSONA);
	}

	public UseCase findComponentByID(String projectID, String componentID) {
		return getUseCaseByType(projectID, componentID, UseCaseType.SERVICE);
	}

	public UseCase findUsecaseByID(String projectID, String usecaseID) {
		return getUseCaseByType(projectID, usecaseID, UseCaseType.USECASE);
	}

	private UseCase getUseCaseByType(String projectID, String usecaseID, UseCaseType usecaseType) {
		UseCase uc = useCaseFacade.find(usecaseID);
		if (null != uc && uc.getProject().getId().equals(projectID) && uc.getUsecaseType() == usecaseType) {
			return uc;
		}
		return null;
	}

	@Transactional
	public UseCase executeCommandOnUsecase(String projectID, String usecaseID, UseCaseInstanceCommand command) {
		UseCase useCase = findUsecaseByID(projectID, usecaseID);
		if (null== useCase){
			throw new EntityNotFoundException();
		}

		switch (command.getCommandType()) {
		case "UseCaseEdit":
			return updateUseCaseEntity(projectID,
					useCase,
					command.getMessageId(),
					command.getName(),
					command.getDescription(),
					UseCaseType.USECASE,
					command.getMessageId(),
					toJsonString(command),
					false);
		case "UseCaseAddComment":
			System.out.println(command.getCommandType());
			break;
		case "UseCaseLinkRisk":
			System.out.println(command.getCommandType());
			break;
		case "UseCaseCreateRisk":
			System.out.println(command.getCommandType());
			break;
		case "UseCaseLinkDependency":
			System.out.println(command.getCommandType());
			break;
		default:
			throw new InvalidCommandException("Command with unknown command type received");
		}
		return useCase;
	}

	@Transactional
	public UseCase executeCommandOnComponent(String projectID, String componentID, ComponentInstanceCommand command) {
		UseCase useCase = findComponentByID(projectID, componentID);
		if (null== useCase){
			throw new EntityNotFoundException();
		}

		switch (command.getCommandType()) {
		case "ComponentEdit":
			return updateUseCaseEntity(projectID,
					useCase,
					command.getMessageId(),
					command.getName(),
					command.getDescription(),
					UseCaseType.SERVICE,
					command.getMessageId(),
					toJsonString(command),
					false);
		case "ComponentDelete":
			System.out.println(command.getCommandType());
			break;
		case "ComponentAddComment":
			System.out.println(command.getCommandType());
			break;
		default:
			throw new InvalidCommandException("Command with unknown command type received");
		}
		return useCase;
	}

	@Transactional
	public UseCase executeCommandOnActor(String projectID, String actorID, ActorInstanceCommand command) {
		UseCase useCase = findComponentByID(projectID, actorID);
		if (null== useCase){
			throw new EntityNotFoundException();
		}

		switch (command.getCommandType()) {
		case "ActorEdit":
			return updateUseCaseEntity(projectID,
					useCase,
					command.getMessageId(),
					command.getName(),
					command.getDescription(),
					UseCaseType.PERSONA,
					command.getMessageId(),
					toJsonString(command),
					false);
		case "ActorDelete":
			System.out.println(command.getCommandType());
			break;
		case "ActorAddComment":
			System.out.println(command.getCommandType());
			break;
		default:
			throw new InvalidCommandException("Command with unknown command type received");
		}
		return useCase;
	}
}
