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
<div id="descripcion">${noticia.descripcion}</div>

<div id="media">
	<div id="imagen"><img class="imagenNoticia" src="${noticia.imagen}"></div> 
	<div id="video">
	<iframe src="${noticia.video }"></iframe>
	</div>
</div>
<br/>
<br/>

<h2>${noticia.comentarios.size()} <spring:message code="noticia.comentarios"/></h2>
<jstl:forEach items="${noticia.comentarios }" var="comentario">
	<h3>${comentario.titulo} | ${comentario.fecha}</h3>
	<div id="descripcion">${comentario.descripcion}</div>
	<br/>
	<hr>
	<br/>
</jstl:forEach>

<security:authorize access="hasRole('USUARIO')">
	<a href=reporte/create.do?noticiaId=${noticia.id}><spring:message code="reporte.create" /></a>
|  |
<a href=comentario/create.do?informacionId=${noticia.id}><spring:message code="comentario.create" /></a>
|  |
<a href=noticia/usuario/enviar.do?noticiaId=${noticia.id}><spring:message code="noticia.enviar" /></a>
</security:authorize>
<br><br>
<a href=""><input type="button" name="goBack" value="<spring:message code="noticia.goBack"/>" /></a>
