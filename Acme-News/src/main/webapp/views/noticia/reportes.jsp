<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<link rel="stylesheet" href="styles/display.css" type="text/css">


<h1>${noticia.titulo}</h1>
<h2>${noticia.fecha} | ${noticia.categoria}</h2>
<br/>
<h2>${noticia.reportes.size()} <spring:message code="noticia.reportes"/></h2>
<jstl:forEach items="${noticia.reportes }" var="reporte">
	<div id="descripcion">
		${reporte.usuario.nombre}:
		<br/>
		<br/>
		&nbsp;&nbsp;&nbsp;${reporte.texto}</div>
	<br/>
	<br/>
</jstl:forEach>

<button class="button" 
	style="vertical-align:middle"
	onclick="location.href='noticia/banear.do?noticiaId=${noticia.id}'"
	><span><spring:message code="noticia.banear"/></span></button>