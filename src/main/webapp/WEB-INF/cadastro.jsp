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
	<h2 class="mb-5">Cadastro de Usuário</h2>
	<s:form action="/criarUsuario" method="post">
	
		<label for="cpf">CPF</label>
		<s:textfield name="cpf" label="CPF" cssClass="inputCentralizado" theme="simple"/>
		
		<label for="cpf">Nome</label>
		<s:textfield name="nome" label="Nome Completo" cssClass="inputCentralizado" theme="simple"/>
		
		<label for="cpf">Email</label>
		<s:textfield name="email" label="Email" cssClass="inputCentralizado" theme="simple"/>
		
		<label for="cpf">Telefone</label>
		<s:textfield name="telefone" label="Telefone" cssClass="inputCentralizado" theme="simple"/>
		
		<label for="cpf">Data de Nascimento</label>
		<s:textfield name="dataNascimento" label="Data de Nascimento" cssClass="inputCentralizado" theme="simple"/>
		
		<label for="cpf">Senha</label>
		<s:textfield name="senha" label="Senha" cssClass="inputCentralizado" theme="simple"/>
		
		<label for="cpf">Confirmar Senha</label>
		<s:textfield name="confirmacaoSenha" label="Confirmar Senha" cssClass="inputCentralizado" theme="simple"/>
		
		<div class="d-flex justify-content-center align-items-center mt-5">
			<s:submit value="Cadastrar" cssClass="submitButton" theme="simple"/>
		</div>
	</s:form>
</body>
</html>