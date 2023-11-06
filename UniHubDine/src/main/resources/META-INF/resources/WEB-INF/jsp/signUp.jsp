<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sign Up | UniHubDine</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/loginstyles.css">
    
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/navbar.jsp" />
	<br><br>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="login-card">
                <h2>Create Your Account</h2>
                <%-- <c:if test="${not empty errorMsg }">
                 <div class="alert alert-danger" role="alert">${errorMsg}</div>
                </c:if>
                 <c:if test="${not empty successMsg }">
                 <div class="alert alert-success" role="alert">${successMsg}</div>
                </c:if> --%>
                <form:form  method="post" modelAttribute="user">
                    <div class="mb-4">
                        <label for="signup-username" class="form-label">Username</label>
                        <form:input type="text" class="form-control" path="userId" placeholder="Choose a username" />
                    </div>

					<div class="mb-4">
                        <label for="signup-email" class="form-label">Email address</label>
                        <form:input type="email" class="form-control" path="useremail" placeholder="Enter email Address" />
                    </div>

                    <div class="mb-4">
                        <label for="signup-password" class="form-label">Password</label>
                        <form:input type="password" class="form-control" path="password" placeholder="Choose a password" />
                    </div>

                    <form:button type="submit" class="btn btn-block">Sign Up</form:button>
                </form:form>
                <p class="text-center">Already have an account? <a href="login">Log In</a></p>
            </div>
        </div>
    </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
