package com.adrianobarrosdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adrianobarrosdev.model.ExamesPendentes;

public class ExamesDAO {

	private Connection connection;
	
	public ExamesDAO(Connection connection) {
		this.connection = connection;
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
