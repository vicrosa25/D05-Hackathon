<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="comentario/edit.do" modelAttribute="comentario">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="informacion" />
	<form:hidden path="usuario" />
	<form:hidden path="fecha" />
	
	<acme:textbox code="comentario.titulo" path="titulo"/>
	<br>
	
	<acme:textarea code="comentario.descripcion" path="descripcion"/>
	<br>
	
	<acme:submit name="save" code="comentario.save"/>
	
	<jstl:if test="${ key == 'evento' }">
		<acme:cancel url="evento/allEventos.do" code="evento.cancel"/>
	</jstl:if>
	
	<jstl:if test="${ key == 'noticia' }">
		<acme:cancel url="noticia/lista.do" code="evento.cancel"/>
	</jstl:if>
	
</form:form>