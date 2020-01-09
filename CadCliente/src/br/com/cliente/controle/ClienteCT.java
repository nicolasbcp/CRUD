package br.com.cliente.controle;

import java.util.List;

import br.com.cliente.bean.Cliente;
import br.com.cliente.dao.ClienteDAO;

public class ClienteCT {
	public void insert(Cliente c) {
		ClienteDAO dao = new ClienteDAO();
		dao.insert(c);
	}

	public void update(Cliente c) {
		ClienteDAO dao = new ClienteDAO();
		dao.update(c);
	}

	public void delete(Cliente c) {
		ClienteDAO dao = new ClienteDAO();
		dao.delete(c);
	}

	public Cliente select(int i) {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = (Cliente) dao.select();
		return c;
	}
	
	public List select() {
		ClienteDAO dao = new ClienteDAO();
		List c = dao.select();
		return c;
	}
}