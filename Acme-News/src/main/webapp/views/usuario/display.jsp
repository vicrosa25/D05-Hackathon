<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>




<ul>
	<acme:field code="usuario.username" value="${usuario.userAccount.username}" />	
	<acme:field code="usuario.nombre" value="${usuario.nombre}" />	
	<acme:field code="usuario.apellidos" value="${usuario.apellidos}" />
	<acme:field code="usuario.email" value="${usuario.email}" />		
</ul>

<br/>
<a href=""><input type="button" name="goBack" value="<spring:message code="usuario.goBack"/>" /></a>
<br><br>