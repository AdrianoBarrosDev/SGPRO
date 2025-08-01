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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/dashboard.css" />
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
			<a href="colaboradores" class="${currentPage == 'colaboradores.jsp' ? 'ativado' : 'desativado'}">
				<img class="me-3" src="static/images/ColaboradoresIcon.png" />
				Colaboradores
			</a>
			<a href="setores" class="${currentPage == 'setores.jsp' ? 'ativado' : 'desativado'}">
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
			
			<div class="col-12 col-lg-6 d-flex justify-content-between align-items-start flex-wrap gap-5">
				
				<div>
					<h2 class="mb-4">Exames Pendentes por Setor</h2>
					<div class="d-flex justify-content-start align-items-start flex-wrap examesPendentes">
					
						<c:forEach var="setor" items="${sessionScope.usuarioLogado.empresa.listaSetores}">
							<div class="setor customCard">
								<img src="static/images/SetorIcon.png" />
								<div class="boxSetor">
									<p class="setorNome">${setor.nome}</p>
									<p class="quantidadeExames">${setor.examesPendentes}</p>								
								</div>
							</div>
		     			</c:forEach>
		     			
					</div>
				</div>
				
				<div class="d-flex w-100 justify-content-between align-items-center pe-5 me-5">
					<button class="detailsButton">Ver Detalhes</button>
					<button class="transparentButton">Gerar PDF</button>
				</div>
				
			</div>
			
			<div class="col-12 col-lg-6 d-flex justify-content-start align-items-start flex-column customCard px-5 py-4">
				
				<h2 class="mb-4 mt-3">Índice de Uso de EPI por Setor</h2>
				<div class="d-flex w-100 justify-content-start align-items-start flex-column">
					<% for (int i = 0; i < 6; i++) { %>
						<div class="d-flex justify-content-start align-items-center w-100">
							<div class="line me-4"></div>
							<p class="porcentagem">50%</p>
						</div>
						<p class="nomeSetorIndice">Financeiro</p>
					<% } %>
				</div>
				
			</div>
			
		</div>
	</div>

</body>
</html>