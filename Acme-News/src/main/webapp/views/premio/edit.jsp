<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="premio/admin/edit.do" modelAttribute="premio">

	<%-- Hidden properties from Domain--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<%-- Hidden properties from Event--%>
	<form:label path="nombre">
		<spring:message code="premio.nombre" />:
	</form:label>
	<form:input path="nombre"/>
	<form:errors cssClass="error" path="nombre" />
	<br />
	<br />

	<form:label path="precio">
	<spring:message code="premio.precio" />:
	</form:label>
	<form:input type="number" min="1" path="precio" />
	<form:errors cssClass="error" path="precio" />
	<br/>
	<br />
	<form:label path="imagen">
		<spring:message code="premio.imagen" />:
	</form:label>
	<form:input path="imagen" />
	<form:errors cssClass="error" path="imagen" />
	<br />
	<br />
	
	<form:label path="descripcion">
		<spring:message code="premio.descripcion" />:
	</form:label>
	<form:input path="descripcion" />
	<form:errors cssClass="error" path="descripcion" />
	
	<br />
	<br />
	
<input type="submit" name="save" value="<spring:message code="premio.save" />" />&nbsp; 

<input type="submit" name="delete" 
		onclick="return confirm('<spring:message code="premio.confirm.delete"/>')"
		value="<spring:message code="premio.delete"/>" />
		&nbsp;
		
	<acme:cancel url="premio/admin/listAdmin.do" code="premio.goBack"/>
</form:form>
	
	
	
	
	