package com.adrianobarrosdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.adrianobarrosdev.model.Empresa;

public class EmpresaDAO {
	
	private Connection connection;
	
	public EmpresaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Empresa empresa) {
		
		String sql = "INSERT INTO empresa VALUES(?, ?, ?, ?)";
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setString(1, empresa.getNome());
			ps.setString(2, empresa.getCnpj());
			ps.setString(3, empresa.getEmailContato());
			ps.setString(4, empresa.getTelefone());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Optional<Empresa> findById(String id) {
		
		String sql = "SELECT * FROM empresa WHERE id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setInt(1, Integer.parseInt(id));
			ps.execute();
			
			try(ResultSet rs = ps.getResultSet()) {
				if(rs.next()) {
					Empresa empresa = new Empresa();
					empresa.setId(Integer.parseInt(id));
					empresa.setNome(rs.getString("nome"));
					empresa.setCnpj(rs.getString("cnpj"));
					empresa.setEmailContato(rs.getString("emailContato"));
					empresa.setTelefone(rs.getString("telefone"));
					return Optional.of(empresa);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
		
	}
	
}
