<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<jstl:if test="${not empty evento.imagen }">
<img src="${evento.imagen}" style="width:304px;height:228px;">
<br/>
</jstl:if>

<display:table name="evento" id="row" requestURI="evento/display.do">
	<spring:message code="evento.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="false" />
	
	<spring:message code="evento.fecha" var="fechaHeader" />
	<display:column property="fecha" title="${fechaHeader}" format="{0,date,dd/MM/yyyy}" />
	
	<spring:message code="evento.direccion" var="direccionHeader" />
	<display:column property="direccion" title="${direccionHeader}" sortable="false" />
	
	<spring:message code="evento.agencia" var="agenciaHeader" />
	<display:column property="agencia.titulo" title="${agenciaHeader}" sortable="false" />
</display:table>
<br/> 

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
	<display:column property="fecha" title="${fechaHeader}" format="{0,date,dd/MM/yyyy}" />
	
	<spring:message code="comentario.usuario" var="usuarioHeader" />
	<display:column property="usuario.nombre" title="${usuarioHeader}" sortable="false" />
	
	
	<display:caption><spring:message code="evento.comentarios"/></display:caption>
</display:table>
<br/>

<security:authorize access="hasRole('USUARIO')">
	<acme:cancel code="comentario.create" url="comentario/create.do?informacionId=${evento.id}"/>
</security:authorize>
<br>
<acme:back code="evento.return"/>
