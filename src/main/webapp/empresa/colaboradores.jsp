<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
		<div class="row w-100 d-flex justify-content-start align-items-start flex-column mb-5 gap-5">
			
			<div class="col-12 d-flex justify-content-start align-items-start flex-column">
				<h2 class="mb-4">Gestão de Colaboradores</h2>
				
				<div class="tabela-wrapper">
					<table class="examesProximosTable">
						<thead>
							<tr>
								<th>Status</th>
								<th>Setor</th>
								<th>Nome Completo</th>
								<th>Cargo/Função</th>
								<th>Tipo Exame</th>
								<th>Próximo Exame</th>
								<th>Indicador Risco</th>
								<th style="text-align: right">Ações</th>
							</tr>
						</thead>
					</table>
					<div class="tabela-scroll-body">
						<table class="examesProximosTable">
					    	<tbody>
					      		<c:forEach var="colaborador" items="${sessionScope.usuarioLogado.empresa.listaColaboradores}">
					       			<tr>
					         			<td>${colaborador.status}</td>
					         			<td>${colaborador.setor.nome}</td>
					         			<td>${colaborador.pessoa.nome}</td>
					         			<td>${colaborador.cargo}</td>
					         			<td>
											<c:if test="${not empty colaborador.listaExamesPendentes}">
											    ${colaborador.listaExamesPendentes[0].tipo}
											</c:if>
										</td>
							         	<td>
											<c:if test="${not empty colaborador.listaExamesPendentes}">
											    <fmt:formatDate value="${colaborador.listaExamesPendentes[0].dataRealizacao}" pattern="dd/MM/yyyy" />
											</c:if>
										</td>
										<td>${colaborador.indicadorRisco}</td>
										<td>
											<div class="acoes">
												
												<c:if test="${not empty colaborador.listaExamesPendentes}">
													<button class="acao" 
														type="button" 
														data-bs-toggle="modal" 
														data-bs-target="#enviarExame" 
														data-nome="${colaborador.pessoa.nome}"
														data-exameId="${colaborador.listaExamesPendentes[0].id}"
													>
														<img src="static/images/Criar.png" />
													</button>
												</c:if>
												
												<button class="acao"><img src="static/images/Visualizar.png" /></button>											
											</div>
										</td>
							         </tr>
				     			</c:forEach>
				      		</tbody>
					    </table>
		  			</div>
				</div>
				
			</div>
			
			<div class="col-12 d-flex justify-content-between align-items-center pe-5 me-5">
				<button class="detailsButton" style="width: 220px" 
					type="button" 
					data-bs-toggle="modal" 
					data-bs-target="#adicionarColaborador"
				>
					Adicionar Colaborador
				</button>
				<button class="transparentButton" 
					type="button" 
					data-bs-toggle="modal" 
					data-bs-target="#permissaoColaborador"
				>
					Gerar Permissão
				</button>
			</div>
			
			
			<!-- Modal Enviar Exame -->
			<div class="modal fade" id="enviarExame" tabindex="-1" aria-labelledby="modalEnviarExame" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			
					<!-- Cabeçalho do Modal -->
					<div class="modal-header">
					  <h5 class="modal-title" id="modalEnviarExame">Enviar Próximo Exame</h5>
					  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
					</div>
				
			      	<!-- Corpo do Modal -->
			      	<div class="modal-body">
				      	
				      	<div class="mb-3">
					      <label for="nomeColaboradorEnviarExame" class="form-label">Nome do Colaborador</label>
					      <input type="text" class="form-control" id="nomeColaboradorEnviarExame" name="nomeColaboradorEnviarExame" value="" readonly>
					    </div>
				      
					  	<form id="formEditarColaborador" action="enviarExame" method="post" enctype="multipart/form-data">
	
						    <div class="mb-3">
						      <label for="arquivoExame" class="form-label">Selecionar Arquivo</label>
						      <input class="form-control" type="file" id="arquivoExame" name="arquivoExame" accept=".pdf,.jpg,.png,.docx">
						    </div>
						    
						    <input type="hidden" id="exameId" name="exameId" value="">
						    
						    <div class="modal-footer">
						      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
						      <button type="submit" class="btn btn-primary">Enviar</button>
						    </div>
						    
					  	</form>
					  	
					</div>
			
			    </div>
			  </div>
			</div>
			
			
			<!-- Modal Adicionar Colaborador -->
			<div class="modal fade" id="adicionarColaborador" tabindex="-1" aria-labelledby="modalAdicionarColaborador" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			
					<!-- Cabeçalho do Modal -->
					<div class="modal-header">
					  <h5 class="modal-title" id="modalAdicionarColaborador">Adicionar Colaborador</h5>
					  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
					</div>
				
			      	<!-- Corpo do Modal -->
			      	<div class="modal-body">
				      
					  	<form id="formAdicionarColaborador" action="adicionarColaborador" method="post">
							
							<div class="mb-3">
						      <label for="nomeColaboradorEnviarExame" class="form-label">CPF do Colaborador</label>
						      <input type="text" class="form-control" id="nomeColaboradorEnviarExame" name="nomeColaboradorEnviarExame" value="">
						    </div>
						    
						    <input type="hidden" id="pessoaIdAdicionarColaborador" name="pessoaIdAdicionarColaborador" value="">
						    <input type="hidden" id="empresaIdAdicionarColaborador" name="empresaIdAdicionarColaborador" value="$">
						    
						    <div class="modal-footer">
						      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
						      <button type="submit" class="btn btn-primary">Enviar</button>
						    </div>
						    
					  	</form>
					  	
					</div>
			
			    </div>
			  </div>
			</div>
			
			
			<!-- Modal Permissão Colaborador -->
			<div class="modal fade" id="permissaoColaborador" tabindex="-1" aria-labelledby="modalPermissãoColaborador" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			
					<!-- Cabeçalho do Modal -->
					<div class="modal-header">
					  <h5 class="modal-title" id="modalPermissãoColaborador">Adicionar Colaborador</h5>
					  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
					</div>
				
			      	<!-- Corpo do Modal -->
			      	<div class="modal-body">
				      
					  	<form id="formPermissaoColaborador" action="permissaoColaborador" method="post" enctype="multipart/form-data">
							
							<div class="mb-3">
						      <label for="cpfColaboradorPermissao" class="form-label">CPF do Colaborador</label>
						      <input type="text" class="form-control" id="cpfColaboradorPermissao" name="cpfColaboradorPermissao" value="">
						    </div>
						    
						    <p>* Importante: Ao dar permissão para um colaborador, ele poderá entrar na conta da empresa e fazer alterações.</p>
						    
						    <input type="hidden" id="empresaIdPermissao" name="empresaIdPermissao" value="${sessionScope.usuarioLogado.empresa.id}">
						    
						    <div class="modal-footer">
						      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
						      <button type="submit" class="btn btn-primary">Enviar</button>
						    </div>
						    
					  	</form>
					  	
					</div>
			
			    </div>
			  </div>
			</div>
			
			
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
	  var enviarExameModal = document.getElementById('enviarExame');
	
	  enviarExameModal.addEventListener('show.bs.modal', function (event) {
	    
	    var button = event.relatedTarget;
	    
	    // Pega o nome do atributo data-nome
	    var nome = button.getAttribute('data-nome');
	    var exameId = button.getAttribute('data-exameId');
	
	    // Atualiza o input dentro do modal
	    enviarExameModal.querySelector('#nomeColaboradorEnviarExame').value = nome;
	    enviarExameModal.querySelector('#exameId').value = exameId;
	    
	  });
	</script>

</body>
</html>