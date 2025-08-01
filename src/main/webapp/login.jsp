<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    // Verifica se o usuário está na sessão
    if (session.getAttribute("usuarioLogado") != null) {
        response.sendRedirect("dashboard");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SGPRO</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/global.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/entrada.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
	<h2 class="mb-5">Entrar</h2>
	
	<s:form action="validarLogin" method="POST">
	
		<label for="cpfLogin">CPF</label>
		<s:textfield name="cpfLogin" cssClass="inputCentralizado" theme="simple"/>
		<s:fielderror fieldName="cpfLogin"/>
		
		<label for="senhaLogin">Senha</label>
		<s:password name="senhaLogin" cssClass="inputCentralizado" theme="simple"/>
		<s:fielderror fieldName="senhaLogin"/>
		
		<label for="empresaId">ID Empresa</label>
		<s:textfield name="empresaId" cssClass="inputCentralizado" theme="simple"/>
		<s:fielderror fieldName="empresaId"/>
		
		<s:if test="hasActionErrors()">
		    <div class="">
		        <s:actionerror />
		    </div>
		</s:if>
		
		<div class="d-flex justify-content-center align-items-center mt-5">
			<s:submit value="Entrar" cssClass="submitButton" theme="simple"/>
		</div>
		
	</s:form>
	
</body>
</html>