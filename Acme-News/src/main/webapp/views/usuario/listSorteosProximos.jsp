<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="necesarios" var="necesarios" />

<link rel="stylesheet" href="styles/display.css" type="text/css">

<display:table name="listSorteosProximos" id="row" requestURI="/usuario/listSorteosProximos.do" pagesize="5" class="displaytag">

	 <spring:message code="sorteo.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="true" />
	
	<spring:message code="sorteo.descripcion" var="descripcionHeader" />
	<display:column property="descripcion" title="${descripcionHeader}" sortable="true" />
	
	 <spring:message code="sorteo.fechaInicio" var="fechaInicioHeader" />
	<display:column property="fechaInicio" title="${fechaInicioHeader}" sortable="true" />
	
	 <spring:message code="sorteo.fechaVencimiento" var="fechaVencimientoHeader" />
	<display:column property="fechaVencimiento" title="${fechaVencimientoHeader}" sortable="true" />
	
	 <spring:message code="sorteo.puntosNecesarios" var="puntosNecesariosHeader" />
	<display:column property="puntosNecesarios" title="${puntosNecesariosHeader}" sortable="true" />
	
	 <spring:message code="sorteo.usuarios" var="usuariosHeader" />
	<display:column value="${row.getUsuarios().size()}" title="${usuariosHeader}" sortable="true" />
	
		<display:column>
<form:form action="usuario/apuntarseSorteo.do?sorteoId=${row.id}">
<input type="submit" name="Apuntarse" value="<spring:message code="usuario.apuntarseSorteo" />"
			onclick="return confirm('<spring:message code="usuario.confirmar.apuntarseSorteo" />')" />&nbsp;
</form:form>
</display:column>

	
</display:table>


<jstl:if test="${puntos!=null}">
	<caption><jstl:out value="${necesarios}"></jstl:out></caption>
	<jstl:out value="${puntos}"></jstl:out>
</jstl:if>
	

	

