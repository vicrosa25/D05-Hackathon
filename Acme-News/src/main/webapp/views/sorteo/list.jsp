<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="sorteos" id="row" requestURI="sorteo/admin/list.do" pagesize="5" class="displaytag">

	<spring:message code="sorteo.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="false" />
	
	<spring:message code="sorteo.fechaInicio" var="fechaInicioHeader" /> 
	<display:column property="fechaInicio" title="${fechaInicioHeader}" format="{0,date,dd/MM/yyyy}" />
	
	<spring:message code="sorteo.fechaVencimiento" var="fechaVencimientoHeader" />
	<display:column property="fechaVencimiento" title="${fechaVencimientoHeader}" format="{0,date,dd/MM/yyyy}" />
	
	<display:column><a href="sorteo/admin/display.do?sorteoId=${row.id}">
 	<spring:message code="sorteo.display"/></a></display:column> 
	
	<display:column><a href="sorteo/admin/edit.do?sorteoId=${row.id}">
 	<spring:message code="sorteo.edit"/></a></display:column> 
 	
</display:table>
<br>
<a href="sorteo/admin/create.do"><spring:message code="sorteo.create" /></a>
<br>
<a href="sorteo/admin/elegirGanadores.do"><spring:message code="sorteo.elegirGanadores" /></a>

