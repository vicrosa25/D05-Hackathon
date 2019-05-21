<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="tasa/administrator/changeTasa.do" modelAttribute="tasa">

	<form:hidden path="version"/>

    <acme:textbox code="tasa.tasaVisita" path="tasaVisita" />
    <br />
    
    <acme:textbox code="tasa.tasaModerador" path="tasaModerador" />
    <br />
    
        <acme:textbox code="tasa.puntosPrincipiante" path="puntosPrincipiante" />
    <br />
    
        <acme:textbox code="tasa.puntosVeterano" path="puntosVeterano" />
    <br />
    
    
        <acme:textbox code="tasa.puntosMaestro" path="puntosMaestro" />
    <br />
    
    
        <acme:textbox code="tasa.costeVeterano" path="costeVeterano" />
    <br />
    
    
        <acme:textbox code="tasa.costeMaestro" path="costeMaestro" />
    <br />
    
	<acme:submit code="tasa.save" name="save" />	

</form:form>