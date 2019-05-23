 

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="welcome.greeting.prefix" /> ${name}<spring:message code="welcome.greeting.suffix" /></p>


<!-- Manage greeting -->
<jstl:if test="${language == 'en'}">
	<p>${englishMessage} ${moment}</p> 
</jstl:if>
<jstl:if test="${language == 'es'}">
	<p>${spanishMessage} ${moment}</p> 
</jstl:if>

	 
<img src="${bannerAleatorio}">
