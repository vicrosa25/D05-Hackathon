<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="eventos" id="row" requestURI="${ requestURI }" pagesize="5" class="displaytag">

	<spring:message code="evento.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" />
	
	<spring:message code="evento.fecha" var="fechaHeader" />
	<display:column property="fecha" title="${fechaHeader}" format="{0,date,dd/MM/yyyy}" />
	
	<spring:message code="evento.direccion" var="direccionHeader" />
	<display:column property="direccion" title="${direccionHeader}" />
	
	<display:column>
		<a href="evento/display.do?eventoId=${row.id}"><spring:message code="evento.display"/></a>
	</display:column> 

	<display:column>
		<a href="evento/manager/delete.do?eventoId=${row.id}"><spring:message code="evento.borrar"/></a>
	</display:column>
	 
</display:table>
 
<a href="evento/manager/create.do"><spring:message code="evento.crear" /></a>