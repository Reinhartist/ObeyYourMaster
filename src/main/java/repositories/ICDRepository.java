package repositories;

import java.util.List;

import models.CD;

public interface ICDRepository {
	public List<CD> findAllCD();
	public CD findCD(Long id);
	public String addCD(CD cd);
	public String deleteCD(Long id);
	public String updateCD(CD cd);
}
