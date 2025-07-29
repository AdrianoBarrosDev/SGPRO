package com.adrianobarrosdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.adrianobarrosdev.model.Pessoa;

public class PessoaDAO {
	
	private Connection connection;
	
	public PessoaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public Optional<Pessoa> findById(int id) throws SQLException {
		
		String sql = "SELECT * FROM pessoa WHERE id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setInt(1, id);

			try(ResultSet rs = ps.executeQuery()) {
				
				if(rs.next()) {
					
					Pessoa pessoa = new Pessoa();
					pessoa.setId(rs.getInt("id"));
					pessoa.setNome(rs.getString("nome"));
					pessoa.setEmail(rs.getString("email"));
					pessoa.setCpf(rs.getString("cpf"));
					pessoa.setTelefone(rs.getString("telefone"));
					pessoa.setDataNascimento(new java.util.Date(rs.getDate("dataNascimento").getTime()));
					
					return Optional.of(pessoa);
					
				} else {
					return Optional.empty();
				}
				
			}
			
		}
		
	}
	
	public void salvar(Pessoa pessoa) {
		
		String sql = "INSERT INTO pessoa(nome, email, cpf, telefone, dataNascimento) VALUES(?, ?, ?, ?, ?)";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getEmail());
			ps.setString(3, pessoa.getCpf());
			ps.setString(4, pessoa.getTelefone());
			ps.setDate(5, new java.sql.Date(pessoa.getDataNascimento().getTime()));
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int salvarRetornandoId(Pessoa pessoa) throws SQLException {
		
		String sql = "INSERT INTO pessoa(nome, email, cpf, telefone, dataNascimento) VALUES(?, ?, ?, ?, ?)";
				
		try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getEmail());
			ps.setString(3, pessoa.getCpf());
			ps.setString(4, pessoa.getTelefone());
			ps.setDate(5, new java.sql.Date(pessoa.getDataNascimento().getTime()));
			
			ps.executeUpdate();
			
			try(ResultSet rs = ps.getGeneratedKeys()) {
				if(rs.next()) {
					return rs.getInt(1);
				} else {
					throw new SQLException("Falha ao obter ID gerado.");
				}
			}
			
		}
		
	}
	
}
