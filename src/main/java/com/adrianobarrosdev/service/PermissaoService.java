package com.adrianobarrosdev.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.adrianobarrosdev.conexao.ConexaoFactory;
import com.adrianobarrosdev.dao.PermissoesDAO;
import com.adrianobarrosdev.dao.UsuarioDAO;
import com.adrianobarrosdev.model.Usuario;

public class PermissaoService {
	
	public void gerarPermissao(String cpf, int empresaId) {
		
		Connection connection = null;
		
		try {
			
			connection = ConexaoFactory.getConnection();
			
			UsuarioDAO usuarioDao = new UsuarioDAO(connection);
			Optional<Usuario> usuarioBuscado = usuarioDao.findByCpf(cpf);
			if(!usuarioBuscado.isPresent()) {
				return;
			}
			
			PermissoesDAO permissaoDao = new PermissoesDAO(connection);
			permissaoDao.salvar(usuarioBuscado.get().getId(), empresaId);
			connection.commit();
			
		} catch (Exception e) {
	        if (connection != null) {
	            try {
	                connection.rollback();
	            } catch (SQLException ex) {
	                throw new RuntimeException("Erro ao fazer rollback", ex);
	            }
	        }
	        throw new RuntimeException("Erro ao gerar permissão", e);

	    } finally {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
            		
	            }
	        }
	    }
		
	}
	
}
