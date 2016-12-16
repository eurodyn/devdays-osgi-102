package com.devdays102.rest.resources;

import com.devdays102.api.GreeterService;
import com.devdays102.api.dto.NameDTO;
import com.devdays102.api.dto.ReplyDTO;
import org.ops4j.pax.cdi.api.OsgiService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/greeter")
@Singleton
public class GreeterResource {

	@Inject
	@OsgiService
	private GreeterService greeterService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReplyDTO greet(NameDTO nameDTO) {
		return new ReplyDTO(greeterService.greet(nameDTO.getName()));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameDTO> getGreeters() {
		return greeterService.getGreeters();
	}

}
