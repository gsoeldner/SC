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
	Edit:
</h1>

	<c:choose>
        <c:when test="${isnew}"><c:set var="method" value="post"/></c:when>
        <c:otherwise><c:set var="method" value="put"/></c:otherwise>
    </c:choose>

	<spring:url value="/users/{id}" var="userUrl">
		<spring:param name="id" value="${user.id}" />
	</spring:url> 
	<form:form modelAttribute="user"  method="${method}">
		<fieldset>
			<legend>
				legend
			</legend>
			<ul>
				<li>
					<label for="lastName">
						name
					</label>
					<div class="control">
						<form:input path="lastName" />
						<form:errors cssClass="error" path="lastName" />
					</div>
				</li>
				<li>
					<label for="firstName">
						firstName
					</label>
					<div class="control">
						<form:input path="firstName" />
						<form:errors cssClass="error" path="firstName" />
					</div>
				</li>
				<li>
					<label for="username">
						username
					</label>
					<div class="control">
						<form:input path="username" />
						<form:errors cssClass="error" path="username" />
					</div>
				</li>				
				<li>
					<label for="password">
						password
					</label>
					<div class="control">
						<form:input path="password" />
						<form:errors cssClass="error" path="password" />
					</div>
				</li>	
			</ul>

			<button id="saveButton" type="submit">save			</button>
			<c:choose>
                <c:when test="${isnew}">
                    <a href="/samples/users">cancel</a>
                </c:when>
                <c:otherwise>
                    <a href="${userUrl}">cancel</a>
                </c:otherwise>
            </c:choose>
			

		</fieldset>
	</form:form>
    
</body>
</html>
