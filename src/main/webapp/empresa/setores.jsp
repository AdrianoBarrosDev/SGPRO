<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SGPRO</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/global.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/setores.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/navCustomizada.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

	<nav class="sidebar">
		
		<img src="static/images/SgproLogoSmall.png" />
	
		<div>
			
			<c:set var="requestURI" value="${pageContext.request.requestURI}" />
			<c:set var="uriParts" value="${fn:split(requestURI, '/')}" />
			<c:set var="currentPage" value="${uriParts[fn:length(uriParts) - 1]}" />
			
			<a href="carregarDashboard" class="${currentPage == 'dashboard.jsp' ? 'ativado' : 'desativado'}">
				<img class="me-3" src="static/images/DashboardIcon.png" />
				Visão Geral
			</a>
			<a href="carregarColaboradores" class="${currentPage == 'colaboradores.jsp' ? 'ativado' : 'desativado'}">
				<img class="me-3" src="static/images/ColaboradoresIcon.png" />
				Colaboradores
			</a>
			<a href="carregarSetores" class="${currentPage == 'setores.jsp' ? 'ativado' : 'desativado'}">
				<img class="me-3" src="static/images/SetoresIcon.png" />
				Setores
			</a>
		</div>
		
		<form action="logoutUsuario" method="post">
			<input type="submit" value="Sair" class="sairButton"/>
		</form>
		
	</nav>
	
	<div class="content">
		<h1>${sessionScope.usuarioLogado.empresa.nome}</h1>
		<div class="row w-100 d-flex justify-content-between align-items-stretch mb-5">
			
			<div class="col-12 d-flex justify-content-start align-items-start flex-wrap" style="gap: 50px">
				<c:forEach var="setor" items="${sessionScope.usuarioLogado.empresa.listaSetores}">
					<div class="setor customCard justify-content-between align-items-center flex-row">
						<div class="boxSetor">
						
							<p class="setorNome" style="margin-bottom: 15px">${setor.nome}</p>
							
							<p class="dadosTitulo">Colaboradores</p>
							<p class="dadosInfo">${setor.quantidadeColaboradores}</p>
							
							<p class="dadosTitulo">Riscos Associados</p>
							<p class="dadosInfo">0 graves, 0 leves</p>
							
							<p class="dadosTitulo">Exames Pendentes</p>
							<p class="dadosInfo">${setor.examesPendentes}</p>
													
						</div>
						
						<div class="bg-dark" style="width:1px; height:100%; margin-left: 50px"></div>
						
						<div class="acoes flex-column gap-3">
							<button class="acao" 
								type="button" 
								data-bs-toggle="modal" 
								data-bs-target="#enviarExame" 
								data-nome="${colaborador.pessoa.nome}"
								data-exameId="${colaborador.listaExamesPendentes[0].id}"
							>
								<img src="static/images/Criar.png" />
							</button>
							<button class="acao"><img src="static/images/Deletar.png" /></button>
							<button class="acao"><img src="static/images/Visualizar.png" /></button>
						</div>
					</div>
	   			</c:forEach>
			</div>
			
		</div>
	</div>

</body>
</html>