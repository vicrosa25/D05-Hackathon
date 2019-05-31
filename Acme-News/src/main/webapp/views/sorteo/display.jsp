<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	<display:column property="fechaInicio" title="${fechaInicioHeader}" format="{0,date,dd/MM/yyyy}" />
	
	<spring:message code="sorteo.fechaVencimiento" var="fechaVencimientoHeader" />
	<display:column property="fechaVencimiento" title="${fechaVencimientoHeader}" format="{0,date,dd/MM/yyyy}" />
	
	<spring:message code="sorteo.puntosNecesarios" var="puntosNecesariosHeader" />
	<display:column property="puntosNecesarios" title="${puntosNecesariosHeader}" sortable="false" />
	
	<spring:message code="sorteo.ganador" var="ganadorHeader" />
	<display:column property="ganador" title="${ganadorHeader}" sortable="false" />
	
	<spring:message code="sorteo.premio" var="premioHeader" />	
		<display:column title="${premioHeader}"><a href="premio/admin/display.do?premioId=${premioId}">
 	<spring:message code="premio.display"/></a></display:column> 
 	
</display:table>

<br>
<display:table name="usuarios" id="row" >
	
	<b><display:caption><spring:message code="sorteo.participantes"/></display:caption></b>

	<spring:message code="usuario.name" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" sortable="false" />
	
	<spring:message code="usuario.surname" var="apellidosHeader" />
	<display:column property="apellidos" title="${precioHeader}" sortable="false" />
	
	<spring:message code="usuario.email" var="emailHeader" />
	<display:column property="email" title="${descripcionHeader}" sortable="false" />
	
</display:table>
<br>
	
	<br>
	<jstl:if test="${ sorteo.ganador == null || sorteo.ganador == '' && not empty sorteo.usuarios }">
		<a href="sorteo/admin/winner.do?sorteoId=${sorteo.id}"><spring:message code="sorteo.elegirGanadores" /></a>
	</jstl:if>
	<br>
	
	<br>
	<input type="button" name="goBack" value="<spring:message code="sorteo.goBack"/>" 
	onclick="javascript: window.location.replace('sorteo/admin/list.do')"/>
	
	
	
	
	