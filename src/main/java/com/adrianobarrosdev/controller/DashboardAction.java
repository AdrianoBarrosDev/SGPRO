package com.adrianobarrosdev.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.adrianobarrosdev.model.Usuario;
import com.opensymphony.xwork2.ActionSupport;

public class DashboardAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;
	
	@Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

	public String execute() {
        Usuario usuario = (Usuario) session.get("usuarioLogado");
        if (usuario == null) {
            return LOGIN;
        }

        if (usuario.getEmpresa() != null) {
            return "empresa";
        } else {
            return "pessoa";
        }
    }
	
}
