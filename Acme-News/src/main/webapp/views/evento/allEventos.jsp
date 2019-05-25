<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="eventos" id="row" requestURI="evento/allEventos.do" pagesize="5" class="displaytag">

	<spring:message code="evento.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="false" />
	
	<spring:message code="evento.direccion" var="direccionHeader" />
	<display:column property="direccion" title="${direccionHeader}" sortable="false" />
	
	<spring:message code="evento.fecha" var="fechaHeader" />
	<display:column property="fecha" title="${fechaHeader}" sortable="false" />

	<spring:message code="evento.agencia" var="agenciaHeader" />
	<display:column property="agencia.titulo" title="${agenciaHeader}" sortable="false" />


<display:column><a href="evento/display.do?eventoId=${row.id}">
 	<spring:message code="evento.display"/></a></display:column> 
 	
 	
 		<security:authorize access="hasRole('USUARIO')">
 	<display:column>
		<form:form action="usuario/compartirInformacion.do?informacionId=${row.id}">
		<input type="submit" name="Compartir" value="<spring:message code="usuario.compartir" />"
			onclick="return confirm('<spring:message code="usuario.confirmar.compartir" />')" />&nbsp;
		</form:form>
	</display:column>
	</security:authorize> 
	
	
</display:table>

<a href="welcome/index.do"><input type="button" name="goBack" value="<spring:message code="evento.return"/>" /></a>	