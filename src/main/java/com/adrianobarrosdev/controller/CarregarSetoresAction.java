package com.adrianobarrosdev.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.adrianobarrosdev.service.SetorService;
import com.opensymphony.xwork2.ActionSupport;

public class CarregarSetoresAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;

	private SetorService setorService = new SetorService();
	private Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public String execute() {
		
		setorService.setSession(session);
		setorService.atualizarQuantidadeColaboradores();
		
		return "success";
	}
	
}
