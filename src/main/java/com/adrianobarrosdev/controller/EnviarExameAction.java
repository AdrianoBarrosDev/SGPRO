package com.adrianobarrosdev.controller;

import java.io.File;

import com.adrianobarrosdev.service.ExamesService;

public class EnviarExameAction {
	
	private File arquivoExame;  
	private String arquivoExameFileName;
	private String arquivoExameContentType;
	private int exameId;
	
	public File getArquivoExame() {
		return arquivoExame;
	}
	
	public void setArquivoExame(File arquivoExame) {
		this.arquivoExame = arquivoExame;
	}

	public String getArquivoExameFileName() {
		return arquivoExameFileName;
	}

	public void setArquivoExameFileName(String arquivoExameFileName) {
		this.arquivoExameFileName = arquivoExameFileName;
	}

	public String getArquivoExameContentType() {
		return arquivoExameContentType;
	}

	public void setArquivoExameContentType(String arquivoExameContentType) {
		this.arquivoExameContentType = arquivoExameContentType;
	}
	
	public int getExameId() {
		return exameId;
	}

	public void setExameId(int exameId) {
		this.exameId = exameId;
	}

	public String execute() {
		
		if (arquivoExame != null) {

            String caminho = "C:/Users/Usuário/eclipse-workspace/sgpro/exames";
            
            File dirUploads = new File(caminho);
            if (!dirUploads.exists()) {
                dirUploads.mkdirs();
            }
            
            File destino = new File(dirUploads, arquivoExameFileName);

            try {
                org.apache.commons.io.FileUtils.copyFile(arquivoExame, destino);
                
                ExamesService examesService = new ExamesService();
        		examesService.enviarExame(exameId, destino.getAbsolutePath());
                
                System.out.println("Arquivo salvo em: " + destino.getAbsolutePath());
                
            } catch (Exception e) {
                e.printStackTrace();
                return "success";
            }
            
        }

        return "success";
		
	}

}
