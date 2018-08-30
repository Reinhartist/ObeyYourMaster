package services;

import java.util.List;

import javax.inject.Inject;

import models.CD;
import repositories.ICDRepository;

public class CDServices {
	@Inject
	private ICDRepository rep;
	
	public List<CD> findAllCD() {
		return rep.findAllCD();
	}
	public CD findCD(Long id) {
		return rep.findCD(id);
	}
	public String addCD(CD cd) {
		return rep.addCD(cd);
	}
	public String deleteCD(Long id) {
		return rep.deleteCD(id);
	}
	public String updateCD(CD cd) {
		return rep.updateCD(cd);
	}
	
}
