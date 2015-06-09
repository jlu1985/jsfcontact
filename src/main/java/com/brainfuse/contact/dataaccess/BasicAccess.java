package com.brainfuse.contact.dataaccess;

import java.util.List;

public interface BasicAccess<T> {

	public boolean create(T t);

	public int update(T t);
	
	public T findById(long id);
	
	public List<T> find();

	public int delete(long id);

	public int delete(long... ids);
}
