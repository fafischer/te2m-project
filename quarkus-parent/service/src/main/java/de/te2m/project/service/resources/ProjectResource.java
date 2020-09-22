package de.te2m.project.service.resources;

import java.util.List;
import java.util.stream.Collectors;

import de.te2m.project.api.impl.ProjectApi;
import de.te2m.project.api.impl.model.*;
import de.te2m.project.service.resources.exception.NotImplementedException;
import de.te2m.project.service.resources.service.CriteriaEntityService;
import de.te2m.project.service.resources.service.ProjectEntityService;
import de.te2m.project.service.resources.service.RiskEntityService;
import de.te2m.project.service.resources.service.UseCaseEntityService;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static de.te2m.project.service.resources.MappingUtils.*;

@Path("/project")
public class ProjectResource extends ProjectApi {

	@Inject
	ProjectEntityService projectEntityService;

	@Inject
	UseCaseEntityService usecaseEntityService;

	@Inject
	RiskEntityService riskEntityService;

	@Inject
	CriteriaEntityService criteriaEntityService;

	@Override
	public Response createActor(String projectID, @Valid ActorEditCommand actorEditCommand) {
		return Response.ok().entity(transformActorToDataView(usecaseEntityService.createActorFromCommand(projectID, actorEditCommand))).build();
	}

	@Override
	public Response createComponent(String projectID, @Valid ComponentEditCommand componentEditCommand) {
		return Response.ok().entity(transformComponentToDataView(usecaseEntityService.createComponentFromCommand(projectID,
				componentEditCommand))).build();
	}

	@Override
	public Response createCriteria(String projectID, @Valid CriteriaEditCommand criteriaEditCommand) {
		return Response.ok().entity(transformCriteriaToDataView(criteriaEntityService.createCriteriaFromCommand(projectID,
				criteriaEditCommand))).build();
	}

	@Override
	public Response createRisk(String projectID, @Valid RiskEditCommand riskEditCommand) {
		return Response.ok().entity(transformRiskToDataView(riskEntityService.createRiskFromCommand(projectID, riskEditCommand))).build();
	}

	@Override
	public Response createUsecase(String projectID, @Valid UseCaseEditCommand useCaseEditCommand) {
		return Response.ok().entity(transformUsecaseToDataView(usecaseEntityService.createUseCaseFromCommand(projectID, useCaseEditCommand))).build();
	}

	@Override
	public Response getActorByID(String projectID, String actorID) {
		return Response.ok().entity(transformActorToDataView(usecaseEntityService.findActorByID(projectID, actorID))).build();
	}

	@Override
	public Response getActors(String projectID) {
		return Response.ok().entity(usecaseEntityService.findAllActors(projectID)).build();
	}

	@Override
	public Response getComponentByID(String projectID, String componentID) {
		return Response.ok().entity(transformComponentToDataView(usecaseEntityService.findComponentByID(projectID, componentID))).build();
	}

	@Override
	public Response getComponents(String projectID) {
		return Response.ok().entity(usecaseEntityService.findAllComponents(projectID)).build();
	}

	@Override
	public Response getCriteriaByID(String projectID, String criteriaID) {
		return Response.ok().entity(transformCriteriaToDataView(criteriaEntityService.findClosingCriteriaByID(projectID, criteriaID))).build();
	}

	@Override
	public Response getCriterias(String projectID) {
		return Response.ok().entity(projectEntityService.getProjectEntityByID(projectID).getClosingCriterias().stream()
				.map(ac -> transformCriteriaToDataView(ac))
				.collect(Collectors.toList())).build();
	}

	@Override
	public Response getProjectByID(String projectID) {
		return Response.ok().entity(transformProjectToDataView(projectEntityService.getProjectEntityByID(projectID))).build();
	}

	@Override
	public Response getRiskByID(String projectID, String riskID) {
		return Response.ok().entity(transformRiskToDataView(riskEntityService.findRiskByID(projectID, riskID))).build();
	}

	@Override
	public Response getRisksForProject(String projectID) {
		return Response.ok().entity(projectEntityService.getProjectEntityByID(projectID).getRisks().stream()
				.map(risk -> transformRiskToDataView(risk))
				.collect(Collectors.toList())).build();
	}

	@Override
	public Response getUsecaseByID(String projectID, String usecaseID) {
		return Response.ok().entity(transformUsecaseToDataView(usecaseEntityService.findUsecaseByID(projectID, usecaseID))).build();
	}

	@Override
	public Response getUsecaseDependenciesByID(String projectID, String usecaseID) {
		throw new NotImplementedException();
	}

	@Override
	public Response getUsecases(String projectID) {
		return Response.ok().entity(usecaseEntityService.findAllUseCases(projectID)).build();
	}

	@Override
	public Response postCommandToProject(String projectID, @Valid ProjectInstanceCommand command) {
		return Response.ok().entity(transformProjectToDataView(projectEntityService.executeCommandOnProject(projectID, command))).build();
	}

	@Override
	public Response updateActor(String projectID, String actorID, @Valid ActorInstanceCommand command) {
		return Response.ok().entity(transformActorToDataView(usecaseEntityService.executeCommandOnActor(projectID, actorID, command))).build();
	}

	@Override
	public Response updateComponent(String projectID, String componentID, @Valid ComponentInstanceCommand command) {
		return Response.ok().entity(transformComponentToDataView(usecaseEntityService.executeCommandOnComponent(projectID, componentID, command))).build();
	}

	@Override
	public Response updateCriterias(String projectID, String criteriaID, @Valid CriteriaInstanceCommand command) {
		return Response.ok().entity(transformCriteriaToDataView(criteriaEntityService.executeCommandOnCriteria(projectID, criteriaID, command))).build();
	}

	@Override
	public Response updateRisk(String projectID, String riskID, @Valid RiskInstanceCommand command) {
		return Response.ok().entity(transformRiskToDataView(riskEntityService.executeCommandOnRisk(projectID, riskID, command))).build();
	}

	@Override
	public Response updateUsecase(String projectID, String usecaseID, @Valid UseCaseInstanceCommand command) {
		return Response.ok().entity(transformUsecaseToDataView(usecaseEntityService.executeCommandOnUsecase(projectID, usecaseID, command))).build();
	}

}
