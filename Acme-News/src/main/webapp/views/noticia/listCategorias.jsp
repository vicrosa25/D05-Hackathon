<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="noticias" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="noticia.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="false" />
	
	<spring:message code="noticia.fecha" var="fechaHeader" />
	<display:column property="fecha" title="${fechaHeader}" sortable="false"/>
 	
 	<spring:message code="noticia.numeroVisitas" var="numeroVisitasHeader" />
	<display:column property="numeroVisitas" title="${numeroVisitasHeader}" sortable="false"/>
	
	<display:column><a href="noticia/display.do?noticiaId=${row.id}">
 	<spring:message code="noticia.display"/></a></display:column> 
 	
 		<security:authorize access="hasRole('USUARIO')">
 	<display:column>
		<form:form action="usuario/compartirInformacion.do?informacionId=${row.id}">
		<input type="submit" name="Compartir" value="<spring:message code="usuario.compartir" />"
			onclick="return confirm('<spring:message code="usuario.confirmar.compartir" />')" />&nbsp;
		</form:form>
	</display:column>
	</security:authorize> 

</display:table>
