<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<spring:url value="/users" var="listUrl"></spring:url> 				
(<a id="listUrl" href="${listUrl}">list</a>)
<spring:url value="/users/ajax" var="ajaxUrl"></spring:url> 				
(<a id="ajaxUrl" href="${ajaxUrl}">Ajax</a>)
</body>
</html>
