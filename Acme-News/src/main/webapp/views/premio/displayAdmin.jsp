<%-- Manu --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="premio" id="row" >

	<spring:message code="premio.nombre" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" sortable="false" />
	
	<spring:message code="premio.precio" var="precioHeader" />
	<display:column property="precio" title="${precioHeader}" sortable="false" />
	
	<spring:message code="premio.descripcion" var="descripcionHeader" />
	<display:column property="descripcion" title="${descripcionHeader}" sortable="false" />
	
</display:table>
<img src="${premio.imagen}" style="width:304px;height:228px;">
<br/>

<br/>
	<input type="button" name="goBack" value="<spring:message code="premio.goBack"/>" 
	onclick="javascript: window.location.replace('premio/admin/listAdmin.do')"/>