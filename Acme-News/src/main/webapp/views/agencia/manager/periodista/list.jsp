<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<display:table name="periodistas" id="row" requestURI="${ requestURI }" pagesize="5" class="displaytag">	
	
	<!-- Foto -->
	<display:column title="${fotoHeader}">		
		<img src="${row.foto}" height="100" width="100" />		
	</display:column>
	
	<!-- Nombre -->
	<spring:message code="periodista.nombre" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" />
	<!-- Apellidos -->
	<spring:message code="periodista.apellidos" var="apellidosHeader" />
	<display:column property="apellidos" title="${apellidosHeader}" />
	
	<!-- Cartera -->
	<spring:message code="periodista.cartera" var="carteraHeader" />
	<display:column property="cartera.paypalEmail" title="${carteraHeader}" />
	
	<!-- Eject -->
	<spring:message code="periodista.eject" var="ejectHeader" />
	<display:column title="${ejectHeader}">
		<a href="agencia/manager/periodista/eject.do?periodistaId=${ row.id }&agenciaId=${ agenciaId }"> <spring:message code="periodista.eject" /></a>
	</display:column>
	
</display:table>

<br>
<acme:cancel url="agencia/manager/list.do" code="agencia.goBack"/>
