<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
                <form action="YOUR_SIGNUP_ENDPOINT" method="post">
                    <div class="mb-4">
                        <label for="signup-username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="signup-username" name="signup-username" placeholder="Choose a username" required>
                    </div>

                    <div class="mb-4">
                        <label for="signup-email" class="form-label">Email Address</label>
                        <input type="email" class="form-control" id="signup-email" name="signup-email" placeholder="Enter your email" required>
                    </div>

                    <div class="mb-4">
                        <label for="signup-password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="signup-password" name="signup-password" placeholder="Choose a password" required>
                    </div>

                    <button type="submit" class="btn btn-block">Sign Up</button>
                </form>
                <p class="text-center">Already have an account? <a href="login">Log In</a></p>
            </div>
        </div>
    </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
