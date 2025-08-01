package com.adrianobarrosdev.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.adrianobarrosdev.conexao.ConexaoFactory;
import com.adrianobarrosdev.dao.EmpresaDAO;
import com.adrianobarrosdev.model.Usuario;

public class ColaboradorService {
	
	private Map<String, Object> session;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void atualizarListaColaboradores() {
		
		Connection connection = null;
		
		try {
			
			connection = ConexaoFactory.getConnection();
			
			Usuario usuario = (Usuario) session.get("usuarioLogado");
			
			EmpresaDAO empresaDao = new EmpresaDAO(connection);
			usuario.getEmpresa().setListaColaboradores(empresaDao.atualizarListaColaboradoresEmpresa(usuario.getEmpresa().getId()));
			
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
