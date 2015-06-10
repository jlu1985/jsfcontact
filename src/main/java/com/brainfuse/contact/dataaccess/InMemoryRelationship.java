package com.brainfuse.contact.dataaccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.models.Relationship;

@ManagedBean(name = "relationshipDao", eager = true)
@ApplicationScoped
public class InMemoryRelationship implements BasicAccess<Relationship> {

	private static final Logger logger = LoggerFactory
			.getLogger(InMemoryRelationship.class);

	private Map<Long, Relationship> relationships;
	
	private long id = 0L;
	
	public InMemoryRelationship() {
		relationships = new HashMap<>();
	}

	@Override
	public boolean create(Relationship t) {
		try {
			t.setRelationshipId(++id);
			relationships.put(t.getOwnerId(), t);
			return true;
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public int update(Relationship t) {
		return (relationships.replace(t.getOwnerId(), t) != null) ? 1 : 0;
	}

	@Override
	public Relationship findById(long id) {
		logger.debug("Find by id {}", id);
		return relationships.get(id);
	}

	@Override
	public List<Relationship> find() {
		logger.debug("Find relationships");
		return new ArrayList<Relationship>(relationships.values());
	}

	@Override
	public int delete(long id) {
		logger.debug("Removing relationship id: {}", id);
		Relationship r = relationships.remove(id);
		if (r != null) {
			logger.debug("Found object and removed");
			return 1;
		}
		logger.debug("Object not found");
		return 0;
	}

	@Override
	public int delete(long... ids) {
		logger.debug("Removing ids: {}", ids);
		int removed = 0;
		for (long id : ids) {
			if (delete(id) == 1) {
				removed++;
			}
		}
		logger.debug("Number of objects removed {}", removed);
		return removed;
	}

	@PostConstruct
	public void init() {
		logger.debug("{} is created by the container", this);
	}
}
