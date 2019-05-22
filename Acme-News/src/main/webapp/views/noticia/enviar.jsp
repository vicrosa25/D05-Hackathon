<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="noticia/usuario/enviar.do" modelAttribute="formulario">
	<%-- Hidden properties --%>
	<form:hidden path="noticiaId" />
	<%-- --------------------------- --%>
	<jstl:if test="${empty usuarios }">
		<spring:message code="usuario.select.empty" />
		<br>
	</jstl:if>
	<jstl:if test="${not empty usuarios }">
		<acme:textarea code="noticia.texto" path="texto"/>
		<br />
		
		<spring:message code="usuario.select" />
		<form:select path="usuario" multiple="false" itemValue="id">
			<form:options items="${usuarios}" itemLabel="nombre" itemValue="id" />
		</form:select>
		<form:errors class="error" path="usuario" />
		<br>
		<br>
	
		<input type="submit" name="save"
			value="<spring:message code="noticia.save" />" />&nbsp; 
	</jstl:if>
</form:form>
<br/>
<acme:cancel code="noticia.goBack" url="/" />