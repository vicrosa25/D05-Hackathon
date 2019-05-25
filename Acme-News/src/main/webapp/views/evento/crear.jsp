<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="evento/manager/crear.do" modelAttribute="evento">
	<%-- Hidden properties from Domain--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<%-- Hidden properties from Informacion --%>
	<form:hidden path="comentarios" />
	<form:hidden path="usuarios" />
	
	<%-- --------------------- --%>
	
	<acme:textbox code="evento.titulo" path="titulo"/>
	<br />
	
	<acme:textarea code="evento.descripcion" path="descripcion"/>
	<br />
	
	<acme:textbox code="evento.imagen" path="imagen"/>
	<br />

	<acme:textbox code="evento.direccion" path="direccion"/>
	<br />
	
	<acme:textbox code="evento.fecha" path="fecha" placeholder="yyyy/MM/dd"/>
	<br />
	
	<!-- Select Agencia -->
	<acme:select items="${ agencias }" itemLabel="titulo" code="evento.agencia" path="agencia"/>
	<br>

	<input type="submit" name="save"
		value="<spring:message code="evento.save" />" />&nbsp; 

</form:form>
<br/>
	<input type="button" name="goBack" value="<spring:message code="evento.goBack"/>" 
	onclick="javascript: window.location.replace('evento/misEventos.do')"/>