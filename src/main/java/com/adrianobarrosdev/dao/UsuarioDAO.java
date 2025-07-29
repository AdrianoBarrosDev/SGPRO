package com.adrianobarrosdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.adrianobarrosdev.model.Empresa;
import com.adrianobarrosdev.model.Pessoa;
import com.adrianobarrosdev.model.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Usuario usuario) {
		
		String sql = "INSERT INTO usuario (senha, pessoa_id) VALUES(?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.NO_GENERATED_KEYS)) {
			
			ps.setString(1, usuario.getSenha());
			
			if(usuario.getPessoa() != null) {
				ps.setInt(2, usuario.getPessoa().getId());
			} else {
				ps.setNull(2, java.sql.Types.INTEGER);
			}
					
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Optional<Usuario> findByCpf(String cpf) {
		
		String sql = "SELECT u.id, u.senha, u.pessoa_id, u.empresa_id FROM usuario AS u JOIN pessoa AS p ON u.pessoa_id = p.id WHERE p.cpf = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setString(1, cpf);
			ps.execute();
			
			try(ResultSet rs = ps.getResultSet()) {
				if(rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setPessoa(new Pessoa(rs.getInt("pessoa_id")));
					usuario.setEmpresa(new Empresa(rs.getInt("empresa_id")));
					return Optional.of(usuario);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
		
	}
	
}
