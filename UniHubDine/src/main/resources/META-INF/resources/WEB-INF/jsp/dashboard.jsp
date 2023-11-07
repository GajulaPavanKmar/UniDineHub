<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>University Restaurant Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/dashboard.css">
    <link rel="stylesheet" href="/css/menuItems.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
</head>
<body>

	<jsp:include page="/WEB-INF/jsp/postLogin/loginNavbar.jsp" />
<!-- Welcome Message -->
<div class="container my-3">
    <div class="row">
        <div class="col-12">
            <h1>Welcome, ${user.firstName}!</h1>
        </div>
    </div>
</div>
<!-- Today's Best Deal Section -->
<div class="container my-4">
    <div class="row align-items-center">
        <!-- Content on the left -->
        <div class="col-lg-6">
            <h3>Honey Pepper Pimento Chicken Sandwich</h3>
            <p>$7.19 | 570 cal</p>
            <a href="#" class="btn btn-danger">Order now</a>
        </div>
        <!-- Image on the right with gradient -->
        <div class="col-lg-6">
            <div class="image-gradient-wrapper">
                <div class="image-gradient">
                <img src="/images/login/pizza.jpg" class="image-fluid" alt="Drink">
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container mt-3">
    <div class="row">
        <!-- Example of a menu item in the first column -->
        <div class="col-md-4 mb-4">
            <div class="card">
				<a href="drinks">
		            <img src="/images/login/beverages.jpg" class="card-img-top" alt="Drinks">
		        </a>                
		        <div class="card-body">
                    <h5 class="card-title">Drinks</h5>
                </div>
            </div>
        </div>
        <!-- Example of a menu item in the second column -->
        <div class="col-md-4 mb-4">
            <div class="card">
            <a href="drinks.html">
                <img src="/images/login/salad.png" class="card-img-top" alt="Drink">
                </a>
                <div class="card-body">
                    <h5 class="card-title">Salads</h5>
                </div>
            </div>
        </div>

        <!-- Example of a menu item in the third column -->
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="/images/login/cupcakes.jpg" class="card-img-top" alt="Drink">
                <div class="card-body">
                    <h5 class="card-title">Desserts</h5>
                </div>
            </div>
        </div>
    </div>
   
   <div class="container mt-3">
    <div class="row">
        <!-- Example of a menu item in the first column -->
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="/images/login/beverages.jpg" class="card-img-top" alt="Drink">
                <div class="card-body">
                    <h5 class="card-title">Drinks</h5>
                </div>
            </div>
        </div>

        <!-- Example of a menu item in the second column -->
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="/images/login/salad.png" class="card-img-top" alt="Drink">
                <div class="card-body">
                    <h5 class="card-title">Salads</h5>
                </div>
            </div>
        </div>

        <!-- Example of a menu item in the third column -->
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="/images/login/cupcakes.jpg" class="card-img-top" alt="Drink">
                <div class="card-body">
                    <h5 class="card-title">Desserts</h5>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
