package com.adrianobarrosdev.model;

public class Permissoes {
	
	private int id;
	private int usuarioId;
	private int empresaId;
	
	public Permissoes() {
		
	}
	
	public Permissoes(int id, int usuarioId, int empresaId) {
		this.id = id;
		this.usuarioId = usuarioId;
		this.empresaId = empresaId;
	}
	
	public Permissoes(int usuarioId, int empresaId) {
		this.usuarioId = usuarioId;
		this.empresaId = empresaId;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUsuarioId() {
		return usuarioId;
	}
	
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public int getEmpresaId() {
		return empresaId;
	}
	
	public void setEmpresaId(int empresaId) {
		this.empresaId = empresaId;
	}
	
}
