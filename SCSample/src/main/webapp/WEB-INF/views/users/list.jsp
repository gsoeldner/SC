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
	Users:
</h1>

<ul>
	<c:forEach items="${users}" var="user">
		<spring:url value="/users/{id}" var="showUrl">
			<spring:param name="id" value="${user.id}" />
		</spring:url> 
		<li><a id="showUrl" href="${showUrl}">${user.firstName} ${user.lastName}</a></li>
	</c:forEach>
</ul>
    
</body>
</html>
