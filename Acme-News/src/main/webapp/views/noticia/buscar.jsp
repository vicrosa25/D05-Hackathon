<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="noticia/buscar.do" modelAttribute="search">
	<form:label path="word">
		<spring:message code="noticia.palabraClave" />:
	</form:label>
	<form:input path="word" />
	<form:errors cssClass="error" path="word" />
	<br />
	<input type="submit" name="search" value="<spring:message code="noticia.buscar" />" />&nbsp;
</form:form> 