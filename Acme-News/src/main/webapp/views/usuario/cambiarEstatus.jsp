<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<link rel="stylesheet" href="styles/display.css" type="text/css">


<spring:message code="necesarios" var="necesarios" />
<spring:message code="usuario.tasaActual" var="tasaActual" />
<spring:message code="usuario.estatus.actual" var="estatus" />
<spring:message code="usuario.tasaNueva" var="tasaNueva" />
<spring:message code="puntosTasa" var="puntosTasa" />
<spring:message code="tienes" var="tienes" />
<spring:message code="costeVeterano" var="costeVeterano" />
<spring:message code="costeMaestro" var="costeMaestro" />


<jstl:out value="${estatus}"></jstl:out>

<jstl:out value="${usuario.estatus}"></jstl:out>
<br>
<br>

<jstl:out value="${tienes}"></jstl:out>
<jstl:out value="${usuario.puntos}"></jstl:out>
<jstl:out value="${puntosTasa}"></jstl:out>
<br>

<jstl:out value="${costeVeterano}"></jstl:out>
<jstl:out value="${tasa.costeVeterano}"></jstl:out>
<jstl:out value="${puntosTasa}"></jstl:out>
<br>

<jstl:out value="${costeMaestro}"></jstl:out>
<jstl:out value="${tasa.costeMaestro}"></jstl:out>
<jstl:out value="${puntosTasa}"></jstl:out>
<br>
<br>

<spring:message code="usuario.estatus" var="estatus" />




<jstl:if test="${usuario.estatus.equals(estatusMaestro)}">

	<img src="images/maestro.png" />
	<br />


	<jstl:out value="${tasaActual}"></jstl:out>

	<jstl:out value="${tasa.puntosMaestro}"></jstl:out>


	<jstl:out value="${puntosTasa}"></jstl:out>


	<spring:message code="usuario.estatusMaximo" var="Maximo" />
	<caption>
		<jstl:out value="${Maximo}"></jstl:out>
	</caption>

</jstl:if>



<jstl:if test="${usuario.estatus.equals(estatusVeterano)}">

	<img src="images/veterano.png" />
	<br />

	<jstl:out value="${tasaActual}"></jstl:out>

	<jstl:out value="${tasa.puntosVeterano}"></jstl:out>


	<jstl:out value="${puntosTasa}"></jstl:out>
	<br>

	<jstl:out value="${tasaNueva}"></jstl:out>

	<jstl:out value="${tasa.puntosMaestro}"></jstl:out>


	<jstl:out value="${puntosTasa}"></jstl:out>

	<form:form action="usuario/cambiarEstatusMetodo.do?">
		<input type="submit" name="Cambiar Estatus"
			value="<spring:message code="usuario.cambiarEstatusAMaestro" />"
			onclick="return confirm('<spring:message code="usuario.confirmar.cambiarEstatus" />')" />&nbsp;
</form:form>
</jstl:if>




<jstl:if test="${usuario.estatus.equals(estatusPrincipiante)}">
	<img src="images/principiante.png" />
	<br />

	<jstl:out value="${tasaActual}"></jstl:out>

	<jstl:out value="${tasa.puntosPrincipiante}"></jstl:out>

	<jstl:out value="${puntosTasa}"></jstl:out>
	<br>
	<jstl:out value="${tasaNueva}"></jstl:out>

	<jstl:out value="${tasa.puntosVeterano}"></jstl:out>

	<jstl:out value="${puntosTasa}"></jstl:out>

	<form:form action="usuario/cambiarEstatusMetodo.do?">
		<input type="submit" name="Cambiar Estatus"
			value="<spring:message code="usuario.cambiarEstatusAVeterano" />"
			onclick="return confirm('<spring:message code="usuario.confirmar.cambiarEstatus" />')" />&nbsp;
</form:form>


</jstl:if>

<jstl:if test="${puntos!=null}">
	<caption>
		<jstl:out value="${necesarios}"></jstl:out>
	</caption>
	<jstl:out value="${puntos}"></jstl:out>
</jstl:if>




