package com.adrianobarrosdev.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.adrianobarrosdev.conexao.ConexaoFactory;
import com.adrianobarrosdev.dao.ExamesDAO;
import com.adrianobarrosdev.model.ExamesPendentes;
import com.adrianobarrosdev.model.Usuario;

public class ExamesService {

	private Map<String, Object> session;
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void atualizarListaExamesPendentesEmpresa() {
		
		Connection connection = null;
		
		List<ExamesPendentes> listaExamesPendentes;
		
		try {
			
			connection = ConexaoFactory.getConnection();
			
			Usuario usuario = (Usuario) session.get("usuarioLogado");
			
			ExamesDAO examesDao = new ExamesDAO(connection);
			listaExamesPendentes = examesDao.examesPendentesMesEmpresa(usuario.getEmpresa().getId());
			session.put("examesPendentes", listaExamesPendentes);
			
		} catch (Exception e) {
	        throw new RuntimeException("Erro ao pegar lista de exames pendentes da empresa", e);

	    } finally {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
	        }
	    }
		
	}
	
}
