<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="registration/periodista.do" commandName="periodistaForm">
	
	 <%-- username--%>
	 <acme:textbox code="periodista.username" path="userAccount.username" />
	 <br>

	<%-- password --%>
	<acme:password code="periodista.password" path="userAccount.password"/>
	<br>
	
	<%-- repit password --%>
	<acme:password code="periodista.passwordCheck" path="passwordCheck"/>
	<br>
	
	<%-- Name --%>
	<acme:textbox code="periodista.name" path="nombre"/>
	<br>
	
	<%-- Surname --%>
	<acme:textbox code="periodista.surname" path="apellidos"/>
	<br>
		
	<%-- email --%>
	<acme:textbox code="periodista.email" path="email"/>
	<br><br>
	
	<%-- email --%>
	<acme:textbox code="periodista.paypalEmail" path="cartera.paypalEmail"/>
	<br><br>
	
	
	<%-- Accept Legal term --%>
	<form:label path="accepted">
		<spring:message code="periodista.accept" />
	</form:label>
	<form:checkbox path="accepted"/>
	<br><br>
	
	
	
	<%-- Buttons --%>
	<acme:submit name="register" code="periodista.register"/>
	<a href=""><input type="button" name="goBack" value="<spring:message code="periodista.cancel"/>" /></a>
	<br><br>
</form:form>