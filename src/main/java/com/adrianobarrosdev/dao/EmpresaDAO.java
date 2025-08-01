package com.adrianobarrosdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.adrianobarrosdev.model.Colaborador;
import com.adrianobarrosdev.model.Empresa;
import com.adrianobarrosdev.model.Pessoa;
import com.adrianobarrosdev.model.Setor;

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
			
			int empresaId = Integer.parseInt(id);
			
			ps.setInt(1, empresaId);
			ps.execute();
			
			try(ResultSet rs = ps.getResultSet()) {
				if(rs.next()) {
					
					List<Colaborador> listaColaboradores = atualizarListaColaboradoresEmpresa(empresaId);
					List<Setor> listaSetores = atualizarListaSetoresEmpresa(empresaId);
					
					Empresa empresa = new Empresa();
					empresa.setId(Integer.parseInt(id));
					empresa.setNome(rs.getString("nome"));
					empresa.setCnpj(rs.getString("cnpj"));
					empresa.setEmailContato(rs.getString("emailContato"));
					empresa.setTelefone(rs.getString("telefone"));
					empresa.setListaColaboradores(listaColaboradores);
					empresa.setListaSetores(listaSetores);
					return Optional.of(empresa);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
		
	}
	
	public List<Colaborador> atualizarListaColaboradoresEmpresa(int empresaId) {
		
		String sql = "SELECT c.id AS colaborador_id, p.nome AS pessoa_nome, p.email AS pessoa_email, p.cpf AS pessoa_cpf, p.telefone AS pessoa_telefone, p.dataNascimento AS pessoa_dataNascimento, "
				+ "c.matricula AS colaborador_matricula, c.cargo AS colaborador_cargo, c.dataAdmissao AS colaborador_dataAdmissao, "
				+ "s.id AS setor_id, s.nome AS setor_nome FROM colaborador AS c "
				+ "JOIN pessoa AS p ON c.pessoa_id = p.id "
				+ "JOIN setor AS s ON c.setor_id = s.id "
				+ "WHERE c.empresa_id = ?";
		
		List<Colaborador> listaColaboradores = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setInt(1, empresaId);
			ps.executeQuery();
			
			try(ResultSet rs = ps.getResultSet()) {
				while(rs.next()) {
					
					Colaborador colaborador = new Colaborador();
					colaborador.setId(rs.getInt("colaborador_id"));
					
					colaborador.setPessoa(
							new Pessoa(rs.getString("pessoa_nome"), rs.getString("pessoa_email"), rs.getString("pessoa_cpf"), rs.getString("pessoa_telefone"), new java.util.Date(rs.getDate("pessoa_dataNascimento").getTime()))
					);
					
					colaborador.setMatricula(rs.getString("colaborador_matricula"));
					colaborador.setCargo(rs.getString("colaborador_cargo"));
					colaborador.setDataAdmissao(new java.util.Date(rs.getDate("colaborador_dataAdmissao").getTime()));
					
					colaborador.setSetor(
							new Setor(rs.getInt("setor_id"), rs.getString("setor_nome"), null, null)
					);
					
					listaColaboradores.add(colaborador);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaColaboradores;
		
	}
	
	
	public List<Setor> atualizarListaSetoresEmpresa(int empresaId) {
		
		String sql = "SELECT s.id AS setorId, s.nome AS setorNome, s.localizacao, s.descricao, COUNT(ex.id) AS examesPendentes FROM setor AS s "
				+ "JOIN empresa AS e ON e.id = s.empresa_id "
				+ "JOIN colaborador AS c ON c.setor_id = s.id "
				+ "JOIN exames AS ex ON ex.colaborador_id = c.id "
				+ "WHERE e.id = ? AND ex.dataPrevista < CURRENT_DATE "
				+ "GROUP BY s.id, s.nome, s.localizacao, s.descricao";
		
		List<Setor> listaSetores = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setInt(1, empresaId);
			ps.executeQuery();
			
			try(ResultSet rs = ps.getResultSet()) {
				while(rs.next()) {

					Setor setor = new Setor();
					setor.setId(rs.getInt("setorId"));
					setor.setNome(rs.getString("setorNome"));
					setor.setLocalizacao(rs.getString("localizacao"));
					setor.setDescricao(rs.getString("descricao"));
					setor.setExamesPendentes(rs.getInt("examesPendentes"));
					
					listaSetores.add(setor);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaSetores;
		
	}
	
}
