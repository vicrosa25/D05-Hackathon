<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table pagesize="5" class="displaytag" name="agencias" requestURI="agencia/listNotFullAgencia.do" id="row">	
	
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
	
	<!-- Join -->
	<display:column>
			<a href="agencia/join.do?agenciaId=${ row.id }"> <spring:message code="agencia.join" /> </a>
	</display:column>

	
</display:table>

<display:table pagesize="5" class="displaytag" name="agencia" requestURI="agencia/listNotFullAgencia.do" id="row">	
	
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
	
	<!-- left -->	
	<display:column>
			<a href="agencia/left.do"> <spring:message code="master.page.left.agencia" /> </a>
	</display:column>

	<display:caption><spring:message code="periodista.tuAgencia"/></display:caption>
</display:table>
