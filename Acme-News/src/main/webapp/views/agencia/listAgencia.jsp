<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h2>All not empty agencies</h2>
<display:table pagesize="5" class="displaytag" name="agencias" requestURI="agencia/listAgencia.do" id="row">	
	
	<!-- Title -->
	<spring:message code="agencia.titulo" var="titleHeader" />
	<display:column property="titulo" title="${titleHeader}" />
	
	<!-- Address -->
	<spring:message code="agencia.direccion" var="addressHeader" />
	<display:column property="direccion" title="${addressHeader}" />
	
	<!-- Capacity -->
	<spring:message code="agencia.capacidadDisponible" var="capacityHeader" />
	<display:column property="capacidadDisponible" title="${capacityHeader}" />
	
	<!-- Tax -->
	<spring:message code="agencia.tasa" var="taxHeader" />
	<display:column property="tasa" title="${taxHeader}" />
	
	
	<jstl:if test="${row.manager == logged}">
		<!-- Update -->
		<spring:message code="agencia.update" var="updateHeader" />
		<display:column>
			<a href="agencia/update.do?agenciaId=${ row.id }"> <spring:message code="agencia.update" /> </a>
		</display:column>
	
		<!-- Delete -->
		<spring:message code="agencia.delete" var="deleteHeader" />
		<display:column>
			<a href="agencia/delete.do?agenciaId=${ row.id }"> <spring:message code="agencia.delete" /> </a>
		</display:column>
	</jstl:if>
	
</display:table>
