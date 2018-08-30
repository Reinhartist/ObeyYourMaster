package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class CD {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
    @ManyToMany(cascade = { 
            CascadeType.ALL
        }, fetch=FetchType.EAGER)
	private List<Artist> artists;
	
	private CD() {}
	
	public CD(String title, List<Artist> artists) {
		this.title = title;
		this.artists = artists;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	
	public List<Artist> getArtists() {
		return artists;
	}
}
