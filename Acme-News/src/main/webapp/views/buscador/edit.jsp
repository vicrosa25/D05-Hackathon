<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="buscador/usuario/edit.do" modelAttribute="buscador">
	
	<%-- Hidden properties from buscador--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ultimaActualizacion" />
	
	<%-- palabra--%>
	<acme:textbox code="buscador.palabra" path="palabra" />
	<br>
	
	<spring:message code="noticia.deportes" var="deportes" />
	<spring:message code="noticia.economia" var="economia" />
	<spring:message code="noticia.motor" var="motor" />
	<spring:message code="noticia.cultura" var="cultura" />
	<spring:message code="noticia.ocio" var="ocio" />
	<spring:message code="noticia.españa" var="españa" />
	<spring:message code="noticia.internacional" var="internacional" />
	<spring:message code="noticia.juegos" var="juegos" />
	<spring:message code="noticia.otros" var="otros" />
	<form:label path="categoria">
		<spring:message code="noticia.categoria" />:
	</form:label>
	<form:select path="categoria" >
		<form:option label="${deportes}" value="DEPORTES"/>
		<form:option label="${economia}" value="ECONOMIA"/>
		<form:option label="${motor}" value="MOTOR"/>
		<form:option label="${cultura}" value="CULTURA"/>
		<form:option label="${ocio}" value="OCIO"/>
		<form:option label="${españa}" value="ESPAÑA"/>
		<form:option label="${internacional}" value="INTERNACIONAL"/>
		<form:option label="${juegos}" value="JUEGOS"/>
		<form:option label="${otros}" value="OTROS"/>
	</form:select>
	<form:errors cssClass="error" path="categoria" />
	<br/>
	<br />
	
	<%-- fecha fin --%>
	<acme:dateinput code="buscador.fechaFin" path="fechaFin" placeholder="dd/mm/yyyy" format="{0,date,dd/MM/yyyy HH:mm}" />
	<br>
	
	<%-- fecha inicio --%>
	<acme:dateinput code="buscador.fechaInicio" path="fechaInicio" placeholder="dd/mm/yyyy" format="{0,date,dd/MM/yyyy HH:mm}" />
	<br>	
	
	<%-- Buttons --%>
	<input type="submit" name="save" 
		value="<spring:message code="buscador.save"/>"/>
	
	<acme:cancel code="buscador.clear" url="/buscador/usuario/clear.do" />
	<acme:cancel code="buscador.cancel" url="/" />
		
</form:form>