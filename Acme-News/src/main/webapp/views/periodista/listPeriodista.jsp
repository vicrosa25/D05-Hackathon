 <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="5" class="displaytag" name="periodistas" requestURI="periodista/listPeriodista.do" id="row">	
	
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
	<display:column property="cartera" title="${carteraHeader}" />
	
	<!-- Agencia -->
	<spring:message code="periodista.agencia" var="agenciaHeader" />
	<display:column property="agencia.titulo" title="${agenciaHeader}" />

</display:table>
