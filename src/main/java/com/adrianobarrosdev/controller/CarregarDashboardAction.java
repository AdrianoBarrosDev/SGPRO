package com.adrianobarrosdev.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.adrianobarrosdev.service.ExamesService;
import com.opensymphony.xwork2.ActionSupport;

public class CarregarDashboardAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() {
		
    	ExamesService examesService = new ExamesService();
    	examesService.setSession(session);
    	examesService.atualizarListaExamesPendentesEmpresa();
		
		return "success";
		
	}
	
}
