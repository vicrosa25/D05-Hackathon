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



 <form:form action="usuario/checkBan.do">
 <input type="text" name="username" value="${holder}"/>  
		 <input type="submit" name="check"
		value="<spring:message code="usuario.probe" />" />&nbsp; 
		
 
 </form:form>
 

 	<spring:message code="usuario.youAskBan"  var="ban1"/>
	<br><br>
	<p id="error" style="color:black; font-size:20px">${ban1}</p> 
	

 

 
  <jstl:if test="${banned==2}">
 	<spring:message code="usuario.youBanned"  var="ban2"/>
	<br><br>
	<p id="error" style="color:red; font-size:30px">${ban2}</p>   

 
 </jstl:if>
 
   <jstl:if test="${banned==1}">
 	<spring:message code="usuario.youNotBanned"  var="ban3"/>
	<br><br>
	<p id="error" style="color:green; font-size:30px">${ban3}</p>   
	
	
	
 
 </jstl:if>
 

		
