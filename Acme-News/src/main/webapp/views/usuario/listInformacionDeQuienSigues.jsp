<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<link rel="stylesheet" href="styles/display.css" type="text/css">

<display:table name="informacionDeQuienSigues" id="row" requestURI="/usuario/listInformacionDeQuienSigues.do" pagesize="5" class="displaytag">

	 <spring:message code="informacion.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="true" />
	
	<spring:message code="informacion.descripcion" var="descripcionHeader" />
	<display:column property="descripcion" title="${descripcionHeader}" sortable="true" />

	<display:column sortable="false" >
		<img src="${row.imagen}" width="500" height="120" />
	</display:column>

</display:table>
