<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />


<tags:pageTemplate titulo="Seu carrinho de compra">
	<a href="${s:mvcUrl('UC#form').build() }">Novo usuário</a>
	<div class="container">
		<h1>Lista de Produtos</h1>
		<p>${senha}</p>
		<p>${falha}</p>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Role</th>
				<th></th>
			</tr>
			<c:forEach items="${usuarios }" var="usuarios">
				<tr>
					<td>${usuarios.nome }</td>
					<td>${usuarios.email }</td>
					<td>${usuarios.roles }</td>
					<td><form:form action="${s:mvcUrl('UC#checkBox').arg(0,usuarios.email).arg(1,usuarios.nome).build() }"> 
							<input type="image"
								src="${contextPath }resources/imagens/plus2.png" name="plus"
								alt="Plus" title="Plus" />
						</form:form></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</tags:pageTemplate>