package fr.ramiere.hibernate.search;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

@Entity
public class Author {
	@Id
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Field(store = Store.YES)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
