<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing Grid -->
<display:table name="banners" id="row" requestURI="#{ requestURI }" pagesize="5" class="displaytag">

	<!-- Url imgae -->
	<spring:message code="banner.image" var="imageHeader" />
	<display:column title="${imageHeader}" >
		<img src="${ row.url }" width="50%" height="200"/>
	</display:column>
	
	
	<!-- Url text -->
	<spring:message code="banner.url" var="urlHeader" />
	<display:column title="${urlHeader}">${ row.url }</display:column>
	
	
	<!-- Remove word link -->
	<spring:message code="banner.delete" var="deleteHeader" />
	<display:column title="${deleteHeader}">
		<a href="banner/admin/delete.do?bannerId=${ row.id }"><spring:message code="banner.delete" /></a>
	</display:column>

</display:table>

<!-- Create link -->
<security:authorize access="hasRole('ADMIN')">
	<a href=banner/admin/create.do><spring:message code="banner.create" /></a>
</security:authorize>

<br><br>



