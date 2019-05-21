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

<img src="${periodista.foto}" style="width:304px;height:228px;">

<display:table name="periodista" id="row" requestURI="periodista/display.do" class="displaytag">

	<spring:message code="periodista.nombre" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" sortable="false" />

 	<spring:message code="periodista.apellidos" var="apellidosHeader" />
	<display:column property="apellidos" title="${apellidosHeader}" sortable="false"/>
	
	<spring:message code="periodista.email" var="emailHeader" />
	<display:column property="email" title="${fechaHeader}" sortable="false"/>
</display:table>

<br/>
<a href=""><input type="button" name="goBack" value="<spring:message code="periodista.goBack"/>" /></a>
<br><br>
