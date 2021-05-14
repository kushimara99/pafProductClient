<%@ page import="com.productclient.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Products.js"></script>
<link rel="stylesheet" href="view/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">

				<div class="container pt-5 pb-5">

					<center>
						<u><h4 class="mb-3 pb-3">Product Form</h4></u>
					</center>
					<form class="needs-validation" novalidate id="productform">
						<input type='hidden' id='hiddenProductIDSave'
							name='hiddenProductIDSave' value=''>
						<div class="row">

							<div class="col-md-12 mb-3">
								<label for="product_id">Product ID:</label> <input type="text"
									class="form-control" id="product_id" name="product_id">

							</div>
						</div>

						<div class="mb-3">
							<label for="product_code">Product Code:</label> <input
								type="text" class="form-control" id="product_code"
								name="product_code">

						</div>

						<div class="mb-3">
							<label for="product_name">Product Name:</label> <input
								type="text" class="form-control" id="product_name"
								name="product_name">
						</div>

						<div class="mb-3">
							<label for="product_price">Product Price:</label> <input
								type="number" class="form-control" id="product_price"
								name="product_price">
						</div>

						<div class="mb-3">
							<label for="quantity">Quantity:</label> <input type="number"
								class="form-control" id="quantity" name="quantity">

						</div>

						<div class="mb-3">
							<label for="researcher_id">Researcher ID:</label> <input
								type="number" class="form-control" id="researcher_id"
								name="researcher_id">

						</div>
						
				
						
						<div class="mb-3">
							<label for="timestamp">Created On:</label> <input
								type="date" class="form-control" id="timestamp"
								name="timestamp">

						</div>
				</div>

				<button class="btn btn-primary btn-lg btn-block" type="button"
					id="addproduct">SAVE PRODUCT</button>
				</form>
			</div>
		</div>

		<br> <br> <br> <br> <br> <br>

	</div>
	<div id='alertSuccess' name='alertSuccess' class='alert alert-success'></div>
	<div id='alertError' name='alertError' class='alert alert-danger'></div>
	<br>

	<div id="divProductGrid" style="width:100%">
		<%
			Product productObject = new Product();
		out.print(productObject.getProduct());
		%>
	</div>
	</div>
</body>
</html>