<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UniHubDine</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
	    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY"></script>
	
</head>
<link rel="stylesheet" href="/css/styleshome.css">
<script type="text/javascript" src="/js/homejs.js"></script>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">UniHubDine</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#menu">Menu</a></li>
					<a class="nav-link active" aria-current="page" href="#partners">Partners</a>
					</li>
					<a class="nav-link active" aria-current="page" href="#aboutus">About
						Us</a>
					</li>
					<a class="nav-link active" aria-current="page" href="#locations">Locations</a>
					</li>
					<a class="nav-link active" aria-current="page" href="#contactus">Contact
						Us</a>
					</li>
					<button class="btn btn-outline-success" type="submit">Sign
						In</button>
				</ul>
			</div>
		</div>
	</nav>
	<div id="carouselExampleCaptions" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="/images/homepage/hbackground1.png" class="d-block w-100"
					alt="...">
				<div class="carousel-caption">
					<h5>First slide label</h5>
					<p>Some representative placeholder content for the first slide.</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="/images/homepage/hbackground2.png" class="d-block w-100"
					alt="...">
				<div class="carousel-caption">
					<h5>Second slide label</h5>
					<p>Some representative placeholder content for the second
						slide.</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="/images/homepage/hbackground3.png" class="d-block w-100"
					alt="...">
				<div class="carousel-caption">
					<h5>Third slide label</h5>
					<p>Some representative placeholder content for the third slide.</p>
				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<!-- About us -->
	<section>
		<!-- Menu Filter Section -->
		<div class="container mt-5">
			<div class="menu-filter mb-4">
				<button class="btn btn-primary menu-btn" data-menu="drinks">Drinks</button>
				<button class="btn btn-primary menu-btn" data-menu="coffees">Coffees</button>
				<button class="btn btn-primary menu-btn" data-menu="desserts">Desserts</button>
			</div>

			<div class="container mt-5">

				<!-- Drinks Section -->
				<div class="row menu-section" id="drinks">
					<h2 class="menu-title">Drinks</h2>
					<!-- Row 1 for Drinks -->
					<div class="row mb-4">
						<!-- Drink 1 -->
						<div class="col-md-6">
							<div class="card mb-4">
								<div
									class="card-body d-flex justify-content-between align-items-center">
									<img src="/images/homepage/CocaCola.png"
										class="card-img-top partner-logo" alt="Drink 1 Name">
									<div>
										<h5 class="card-title mb-0">Coca-Cola</h5>
										<p class="card-text mb-0">Refreshing soft drink.</p>
									</div>
									<p class="card-text mb-0">
										<strong>$1.00</strong>
									</p>
								</div>
							</div>
						</div>
						<!-- Drink 2 -->
						<!-- Coca-Cola -->
						<div class="col-md-6">
							<div class="card mb-4">
								<div
									class="card-body d-flex justify-content-between align-items-center">
									<img src="/images/homepage/CocaCola.png"
										class="card-img-top partner-logo" alt="Drink 1 Name">
									<div>
										<h5 class="card-title mb-0">Coca-Cola</h5>
										<p class="card-text mb-0">Refreshing soft drink.</p>
									</div>
									<p class="card-text mb-0">
										<strong>$1.00</strong>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>

	</section>


	<section>

		<div class="container mt-5">

			<!-- Row 1 -->
			<div class="row mb-4">
				<!-- Partner 1 -->
				<div class="col-md-4">
					<div class="card">
						<img src="/images/homepage/starbucks.webp" class="card-img-top partner-logo" alt="Partner 1 Name">
						<div class="card-body text-center">
							<h5 class="card-title">Starbucks</h5>
							<p class="card-text">One liner about Partner 1.</p>
						</div>
					</div>
				</div>
				<!-- Partner 2 -->
				<div class="col-md-4">
					<div class="card">
						<img src="/images/homepage/dunkin.png" class="card-img-top partner-logo" alt="Partner 1 Name">
						<div class="card-body text-center">
							<h5 class="card-title">Dunkin</h5>
							<p class="card-text">One liner about Partner 2.</p>
						</div>
					</div>
				</div>
				<!-- Partner 3 -->
				<div class="col-md-4">
					<div class="card">
						<img src="/images/homepage/chickfilla.png" class="card-img-top partner-logo" alt="Partner 1 Name">
						<div class="card-body text-center">
							<h5 class="card-title">Chick-Fill-A</h5>
							<p class="card-text">One liner about Partner 3.</p>
						</div>
					</div>
				</div>
			</div>

			<!-- Row 2 -->
			<div class="row">
				<!-- Partner 4 -->
				<div class="col-md-4">
					<div class="card">
						<img src="/images/homepage/einstein.png" class="card-img-top partner-logo" alt="Partner 1 Name">
						<div class="card-body text-center">
							<h5 class="card-title">Einstein</h5>
							<p class="card-text">One liner about Partner 3.</p>
						</div>
					</div>
				</div>
				<!-- Partner 5 -->
				<div class="col-md-4">
					<div class="card">
						<img src="/images/homepage/kfc.png" class="card-img-top partner-logo" alt="Partner 1 Name">
						<div class="card-body text-center">
							<h5 class="card-title">KFC</h5>
							<p class="card-text">One liner about Partner 3.</p>
						</div>
					</div>
				</div>
				<!-- Partner 6 -->
				<div class="col-md-4">
					<div class="card">
						<img src="/images/homepage/mcd.png" class="card-img-top partner-logo" alt="Partner 1 Name">
						<div class="card-body text-center">
							<h5 class="card-title">KFC</h5>
							<p class="card-text">One liner about Partner 3.</p>
						</div>
					</div>
				</div>
			</div>

		</div>
	</section>
	<section>
	<div class="container mt-5">

        <!-- Google Map -->
        <div id="map" style="width: 100%; height: 400px; margin-bottom: 20px;"></div>

       <div class="row" align="center">
            <div class="col-md-4">
                <h4>Address</h4>
                <p>1000 Hilltop Cir, Baltimore, MD 21250, USA</p>
            </div>
            <div class="col-md-4">
                <h4>Phone Number</h4>
                <p>+1 (410) 455-1000</p>
            </div>
            <div class="col-md-4 d-flex align-items-center">
                <a href="https://www.google.com/maps/dir//1000+Hilltop+Cir,+Baltimore,+MD+21250,+USA" target="_blank" class="btn btn-primary">Get Directions</a>
            </div>
        </div>
    </div>
    

    <script>
        function initMap() {
            var umbcLocation = {lat: 39.2555, lng: -76.7113};
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 15,
                center: umbcLocation
            });
            var marker = new google.maps.Marker({
                position: umbcLocation,
                map: map
            });
        }
        initMap();
    </script>
	</section>
	<section>
		<div class="container mt-5" id="form-container">
	<h2>Contact Us</h2>

        <form action="YOUR_SERVER_ENDPOINT" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="mb-3">
                <label for="subject" class="form-label">Subject</label>
                <input type="text" class="form-control" id="subject" name="subject">
            </div>

            <div class="mb-3">
                <label for="message" class="form-label">Message</label>
                <textarea class="form-control" id="message" name="message" rows="4" required></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
	</div>
	</section>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>
</html>