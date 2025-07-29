<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SGPRO</title>
	<link rel="stylesheet" href="assets/global.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
	<h2 class="mb-5">Entrar</h2>
	
	<s:form action="/validarLogin" method="POST">
	
		<label for="cpf">CPF</label>
		<s:textfield name="cpf" label="CPF" cssClass="inputCentralizado" theme="simple"/>
		<s:fielderror fieldName="cpf"/>
		
		<label for="senha">Senha</label>
		<s:textfield name="senha" label="Senha" cssClass="inputCentralizado" theme="simple"/>
		<s:fielderror fieldName="senha"/>
		
		<label for="empresaId">ID Empresa</label>
		<s:textfield name="empresaId" label="EmpresaId" cssClass="inputCentralizado" theme="simple"/>
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