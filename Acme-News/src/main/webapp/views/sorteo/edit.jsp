<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<form:form action="sorteo/admin/edit.do" modelAttribute="sorteo">

	<%-- Hidden properties from Domain--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="usuarios" />
	<form:hidden path="ganador" />
	
	
	<form:label path="titulo">
		<spring:message code="sorteo.titulo" />:
	</form:label>
	<form:input path="titulo"/>
	<form:errors cssClass="error" path="titulo" />
	<br />
	<br />
	
		<form:label path="descripcion">
		<spring:message code="sorteo.descripcion" />:
	</form:label>
	<form:input path="descripcion"/>
	<form:errors cssClass="error" path="descripcion" />
	<br />
	<br />
	
	
		<form:label path="fechaVencimiento">
		<spring:message code="sorteo.fechaVencimiento" />:
	</form:label>
	<form:input path="fechaVencimiento"/>
	<form:errors cssClass="error" path="fechaVencimiento" />
	<br />
	<br />
	
			<form:label path="puntosNecesarios">
		<spring:message code="sorteo.puntosNecesarios" />:
	</form:label>
	<form:input type="number"  path="puntosNecesarios"/>
	<form:errors cssClass="error" path="puntosNecesarios" />
	<br />
	<br />
	
	
			<form:label path="fechaInicio">
		<spring:message code="sorteo.fechaInicio" />:
	</form:label>
	<form:input path="fechaInicio"/>
	<form:errors cssClass="error" path="fechaInicio" />
	<br />
	<br />
	
<form:label path="premio">
	<spring:message code="sorteo.premio" />:
	</form:label>
	<br>
	<form:select multiple="false" path="premio" items="${premios}" itemLabel="nombre" itemValue="id"/>
	<form:errors class="error" path="premio" />
	
<input type="submit" name="save"
		value="<spring:message code="sorteo.save" />" />&nbsp; 

<input type="submit" name="delete" 
		onclick="return confirm('<spring:message code="sorteo.confirm.delete"/>')"
		value="<spring:message code="sorteo.delete"/>" />
		&nbsp;
</form:form>
<br/>
	<input type="button" name="goBack" value="<spring:message code="sorteo.goBack"/>" 
	onclick="javascript: window.location.replace('sorteo/admin/listAdmin.do')"/>