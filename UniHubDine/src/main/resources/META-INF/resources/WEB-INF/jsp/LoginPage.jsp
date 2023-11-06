<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sign In | UniHubDine</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-k6RqeWeci5ZR/Lv4MR0sA0FfDOMt23cez/5paNdF+TmFE5o2D1z7uJz7T9WSVfaH" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/loginstyles.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/navbar.jsp" />
<br><br>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="login-card">
                <h2>Welcome Back!</h2>
                <h2>${errorMsg}</h2>
                <form  method="post">
                    <div class="mb-4">
                        <label for="login-username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="userId" name="userId" placeholder="Enter your username" required>
                    </div>

                    <div class="mb-4">
                        <label for="login-password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                    </div>

                    <button class="btn btn-block">Log In</button>
                </form>
                <p class="text-center">New here? <a href="signup">Create an account</a></p>
            </div>
        </div>
    </div>
</div>

<!-- ... [same as before] ... -->
	

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
