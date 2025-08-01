package com.adrianobarrosdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adrianobarrosdev.model.Exames;
import com.adrianobarrosdev.model.ExamesPendentes;

public class ExamesDAO {

	private Connection connection;
	
	public ExamesDAO(Connection connection) {
		this.connection = connection;
	}
	
	
	public void enviarExame(int exameId, String url) {
		
		String sql = "UPDATE exames SET status = 'Enviado', caminhoArquivo = ? WHERE id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setString(1, url);
			ps.setInt(2, exameId);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public List<Exames> examesColaborador(int colaboradorId) {
		
		List<Exames> listaExames = new ArrayList<>();
		
		String sql = "SELECT e.id AS exame_id, e.tipo, e.dataPrevista, e.status AS exame_status, e.caminhoArquivo FROM exames AS e "
				+ "JOIN colaborador AS c ON e.colaborador_id = c.id "
				+ "WHERE e.colaborador_id = ? "
				+ "ORDER BY e.dataPrevista";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, colaboradorId);
			
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					
					Exames exame = new Exames();
					exame.setId(rs.getInt("exame_id"));
					exame.setTipo(rs.getString("tipo"));
					exame.setDataRealizacao(new java.util.Date(rs.getDate("dataPrevista").getTime()));
					exame.setStatus(rs.getString("exame_status"));
					exame.setCaminhoArquivo(rs.getString("caminhoArquivo"));
					
					listaExames.add(exame);
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaExames;
		
	}
	
	public List<ExamesPendentes> examesPendentesMesEmpresa(int empresaId) {
		
		List<ExamesPendentes> listaExames = new ArrayList<>();
		
		String sql = "SELECT s.nome AS nomeSetor, p.nome AS nomeColaborador, e.tipo AS tipoExame, e.dataPrevista FROM exames AS e "
				+ "JOIN colaborador AS c ON c.id = e.colaborador_id "
				+ "JOIN pessoa AS p ON p.id = c.pessoa_id "
				+ "JOIN setor AS s ON s.id = c.setor_id "
				+ "WHERE c.empresa_id = ? AND e.dataPrevista BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL '30 days'";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, empresaId);
			
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					
					ExamesPendentes exame = new ExamesPendentes();
					exame.setNomeSetor(rs.getString("nomeSetor"));
					exame.setNomeColaborador(rs.getString("nomeColaborador"));
					exame.setTipoExame(rs.getString("tipoExame"));
					exame.setDataPrevista(new java.util.Date(rs.getDate("dataPrevista").getTime()));
					
					listaExames.add(exame);
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaExames;
		
	}
	
}
