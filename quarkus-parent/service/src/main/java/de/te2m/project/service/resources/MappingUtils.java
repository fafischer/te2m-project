package de.te2m.project.service.resources;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.te2m.project.api.impl.model.*;
import de.te2m.project.service.core.entity.ClosingCriteria;
import de.te2m.project.service.core.entity.project.Project;
import de.te2m.project.service.core.entity.risk.Risk;
import de.te2m.project.service.core.entity.usecase.UseCase;
import de.te2m.project.service.core.entity.usecase.UseCaseDependencyType;
import de.te2m.project.service.core.entity.usecase.UseCaseType;
import de.te2m.project.service.resources.exception.MappingException;

public class MappingUtils {

	public static OffsetDateTime dateToOffsetDateTime(Date date) {

		if (null == date) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime();
	}

	public static String toJsonString(Object toBeSerialized) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(toBeSerialized);
		} catch (JsonProcessingException e) {
			throw new MappingException(e);
		}
	}

	public static final ProjectDataView transformProjectToDataView(Project project) {
		ProjectDataView pdw = new ProjectDataView();

		// base data
		pdw.setName(project.getName());
		pdw.setDescription(project.getDescription());
		pdw.setId(project.getId());
		pdw.setLocked(project.isLocked());
		// dependend entities
		pdw.setUsecases(project.getUsecases().stream()
				.filter(useCase -> useCase.getUsecaseType() == UseCaseType.USECASE && !useCase.isEpic())
				.map(current -> current.getId())
				.collect(Collectors.toList()));
		pdw.setActors(getUsecasesByType(project, UseCaseType.PERSONA));
		pdw.setCriterias(project.getClosingCriterias().stream()
				.map(current -> current.getId())
				.collect(Collectors.toList()));
		pdw.setComponents(getUsecasesByType(project, UseCaseType.SERVICE));
		pdw.setRisks(project.getRisks().stream()
				.map(current -> current.getId())
				.collect(Collectors.toList()));

		// other data
		pdw.setCreated(dateToOffsetDateTime(project.getCreationDate()));
		pdw.setLastUpdated(dateToOffsetDateTime(project.getLastModifiedDate()));
		return pdw;
	}

	public static final List<String> getUsecasesByType(Project project, UseCaseType type) {
		return project.getUsecases().stream()
				.filter(useCase -> useCase.getUsecaseType() == type)
				.map(current -> current.getId())
				.collect(Collectors.toList());
	}

	public static final ActorDataView transformActorToDataView(UseCase actor) {
		// TODO evaluate additional validation
		ActorDataView adv = new ActorDataView();
		adv.setId(actor.getId());
		adv.setName(actor.getName());
		adv.setDescription(actor.getDescription());
		adv.setUsecases(actor.getRequiredUseCases().stream()
				.filter(useCase -> useCase.getType() == UseCaseDependencyType.USES)
				.map(current -> current.getId())
				.collect(Collectors.toList()));
		// other data
		adv.setCreated(dateToOffsetDateTime(actor.getCreationDate()));
		adv.setLastUpdated(dateToOffsetDateTime(actor.getLastModifiedDate()));
		return adv;
	}

	public static final ComponentDataView transformComponentToDataView(UseCase uc) {

		// TODO evaluate additional validation
		ComponentDataView adv = new ComponentDataView();
		adv.setId(uc.getId());
		adv.setName(uc.getName());
		adv.setDescription(uc.getDescription());
		/*
		TODO add usecase to service dependency in entity layer
		adv.setUsecases(uc.getRequiredUseCases().stream()
				.filter(useCase -> useCase.getType() == UseCaseDependencyType.USES)
				.map(current -> current.getId())
				.collect(Collectors.toList()));
		*/
		adv.setRisks(uc.getRisks().stream().map(current -> current.getId())
				.collect(Collectors.toList()));
		// other data
		adv.setCreated(dateToOffsetDateTime(uc.getCreationDate()));
		adv.setLastUpdated(dateToOffsetDateTime(uc.getLastModifiedDate()));
		return adv;
	}

	public static final UseCaseDataView transformUsecaseToDataView(UseCase uc) {
		// TODO evaluate additional validation
		UseCaseDataView adv = new UseCaseDataView();
		adv.setId(uc.getId());
		adv.setName(uc.getName());
		adv.setDescription(uc.getDescription());
		adv.setActors(uc.getRequiredUseCases().stream()
				.filter(useCase -> useCase.getType() == UseCaseDependencyType.USES)
				.map(current -> current.getId())
				.collect(Collectors.toList()));
		// TODO add handling for dependend usecases
		adv.setRisks(uc.getRisks().stream().map(current -> current.getId())
				.collect(Collectors.toList()));
		// other data
		adv.setCreated(dateToOffsetDateTime(uc.getCreationDate()));
		adv.setLastUpdated(dateToOffsetDateTime(uc.getLastModifiedDate()));
		return adv;
	}

	public static final RiskDataView transformRiskToDataView(Risk uc) {

		RiskDataView adv = new RiskDataView();
		adv.setId(uc.getId());
		adv.setName(uc.getName());
		adv.setDescription(uc.getDescription());
		adv.setUsecases(uc.getAffectedUsecases().stream()
				.filter(useCase -> useCase.getUsecaseType() == UseCaseType.USECASE)
				.map(current -> current.getId())
				.collect(Collectors.toList()));
		adv.setComponents(uc.getAffectedUsecases().stream()
				.filter(useCase -> useCase.getUsecaseType() == UseCaseType.SERVICE)
				.map(current -> current.getId())
				.collect(Collectors.toList()));
		// other data
		adv.setCreated(dateToOffsetDateTime(uc.getCreationDate()));
		adv.setLastUpdated(dateToOffsetDateTime(uc.getLastModifiedDate()));
		return adv;
	}

	public static final CriteriaBaseDataView transformCriteriaToDataView(ClosingCriteria uc) {

		CriteriaBaseDataView adv = new CriteriaBaseDataView();
		adv.setId(uc.getId());
		adv.setName(uc.getName());
		adv.setDescription(uc.getDescription());
		// other data
		adv.setCreated(dateToOffsetDateTime(uc.getCreationDate()));
		adv.setLastUpdated(dateToOffsetDateTime(uc.getLastModifiedDate()));
		return adv;
	}

}
