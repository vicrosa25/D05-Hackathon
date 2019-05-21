<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="premios" id="row" requestURI="premio/admin/listAdmin.do" pagesize="5" class="displaytag">

	<spring:message code="premio.nombre" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" sortable="false" />
	
 	<spring:message code="premio.precio" var="precioHeader" />
	<display:column property="precio" title="${precioHeader}" sortable="false"/>
	
	
	
	<display:column><a href="premio/admin/displayAdmin.do?premioId=${row.id}">
 	<spring:message code="premio.display"/></a></display:column> 
	
	<display:column><a href="premio/admin/edit.do?premioId=${row.id}">
 	<spring:message code="premio.edit"/></a></display:column> 
 	
 	


	
</display:table>



 <a href="premio/admin/create.do"><spring:message code="premio.create" /></a>