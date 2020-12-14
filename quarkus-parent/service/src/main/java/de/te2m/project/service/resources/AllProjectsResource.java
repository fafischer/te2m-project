package de.te2m.project.service.resources;

import java.util.List;

import de.te2m.project.api.impl.ProjectsApi;
import de.te2m.project.api.impl.model.ProjectDataView;
import de.te2m.project.api.impl.model.ProjectEditCommand;
import de.te2m.project.service.resources.service.ProjectEntityService;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static de.te2m.project.service.resources.MappingUtils.transformProjectToDataView;

@Path("/projects")
public class AllProjectsResource extends ProjectsApi {

	@Inject
	private ProjectEntityService projectEntityService;

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProjects(@Valid ProjectEditCommand projectEditCommand)

	{
		return Response.ok().entity(transformProjectToDataView(projectEntityService.createProjectFromCommand(projectEditCommand))).build();
	}

	@Override
	public Response getAllProjects() {
		return Response.ok().entity(projectEntityService.findAllProjects()).build();
	}

}
