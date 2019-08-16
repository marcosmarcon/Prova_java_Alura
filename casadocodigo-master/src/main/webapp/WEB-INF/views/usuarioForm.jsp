<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />
<tags:pageTemplate titulo=" ">
	<c:url value="/resources/css" var="cssPath" />
	<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
	<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
	<div class="container">
	
		<h1>Cadastro de Usuário</h1>
		
		<p>${falha}</p>
		<form:form action="${s:mvcUrl('UC#formUser').build() }" method="post"
			modelAttribute="usuario" enctype="multipart/form-data">
			<div class="form-group">
				<label>Nome</label>
				<form:input path="nome" cssClass="form-control" />
				<form:errors path="nome" />
			</div>
			<div class="form-group">
				<label>Email</label>
				<form:input path="email" valeu="email" cssClass="form-control" />
				<form:errors path="email" />
			</div>
			<div class="form-group">
				<label>Senha</label>
				<form:input type="password" path="senha" cssClass="form-control" />
				<form:errors path="senha" />
			</div>
	
	<!--  aaa-->
			<div class="form-group">
				<label>Senha repetida</label>
				<form:input type="password" path="senhaRepetida"
					cssClass="form-control" />
				<form:errors path="senhaRepetida" />
			</div>
  
			<button type="submit" class="btn btn-primary">Salvar</button>
		</form:form>
	</div>

</tags:pageTemplate>
