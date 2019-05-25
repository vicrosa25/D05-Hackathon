<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="periodista.dinero"  var="dinero"/>
	
<p id="dinero" style="color:green; font-size:30px">${dinero}</p> 
<input type="text" name="dineroAcumulado" readonly="readonly" value="${dineroAcumulado} Euros" >
 <form:form action="periodista/retirarDinero.do">
 
	<input type="submit" name="retirar"
		value="<spring:message code="periodista.retirar" />" />&nbsp; 
		 
 </form:form>

<spring:message code="periodista.dineroAcumulado"  var="dineroAcumulado"/>
<br>
<p id="dineroTotal" style="color:green; font-size:30px">${dineroAcumulado}</p>   
<input type="text" name="dineroAcumuladoTotal" readonly="readonly" value="${dineroAcumuladoTotal} Euros" >
 
<spring:message code="periodista.errorRetirar"  var="errorRetirar"/>
<jstl:if test="${error==1}">
<p id="errorRetirar" style="color:red; font-size:30px">${errorRetirar}</p>   
</jstl:if>

 

		
