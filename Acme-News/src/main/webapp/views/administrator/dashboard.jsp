<%-- Manu --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<spring:message code="administrator.dashboard.query1" var="query1Header" />
<spring:message code="administrator.dashboard.query2" var="query2Header" />
<spring:message code="administrator.dashboard.query3" var="query3Header" />
<spring:message code="administrator.dashboard.query3" var="query3Header" />
<spring:message code="administrator.dashboard.query4" var="query4Header" />
<spring:message code="administrator.dashboard.query5" var="query5Header" />
<spring:message code="administrator.dashboard.query6" var="query6Header" />
<spring:message code="administrator.dashboard.query7" var="query7Header" />
<spring:message code="administrator.dashboard.query8" var="query8Header" />
<spring:message code="administrator.dashboard.query9" var="query9Header" />
<spring:message code="administrator.dashboard.query10" var="query10Header" />
<spring:message code="administrator.dashboard.min" var="minHeader" />
<spring:message code="administrator.dashboard.max" var="maxHeader" />
<spring:message code="administrator.dashboard.avg" var="avgHeader" />
<spring:message code="administrator.dashboard.number" var="numberHeader" />
<spring:message code="administrator.dashboard.name" var="nameHeader" />
<spring:message code="administrator.dashboard.principiante" var="principianteHeader" />
<spring:message code="administrator.dashboard.veterano" var="veteranoHeader" />
<spring:message code="administrator.dashboard.maestro" var="maestroHeader" />
<spring:message code="administrator.dashboard.total" var="totalHeader" />


<head>
<link rel="stylesheet" href="styles/tablas.css" type="text/css">
</head>

<table>
<caption><jstl:out value="${query1Header}"></jstl:out></caption>
<tr>
 <th><jstl:out value="${minHeader}"></jstl:out></th>
 <th><jstl:out value="${maxHeader}"></jstl:out></th>
  <th><jstl:out value="${avgHeader}"></jstl:out></th>

</tr>

  <tr>
  
    <td><jstl:out value="${query1[0]}"></jstl:out></td>
    <td><jstl:out value="${query1[1]}"></jstl:out></td>
    <td><jstl:out value="${query1[2]}"></jstl:out></td>
    
  </tr>
 
  
</table>
<br/>

  <table>
<caption><jstl:out value="${query2Header}"></jstl:out></caption>
  <tr>
    <th><jstl:out value="${nameHeader}"></jstl:out></th>
  </tr>
   <jstl:forEach items="${query2}" var="element1">
      <tr>
           <td>${element1}</td>
      </tr>
   </jstl:forEach>
</table>
<br>
<table>
<caption><jstl:out value="${query3Header}"></jstl:out></caption>
<tr>
 <th><jstl:out value="${principianteHeader}"></jstl:out></th>
 <th><jstl:out value="${veteranoHeader}"></jstl:out></th>
  <th><jstl:out value="${maestroHeader}"></jstl:out></th>

</tr>

  <tr>
  
    <td><jstl:out value="${query3_1}"></jstl:out></td>
    <td><jstl:out value="${query3_2}"></jstl:out></td>
    <td><jstl:out value="${query3_3}"></jstl:out></td>
    
  </tr>
 
  
</table>
<br/>

<table>
<caption><jstl:out value="${query4Header}"></jstl:out></caption>
<tr>
 <th><jstl:out value="${minHeader}"></jstl:out></th>
 <th><jstl:out value="${maxHeader}"></jstl:out></th>
  <th><jstl:out value="${avgHeader}"></jstl:out></th>

</tr>

  <tr>
  
    <td><jstl:out value="${query4[0]}"></jstl:out></td>
    <td><jstl:out value="${query4[1]}"></jstl:out></td>
    <td><jstl:out value="${query4[2]}"></jstl:out></td>
    
  </tr>
 
  
</table>
<br/>

 <table>
<caption><jstl:out value="${query5Header}"></jstl:out></caption>
  <tr>
    <th><jstl:out value="${totalHeader}"></jstl:out></th>
  </tr>
      <tr>
           <td>${query5}</td>
      </tr>
 
</table>

<br>

 <table>
<caption><jstl:out value="${query6Header}"></jstl:out></caption>
  <tr>
    <th><jstl:out value="${nameHeader}"></jstl:out></th>
  </tr>
   <jstl:forEach items="${query6}" var="element1">
      <tr>
           <td>${element1}</td>
      </tr>
   </jstl:forEach>
</table>
<br>

 <table>
<caption><jstl:out value="${query7Header}"></jstl:out></caption>
  <tr>
    <th><jstl:out value="${nameHeader}"></jstl:out></th>
  </tr>
   <jstl:forEach items="${query7}" var="element2">
      <tr>
           <td>${element2}</td>
      </tr>
   </jstl:forEach>
</table>
<br>

 <table>
<caption><jstl:out value="${query8Header}"></jstl:out></caption>
  <tr>
    <th><jstl:out value="${numberHeader}"></jstl:out></th>
  </tr>
      <tr>
           <td>${query8}</td>
      </tr>
 
</table>
<br>


 <table>
<caption><jstl:out value="${query9Header}"></jstl:out></caption>
  <tr>
    <th><jstl:out value="${numberHeader}"></jstl:out></th>
  </tr>
      <tr>
           <td>${query9}</td>
      </tr>
 
</table>
<br>

 <table>
<caption><jstl:out value="${query10Header}"></jstl:out></caption>
  <tr>
    <th><jstl:out value="${numberHeader}"></jstl:out></th>
  </tr>
      <tr>
           <td>${query10}</td>
      </tr>
 
</table>


