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

<img src="${evento.imagen}" style="width:304px;height:228px;">
<br/>

<display:table name="evento" id="row" requestURI="evento/display.do">
	<spring:message code="evento.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="false" />
	
	<spring:message code="evento.fecha" var="fechaHeader" />
	<display:column property="fecha" title="${fechaHeader}" sortable="false" />
	
	<spring:message code="evento.direccion" var="direccionHeader" />
	<display:column property="direccion" title="${direccionHeader}" sortable="false" />
	
	<spring:message code="evento.periodista" var="periodistaHeader" />
	<display:column property="periodista.nombre" title="${periodistaHeader}" sortable="false" />
	
	<spring:message code="evento.agencia" var="agenciaHeader" />
	<display:column property="periodista.agencia.titulo" title="${agenciaHeader}" sortable="false" />
</display:table>
<br/>

 <spring:message code="reporte.create" var="editHeader" />
 <security:authorize access="hasRole('USUARIO')">
<a href=comentario/create.do?informacionId=${row.id}><spring:message code="comentario.create" /></a>
</security:authorize> 

<display:table name="evento" id="row" requestURI="evento/display.do">
	<spring:message code="evento.descripcion" var="descriptionHeader" />
	<display:column property="descripcion" title="${descriptionHeader}" sortable="false" />	
</display:table>
<br/>

<display:table name="comentarios" id="row" requestURI="evento/display.do">
	<spring:message code="comentario.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="false" />
	
	<spring:message code="comentario.descripcion" var="descripcionHeader" />
	<display:column property="descripcion" title="${descripcionHeader}" sortable="false" />
	
	<spring:message code="comentario.fecha" var="fechaHeader" />
	<display:column property="fecha" title="${fechaHeader}" sortable="false" />
	
	<spring:message code="comentario.usuario" var="usuarioHeader" />
	<display:column property="usuario.nombre" title="${usuarioHeader}" sortable="false" />
</display:table>




<input type="button" name="goBack" value="<spring:message code="evento.return"/>" 
onclick="javascript: window.location.replace('evento/allEventos.do')"/>