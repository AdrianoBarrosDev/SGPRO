package com.adrianobarrosdev.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.adrianobarrosdev.service.ColaboradorService;
import com.adrianobarrosdev.service.ExamesService;
import com.opensymphony.xwork2.ActionSupport;

public class CarregarColaboradoresAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	
	private ExamesService examesService = new ExamesService();
	private ColaboradorService colaboradorService = new ColaboradorService();
	private Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public String execute() {
		
		colaboradorService.setSession(session);
		colaboradorService.atualizarListaColaboradores();
		
		examesService.setSession(session);
		examesService.atualizarExamesColaboradoresEmpresa();
		
		return "success";
	}
	
}
