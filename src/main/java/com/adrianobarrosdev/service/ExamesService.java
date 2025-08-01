package com.adrianobarrosdev.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.adrianobarrosdev.conexao.ConexaoFactory;
import com.adrianobarrosdev.dao.ExamesDAO;
import com.adrianobarrosdev.model.Exames;
import com.adrianobarrosdev.model.ExamesPendentes;
import com.adrianobarrosdev.model.Usuario;

public class ExamesService {

	private Map<String, Object> session;
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void enviarExame(int exameId, String url) {
		
		Connection connection = null;
		
		try {
			
			connection = ConexaoFactory.getConnection();
			
			ExamesDAO examesDao = new ExamesDAO(connection);
			examesDao.enviarExame(exameId, url);
			connection.commit();
			
			
		} catch (Exception e) {
	        if (connection != null) {
	            try {
	                connection.rollback();
	            } catch (SQLException ex) {
	                throw new RuntimeException("Erro ao fazer rollback", ex);
	            }
	        }
	        throw new RuntimeException("Erro ao enviar exame", e);

	    } finally {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
            		
	            }
	        }
	    }
		
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
	
	public void atualizarExamesColaboradoresEmpresa() {
		
		Connection connection = null;
		
		try {
			
			connection = ConexaoFactory.getConnection();
			
			Usuario usuario = (Usuario) session.get("usuarioLogado");
			
			ExamesDAO examesDao = new ExamesDAO(connection);
			
			Date dataAtual = new Date();
			
			usuario.getEmpresa().getListaColaboradores().stream().forEach(colaborador -> {
				
				List<Exames> listaExames = examesDao.examesColaborador(colaborador.getId());
				colaborador.setListaExames(listaExames.stream()
						.filter(exame -> !"PENDENTE".equalsIgnoreCase(exame.getStatus()))
						.collect(Collectors.toList())
					);
				
				colaborador.setListaExamesPendentes(listaExames.stream()
						.filter(exame -> "PENDENTE".equalsIgnoreCase(exame.getStatus()))
						.collect(Collectors.toList())
					);
				
				if(!colaborador.getListaExamesPendentes().isEmpty()) {
					
					if(colaborador.getListaExamesPendentes().get(0).getDataRealizacao().before(dataAtual)) {
						colaborador.setIndicadorRisco("Alto 🔴");
					} else {
						colaborador.setIndicadorRisco("Moderado 🟠");
					}
					
				} else {
					colaborador.setIndicadorRisco("Leve 🟢");
				}
				
			});

			
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
