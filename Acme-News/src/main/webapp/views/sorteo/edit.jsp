<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
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
	<br>
	<br>
	
	<form:label path="descripcion">
	<spring:message code="sorteo.descripcion" />:
	</form:label>
	<form:input path="descripcion"/>
	<form:errors cssClass="error" path="descripcion" />
	<br>
	<br>
	
	<acme:dateinput code="sorteo.fechaInicio" format="{0,date,dd/MM/yyyy}" path="fechaInicio" placeholder="dd/mm/yyyy"/>
	<br>
	
	<form:label path="fechaVencimiento">
	<spring:message code="sorteo.fechaVencimiento" />:
	</form:label>
	<form:input path="fechaVencimiento" format="{0,date,dd/MM/yyyy}" placeholder="dd/mm/yyyy" />
	<form:errors cssClass="error" path="fechaVencimiento" />
	<br>
	<br>
	
	<form:label path="puntosNecesarios">
	<spring:message code="sorteo.puntosNecesarios" />:
	</form:label>
	<form:input type="number"  path="puntosNecesarios"/>
	<form:errors cssClass="error" path="puntosNecesarios" />
	<br>
	<br>
	
	<form:label path="premio">
	<spring:message code="sorteo.premio" />:
	</form:label>
	<form:select multiple="false" path="premio" items="${premios}" itemLabel="nombre" itemValue="id"/>
	<form:errors class="error" path="premio" />
	<br>
	<br>
	
	
	<%-- Buttons --%>
	<acme:submit name="save" code="sorteo.save"/> 
	
	<jstl:if test="${ sorteo.id != 0 }">
	<input type="submit" name="delete" 
		onclick="return confirm('<spring:message code="sorteo.confirm.delete"/>')"
		value="<spring:message code="sorteo.delete"/>" />&nbsp;
	</jstl:if>
		
	<acme:cancel url="sorteo/admin/list.do" code="sorteo.cancel"/>
</form:form>

	
	
	
	
	
	
	
	