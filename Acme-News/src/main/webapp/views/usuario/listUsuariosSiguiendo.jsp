<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${not empty usuariosSiguiendo}">
<display:table name="usuariosSiguiendo" id="row" requestURI="/usuario/listUsuariosSiguiendo.do" pagesize="5" class="displaytag">

	<spring:message code="usuario.nombre" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" sortable="true" />
	
	<spring:message code="usuario.apellidos" var="apellidosHeader" />
	<display:column property="apellidos" title="${apellidosHeader}" sortable="true" />
	
	<spring:message code="usuario.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" sortable="true" />
	
	<spring:message code="usuario.estatus" var="estatusHeader" />
	<display:column property="estatus" title="${estatusHeader}" sortable="true" />
	
	<display:column>
	<form:form action="usuario/noSeguirUsuario.do?usuarioId=${row.id}">
	<input type="submit" name="noSeguir" value="<spring:message code="usuario.noSeguir" />"
	onclick="return confirm('<spring:message code="usuario.confirmar.noSeguir" />')" />&nbsp;
	</form:form>
	</display:column>
</display:table>
</jstl:if>
<jstl:if test="${empty usuariosSiguiendo}">
	<spring:message code="usuario.siguiendo.vacio"/>
	<br>
</jstl:if>

<jstl:if test="${not empty usuarios}">
<display:table name="usuarios" id="row" requestURI="/usuario/listUsuariosSiguiendo.do" pagesize="5" class="displaytag">

	<spring:message code="usuario.nombre" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" sortable="true" />
	
	<spring:message code="usuario.apellidos" var="apellidosHeader" />
	<display:column property="apellidos" title="${apellidosHeader}" sortable="true" />
	
	<spring:message code="usuario.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" sortable="true" />
	
	<spring:message code="usuario.estatus" var="estatusHeader" />
	<display:column property="estatus" title="${estatusHeader}" sortable="true" />
	
	<display:column>
<form:form action="usuario/seguirUsuario.do?usuarioId=${row.id}">
<input type="submit" name="Seguir" value="<spring:message code="usuario.seguir" />"
			onclick="return confirm('<spring:message code="usuario.confirmar.seguir" />')" />&nbsp;
</form:form>
</display:column>

</display:table>
</jstl:if>
<jstl:if test="${empty usuarios}">
	<spring:message code="usuario.siguiendo.lleno"/>
	<br>
</jstl:if>
