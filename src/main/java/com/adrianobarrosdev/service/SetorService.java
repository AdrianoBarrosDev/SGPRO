package com.adrianobarrosdev.service;

import java.util.Map;

import com.adrianobarrosdev.model.Empresa;
import com.adrianobarrosdev.model.Usuario;

public class SetorService {
	
	private Map<String, Object> session;
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void atualizarQuantidadeColaboradores() {
		
		Empresa empresa = (Empresa) ((Usuario) session.get("usuarioLogado")).getEmpresa();
		
		empresa.getListaSetores().stream().forEach(s -> {
			
			s.setQuantidadeColaboradores((int) empresa.getListaColaboradores().stream()
					.filter(c -> c.getSetor().getId() == s.getId())
					.count()
					);
			
		});
		
	}
	
}
