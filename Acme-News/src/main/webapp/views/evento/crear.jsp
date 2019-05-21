<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="evento/crear.do" modelAttribute="evento">
	<%-- Hidden properties from Domain--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<%-- Hidden properties from Informacion --%>
	<form:hidden path="comentarios" />
	<form:hidden path="usuarios" />
	
	<%-- --------------------- --%>
	
	<acme:textarea code="evento.titulo" path="titulo"/>
	<br />
	<br />
	
	<acme:textarea code="evento.descripcion" path="descripcion"/>
	<br />
	<br />
	
	<acme:textbox code="evento.imagen" path="imagen"/>
	<br />
	<br />

	<acme:textbox code="evento.direccion" path="direccion"/>
	<br />
	<br />
	
	<acme:textbox code="evento.fecha" path="fecha" placeholder="yyyy/MM/dd"/>
	<br />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="evento.save" />" />&nbsp; 

</form:form>
<br/>
	<input type="button" name="goBack" value="<spring:message code="evento.goBack"/>" 
	onclick="javascript: window.location.replace('evento/misEventos.do')"/>