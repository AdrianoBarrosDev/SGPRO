package com.adrianobarrosdev.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

public class LogoutUsuarioAction implements SessionAware {
	
	private Map<String, Object> session;
	
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute() {
    	session.clear();

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession httpSession = request.getSession(false);

        if (httpSession != null) {
            httpSession.invalidate();
        }

        return "success";
    }
}
