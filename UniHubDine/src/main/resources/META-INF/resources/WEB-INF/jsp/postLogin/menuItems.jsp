<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Drinks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/menuItems.css">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/postLogin/loginNavbar.jsp" />
<div class="container mt-5">
    <h2 class="text-center mb-4">Drinks Menu</h2>
    <div class="row">
        <!-- Menu Item 1 -->
        <div class="col-md-4 mb-4">
            <div class="card menu-item">                
                <img src="/images/homepage/CocaCola.png" class="card-img-top" alt="Menu Item 1">
                <div class="card-body">
                    <h5 class="card-title">Item 1</h5>
                    <p class="card-text">Description for Item 1.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$9.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="item1">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Menu Item 1 -->
        <div class="col-md-4 mb-4">
            <div class="card menu-item">
                <!-- Replace with your image path -->
                <img src="/images/menuItems/Sprite.png" class="card-img-top" alt="Menu Item 1">
                <div class="card-body">
                    <h5 class="card-title">Item 1</h5>
                    <p class="card-text">Description for Item 1.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$9.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="item1">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Menu Item 1 -->
        <div class="col-md-4 mb-4">
            <div class="card menu-item">
                <!-- Replace with your image path -->
                <img src="/images/menuItems/pepsi.png" class="card-img-top" alt="Menu Item 1">
                <div class="card-body">
                    <h5 class="card-title">Item 1</h5>
                    <p class="card-text">Description for Item 1.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$9.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="item1">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card menu-item">                
                <img src="/images/menuItems/canadadry.png" class="card-img-top" alt="Menu Item 1">
                <div class="card-body">
                    <h5 class="card-title">Item 1</h5>
                    <p class="card-text">Description for Item 1.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$9.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="item1">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Menu Item 1 -->
        <div class="col-md-4 mb-4">
            <div class="card menu-item">
                <!-- Replace with your image path -->
                <img src="/images/menuItems/mountaindew.png" class="card-img-top" alt="Menu Item 1">
                <div class="card-body">
                    <h5 class="card-title">Item 1</h5>
                    <p class="card-text">Description for Item 1.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$9.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="item1">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Menu Item 1 -->
        <div class="col-md-4 mb-4">
            <div class="card menu-item">
                <!-- Replace with your image path -->
                <div>
                <img src="/images/menuItems/sunkist.webp" class="card-img-top" alt="Menu Item 1"></div>
                <div class="card-body">
                    <h5 class="card-title">Item 1</h5>
                    <p class="card-text">Description for Item 1.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$9.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="item1">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Repeat for each item, changing the number in the id and the image path -->
        <!-- ... other menu items ... -->
    </div>
</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>

</body>
</html>