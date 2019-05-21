<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="noticias" id="row" requestURI="noticia/listBanear.do" pagesize="5" class="displaytag">

	<spring:message code="noticia.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="false" />
	
	<spring:message code="noticia.fecha" var="fechaHeader" />
	<display:column property="fecha" title="${fechaHeader}" sortable="false"/>
	
	<spring:message code="noticia.categoria" var="categoriaHeader" />
	<display:column property="categoria" title="${categoriaHeader}" sortable="false"/>

	<display:column><a href="noticia/display.do?noticiaId=${row.id}">
 	<spring:message code="noticia.display"/></a></display:column> 

	<display:column><a href="noticia/aceptar.do?noticiaId=${row.id}">
 	<spring:message code="noticia.aceptar"/></a></display:column> 
 	
 	<display:column><a href="noticia/denegar.do?noticiaId=${row.id}">
 	<spring:message code="noticia.denegar"/></a></display:column> 

</display:table>