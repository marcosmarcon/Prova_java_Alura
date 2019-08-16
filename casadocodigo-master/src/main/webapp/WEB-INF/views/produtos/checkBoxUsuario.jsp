<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />


<tags:pageTemplate titulo="Seu carrinho de compra">
	<div class="container">
		<h1>Cadastro de permissões para ${usuarios.nome}</h1>
		<form:form action="${s:mvcUrl('UC#updateRole').build() }"
			modelAttribute="usuarios">
			<div class="form-grpup">
				<form:input path="email" type="hidden" name="${usuarios.email}" />
				<form:input path="senha" type="hidden" name="${usuarios.senha}" />
				<form:input path="senhaRepetida" type="hidden"
					name="${usuarios.senhaRepetida}" />
				<form:input path="nome" type="hidden" name="${usuarios.nome}" />

				<form:checkbox path="roles" value="ROLE_USER" />
				ROLE_USER
				<form:checkbox path="roles" value="ROLE_ADMIN" />
				ROLE_ADMIN
				<form:checkbox path="roles" value="ROLE_COMERCIAL" />
				ROLE_COMERCIAL
			</div>
			<div>
				<button type="submit" class="btn btn-primary">Atualizar</button>
			</div>
		</form:form>
	</div>
</tags:pageTemplate>