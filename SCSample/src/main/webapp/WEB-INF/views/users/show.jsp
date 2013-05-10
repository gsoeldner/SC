<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>User</title>
</head>
<body>
<h1>
	User:
</h1>

<spring:url value="/users" var="listUrl"></spring:url> 				
(<a id="listUrl" href="${listUrl}">list</a>)

<ul>	
	<li><p>Name: ${user.firstName} ${user.lastName}</p></li>
	<li><p>Username: ${user.username} </p></li>
	<li><p>Status: ${user.status} </p></li>
</ul>

<spring:url value="/users/{id}/edit" var="editUrl">
	<spring:param name="id" value="${user.id}" />
</spring:url> 				
(<a id="editUrl" href="${editUrl}">edit</a>)

	<c:choose>
         <c:when test="${delete}">
         Are you sure?
             <spring:url value="/users/{id}" var="userUrl">
				<spring:param name="id" value="${user.id}" />
			</spring:url> 
			<form:form   method="DELETE">
				<button id="deleteButton" type="submit">Yes	</button>
				<a href="${userUrl}">cancel</a>
			</form:form>			 
         </c:when>
         <c:otherwise>
             <spring:url value="/users/{id}/delete" var="deleteUrl">
					<spring:param name="id" value="${user.id}" />
			</spring:url> 				
			(<a id="deleteUrl" href="${deleteUrl}">delete</a>)
         </c:otherwise>
     </c:choose>
</body>
</html>
