<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="noticia/crear.do" modelAttribute="noticia">
	<%-- Hidden properties from Domain--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<%-- Hidden properties from Informacion --%>
	<form:hidden path="comentarios" />
	<form:hidden path="usuarios" />
	
	<%-- Hidden properties from Noticia--%>
	<form:hidden path="estado" />
	<form:hidden path="fecha" />
	<form:hidden path="numeroVisitas" />
	<form:hidden path="noticiasRelacionadas" />
	<form:hidden path="reportes" />
	<%-- --------------------------- --%>
	
	<acme:textbox code="noticia.titulo" path="titulo"/>
	<br />
	
	<acme:textarea code="noticia.descripcion" path="descripcion"/>
	<br />
	
	<acme:textbox code="noticia.imagen" path="imagen"/>
	<br />
	
	<spring:message code="noticia.placeholder.video" var="placeholder" />
	<acme:textbox code="noticia.video" path="video" placeholder="${placeholder}"/>
	
	<br />
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

<input type="submit" name="save"
		value="<spring:message code="noticia.save" />" />&nbsp; 

</form:form>
<br/>
	<input type="button" name="goBack" value="<spring:message code="noticia.goBack"/>" 
	onclick="javascript: window.location.replace('noticia/misNoticias.do')"/>