package br.com.cliente.util;

import java.util.List;

public interface Dao {
	public void insert(Object o);
	public void update(Object o);
	public void delete(Object o);
	public Object select(int i);
	public List select();
}
