package com.adrianobarrosdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissoesDAO {

	private Connection connection;
	
	public PermissoesDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(int usuarioId, int empresaId) { 
		String sql = "INSERT INTO permissoes(usuario_id, empresa_id) VALUES (?, ?)";
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, usuarioId);
			ps.setInt(2, empresaId);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean validarPermissaoUsuarioEmpresa(int usuarioId, int empresaId) {
		String sql = "SELECT id FROM permissoes WHERE usuario_id = ? AND empresa_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, usuarioId);
			ps.setInt(2, empresaId);
			ps.execute();
			
			try(ResultSet rs = ps.getResultSet()) {
				if(rs.next()) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
