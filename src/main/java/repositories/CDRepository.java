package repositories;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import models.Artist;
import models.CD;

@Default
@Transactional(SUPPORTS)
public class CDRepository implements ICDRepository{
	@PersistenceContext(name = "primary")
	private EntityManager manager;

	public List<CD> findAllCD() {
		return manager.createQuery("select m from CD m", CD.class).getResultList();
	}

	public CD findCD(Long id) {
		return manager.find(CD.class, id);
	}

	@Transactional(REQUIRED)
	public String addCD(CD cd) {
		manager.persist(correctArtistID(cd));
		return "Added";
	}

	@Transactional(REQUIRED)
	public String deleteCD(Long id) {
		CD cd = findCD(id);
		if (cd != null) manager.remove(cd);
		return "Deleted";
	}

	@Transactional(REQUIRED)
	public String updateCD(CD cd) {
		if(findCD(cd.getId()) != null) manager.merge(cd);
		return "Updated";
	}
	
	public List<Artist> findArtist(String name) {
		Query query = manager.createQuery("from Artist where name= :name");
		query.setParameter("name", name);
		return (List<Artist>) query.getResultList();
	}
	
	public CD correctArtistID(CD cd) {
		List<Artist> artistsOnCD = cd.getArtists();
		List<Artist> correctArtistList = new ArrayList<>();
		for(Artist artist: artistsOnCD) {
			List<Artist> artistsOnDB = findArtist(artist.getName());
			if(artistsOnDB.size() != 0) correctArtistList.add(artistsOnDB.get(0));
			else correctArtistList.add(artist);
		}
		cd.setArtists(correctArtistList);
		return cd;
	}
}
