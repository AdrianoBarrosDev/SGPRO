package com.adrianobarrosdev.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.adrianobarrosdev.conexao.ConexaoFactory;
import com.adrianobarrosdev.dao.EmpresaDAO;
import com.adrianobarrosdev.dao.PermissoesDAO;
import com.adrianobarrosdev.dao.PessoaDAO;
import com.adrianobarrosdev.dao.UsuarioDAO;
import com.adrianobarrosdev.model.Empresa;
import com.adrianobarrosdev.model.Pessoa;
import com.adrianobarrosdev.model.Usuario;

public class UsuarioService {
	
	public void cadastrar(Usuario usuario) {
		
	    Connection connection = null;

	    try {
	        connection = ConexaoFactory.getConnection();
	        
	        if(usuario.getPessoa() != null) {
	        	PessoaDAO pessoaDao = new PessoaDAO(connection);
	        	
	        	Optional<Pessoa> pessoaBuscada = pessoaDao.findById(usuario.getPessoa().getId());
	        	
	        	if(!pessoaBuscada.isPresent()) {
	        		int idGerado = pessoaDao.salvarRetornandoId(usuario.getPessoa());
	        		usuario.getPessoa().setId(idGerado);;
	        	} else {
	        		usuario.setPessoa(pessoaBuscada.get());
	        	}
	        	
	        }

	        UsuarioDAO usuarioDao = new UsuarioDAO(connection);
	        usuarioDao.salvar(usuario);
	        connection.commit();

	    } catch (Exception e) {
	        if (connection != null) {
	            try {
	                connection.rollback();
	            } catch (SQLException ex) {
	                throw new RuntimeException("Erro ao fazer rollback", ex);
	            }
	        }
	        throw new RuntimeException("Erro ao cadastrar usuário", e);

	    } finally {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
            		
	            }
	        }
	    }
	}
	
	public Optional<Usuario> validarLogin(String cpf, String senha) {
		
		Connection connection = null;
		
		try {
			connection = ConexaoFactory.getConnection();
			
			UsuarioDAO usuarioDao = new UsuarioDAO(connection);
			
			Optional<Usuario> usuarioBuscado = usuarioDao.findByCpf(cpf);
			if(usuarioBuscado.isPresent()) {
				if(usuarioBuscado.get().getSenha().equals(senha)) {
					
					usuarioBuscado.get().setSenha("");
					return usuarioBuscado;
					
				}
			}
			
		} catch(Exception e) {
			throw new RuntimeException("Erro ao buscar usuário", e);
			
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return Optional.empty();
		
	}
	
	public Optional<Usuario> validarUsuarioEmpresa(String cpf, String senha, String empresaId) {
		
		Connection connection = null;
		
		try {
			connection = ConexaoFactory.getConnection();
			
			EmpresaDAO empresaDao = new EmpresaDAO(connection);
			Optional<Empresa> empresaBuscada = empresaDao.findById(empresaId);
			
			if(!empresaBuscada.isPresent()) {
				return Optional.empty();
			}
			
			UsuarioDAO usuarioDao = new UsuarioDAO(connection);
			Optional<Usuario> usuarioBuscado = usuarioDao.findByCpf(cpf);
			
			if(usuarioBuscado.isPresent()) {
				
				if(usuarioBuscado.get().getSenha().equals(senha)) {
					
					PermissoesDAO permissoesDao = new PermissoesDAO(connection);
					boolean validarPermissaoUsuarioEmpresa = permissoesDao.validarPermissaoUsuarioEmpresa(usuarioBuscado.get().getId(), empresaBuscada.get().getId());
					
					if(validarPermissaoUsuarioEmpresa) {
						usuarioBuscado.get().setEmpresa(empresaBuscada.get());
						usuarioBuscado.get().setSenha("");
						return usuarioBuscado;
					}
					
				}
			}
			
		} catch(Exception e) {
			throw new RuntimeException("Erro ao buscar usuário", e);
			
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return Optional.empty();
		
	}
	
}
