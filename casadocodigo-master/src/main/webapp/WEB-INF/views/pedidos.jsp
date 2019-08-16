<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />

<tags:pageTemplate titulo="Lista depedidos">
	<div class="container">
		<h1>Lista de Pedidos</h1>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Valor</th>
				<th>Data Pedido</th>
				<th>Titulo</th>
			</tr>
			<c:forEach items="${response }" var="response">
				<tr>
					<td>${response.id}</td>
					<td>${response.valor}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${response.data.time}" /></td>
					<td>
					<c:forEach items="${response.produtos }" var="p">
						${p.titulo}
					</c:forEach>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>

</tags:pageTemplate>