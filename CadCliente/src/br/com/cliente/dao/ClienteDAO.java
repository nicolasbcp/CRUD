package br.com.cliente.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cliente.bean.Cliente;
import br.com.cliente.util.Conector;
import br.com.cliente.util.Dao;

public class ClienteDAO implements Dao {
	public void insert(Object arg0) {
		Cliente c = (Cliente) arg0;
		String sql = "insert into cliente (nome,endereco,municipio,cep,cel,cpf,genero) values (?,?,?,?,?,?,?,?)";
		try {
		PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getEndereco());
		ps.setString(3, c.getMunicipio());
		ps.setString(4, c.getCep());
		ps.setString(6, c.getCel());
		ps.setString(7, c.getCpf());
		ps.setString(9, c.getGenero());

		ps.execute(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public void delete(Object arg0) {
		Cliente c = (Cliente) arg0;
		String sql = "delete from cliente where id=?";
		try {
			PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Object arg0) {
		Cliente c = (Cliente) arg0;
		String sql = "update cliente set nome=?,endereco=?,municipio=?,cep=?,cel=?,cpf=?,genero=? where id=?";
		try {
			PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getEndereco());
			ps.setString(3, c.getMunicipio());
			ps.setString(4, c.getCep());
			ps.setString(6, c.getCel());
			ps.setString(7, c.getCpf());
			ps.setString(9, c.getGenero());
			ps.setInt(10, c.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Object select(int arg0) {
		Cliente c = new Cliente();
		String sql = "select * from cliente where id=?";
		try {
			PreparedStatement ps = Conector.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setMunicipio(rs.getString("municipio"));
				c.setCep(rs.getString("cep"));
				c.setCel(rs.getString("cel"));
				c.setCpf(rs.getString("cpf"));
				c.setGenero(rs.getString("genero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public List select() {
		List list = new ArrayList();
		String sql = "select * from cliente";
		PreparedStatement ps;
		try {
			ps = Conector.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setMunicipio(rs.getString("municipio"));
				c.setCep(rs.getString("cep"));
				c.setCel(rs.getString("cel"));
				c.setCpf(rs.getString("cpf"));
				c.setGenero(rs.getString("genero"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}