<%-- Manu --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="sorteo" id="row" >

	<spring:message code="sorteo.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="false" />
	
	<spring:message code="sorteo.descripcion" var="descripcionHeader" />
	<display:column property="descripcion" title="${descripcionHeader}" sortable="false" />
	
	<spring:message code="sorteo.fechaInicio" var="fechaInicioHeader" />
	<display:column property="fechaInicio" title="${fechaInicioHeader}" sortable="false" />
	
		<spring:message code="sorteo.puntosNecesarios" var="puntosNecesariosHeader" />
	<display:column property="puntosNecesarios" title="${puntosNecesariosHeader}" sortable="false" />
	
	
		<spring:message code="sorteo.ganador" var="ganadorHeader" />
	<display:column property="ganador" title="${ganadorHeader}" sortable="false" />
	
<spring:message code="sorteo.premio" var="premioHeader" />	
		<display:column title="${premioHeader}"><a href="premio/admin/displayAdmin.do?premioId=${premioId}">
 	<spring:message code="premio.display"/></a></display:column> 
 	
</display:table>

<display:table name="usuarios" id="row" >

	<spring:message code="usuario.name" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" sortable="false" />
	
	<spring:message code="usuario.surname" var="apellidosHeader" />
	<display:column property="apellidos" title="${precioHeader}" sortable="false" />
	
	<spring:message code="usuario.email" var="emailHeader" />
	<display:column property="email" title="${descripcionHeader}" sortable="false" />
	
</display:table>
	


	<input type="button" name="goBack" value="<spring:message code="sorteo.goBack"/>" 
	onclick="javascript: window.location.replace('sorteo/admin/listAdmin.do')"/>