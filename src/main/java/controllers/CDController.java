package controllers;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.qa.albums.Util;

import models.Artist;
import models.CD;
import services.CDServices;


@Path("CD")
public class CDController {
	@Inject
	private CDServices service;
	
	@Path("all")
	@GET
	public String findAllCD() {
		return Util.toJson(service.findAllCD());
	}
	
	@Path("one")
	@GET
	public String findCD(@QueryParam("id") Long id) {
		return Util.toJson(service.findCD(id));
	}
	
	@Path("add")
	@POST
	public String addCD(String body) {
		return service.addCD(Util.fromJson(body, CD.class));
	}

	@Path("delete")
	@PUT
	public String deleteCD(@QueryParam("id") Long id) {
		return service.deleteCD(id);
	}
	
	@Path("update")
	@POST
	public String updateCD(@QueryParam("id") Long id, String body) {
		CD toUpdate = Util.fromJson(body, CD.class);
		toUpdate.setId(id);
		return service.updateCD(toUpdate);
	}
}
