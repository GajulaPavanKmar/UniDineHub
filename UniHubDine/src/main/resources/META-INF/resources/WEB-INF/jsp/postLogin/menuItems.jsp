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
<!-- Sort Buttons -->
<div class="text-center mb-4">
    <button onclick="sortItemsByPrice()" class="btn btn-secondary">Sort by Price</button>
    <button onclick="sortItemsByName()" class="btn btn-secondary">Sort by Name</button>
    <button onclick="sortItemsByDescription()" class="btn btn-secondary">Sort by Description</button>
</div>

    <div class="row" id="itemsContainer">
        <!-- Menu Item 1 -->
        <div class="col-md-4 mb-4">
            <div class="card menu-item" data-price="9.99">                
                <img src="/images/homepage/CocaCola.png" class="card-img-top" alt="Coca Cola">
                <div class="card-body">
                    <h5 class="card-title">Coca Cola</h5>
                    <p class="card-text">Classic cola flavor.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$9.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="coke">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
               <div class="col-md-4 mb-4">
            <div class="card menu-item" data-price="8.99">                
                <img src="/images/menuitems/sprite.png" class="card-img-top" alt="Coca Cola">
                <div class="card-body">
                    <h5 class="card-title">Sprite</h5>
                    <p class="card-text">Classic cola flavor.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$8.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="coke">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
               <div class="col-md-4 mb-4">
            <div class="card menu-item" data-price="5.99">                
                <img src="/images/menuitems/sunkist.webp" class="card-img-top" alt="Coca Cola">
                <div class="card-body">
                    <h5 class="card-title">Sunkist</h5>
                    <p class="card-text">Classic cola flavor.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$5.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="coke">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card menu-item" data-price="5.99">                
                <img src="/images/menuitems/canadadry.png" class="card-img-top" alt="Coca Cola">
                <div class="card-body">
                    <h5 class="card-title">Canada Dry</h5>
                    <p class="card-text">Classic cola flavor.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$5.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="coke">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card menu-item" data-price="5.99">                
                <img src="/images/menuitems/mountaindew.png" class="card-img-top" alt="Coca Cola">
                <div class="card-body">
                    <h5 class="card-title">Mountain Dew</h5>
                    <p class="card-text">Classic cola flavor.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$5.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="coke">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card menu-item" data-price="5.99">                
                <img src="/images/menuitems/pepsi.png" class="card-img-top" alt="Coca Cola">
                <div class="card-body">
                    <h5 class="card-title">Pepsi</h5>
                    <p class="card-text">Classic cola flavor.</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="price">$5.99</span>
                        <button type="button" class="btn btn-primary add-to-cart" data-id="coke">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- ... other menu items with their respective data-price and data-id ... -->
    </div>
</div>
<script>
function sortItemsByPrice() {
    var itemsContainer = document.getElementById('itemsContainer');
    var items = itemsContainer.getElementsByClassName('col-md-4'); // Get the column divs instead of the menu-item divs
    var itemsArray = Array.from(items); // Convert HTMLCollection to Array

    // Sort the items by data-price attribute
    itemsArray.sort(function(a, b) {
        var priceA = parseFloat(a.querySelector('.menu-item').getAttribute('data-price'));
        var priceB = parseFloat(b.querySelector('.menu-item').getAttribute('data-price'));
        return priceA - priceB; // For ascending sort
    });

    // Re-append items to the container in the new order
    itemsArray.forEach(function(item) {
        itemsContainer.appendChild(item);
    });
}
function sortItemsByName() {
    var itemsContainer = document.getElementById('itemsContainer');
    var items = itemsContainer.getElementsByClassName('col-md-4');
    var itemsArray = Array.from(items);

    itemsArray.sort(function(a, b) {
        var nameA = a.querySelector('.card-title').textContent.toUpperCase(); // Convert to uppercase for case-insensitive comparison
        var nameB = b.querySelector('.card-title').textContent.toUpperCase();
        return nameA.localeCompare(nameB); // Use localeCompare for string comparison
    });

    itemsArray.forEach(function(item) {
        itemsContainer.appendChild(item);
    });
}

function sortItemsByDescription() {
    var itemsContainer = document.getElementById('itemsContainer');
    var items = itemsContainer.getElementsByClassName('col-md-4');
    var itemsArray = Array.from(items);

    itemsArray.sort(function(a, b) {
        var descriptionA = a.querySelector('.card-text').textContent.toUpperCase();
        var descriptionB = b.querySelector('.card-text').textContent.toUpperCase();
        return descriptionA.localeCompare(descriptionB);
    });

    itemsArray.forEach(function(item) {
        itemsContainer.appendChild(item);
    });
}
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</body>
</html>