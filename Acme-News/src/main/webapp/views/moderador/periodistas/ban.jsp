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

<security:authorize access="hasRole('MODERADOR')">
<display:table name="periodistasToBan" id="row" requestURI="${ requestURI }" pagesize="5" class="displaytag">
	
	
	<display:caption><spring:message code="moderador.journalist.notBanned" /></display:caption>

	<spring:message code="usuario.name" var="nameHeader" />
 	<display:column property="nombre" title="${nameHeader}" sortable="true" />
 

 	<spring:message code="usuario.surname" var="surnameHeader" />
 	<display:column property="apellidos" title="${surnameHeader}" sortable="true" />

 	<spring:message code="usuario.email" var="emailHeader" />
 	<display:column property="email" title="${emailHeader}" sortable="true" />

	<display:column>
	 <form:form action="moderador/periodistas/ban.do?periodistaId=${row.id}">
	 	<input type="submit" name="save" value="<spring:message code="usuario.ban" />" />&nbsp;  
	 </form:form>
	</display:column>


</display:table>
 
<display:table name="periodistasToUnban" id="row" requestURI="${ requestURI }" pagesize="5" class="displaytag">

	<display:caption><spring:message code="moderador.journalist.banned" /></display:caption>

	<spring:message code="usuario.name" var="nameHeader" />
	<display:column property="nombre" title="${nameHeader}" sortable="true" />
 

	<spring:message code="usuario.surname" var="surnameHeader" />
	<display:column property="apellidos" title="${surnameHeader}" sortable="true" />

	<spring:message code="usuario.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" sortable="true" />

	<display:column>
	 <form:form action="moderador/periodistas/ban.do?periodistaId=${row.id}">
	 	<input type="submit" name="save" value="<spring:message code="usuario.unBan" />" />&nbsp;  
	 </form:form>
	</display:column>

</display:table>

 
</security:authorize>
		
