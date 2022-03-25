<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	
	<!-- jquery cdn -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Bootstrap JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	<!-- plugin -->
	<script src="lib/plugin/cloneData.js"></script>
		
	<!-- address plugin javascript -->
	<script src="lib/js/AddressPlugin.js" type="text/javascript"></script>
	
	<!-- custom css -->
	<link rel="stylesheet" href="lib/css/register.css">
</head>
<body>
	<div class="container">
		<form enctype="multipart/form-data" method="post" action="RegisterServlet">
			<div class="formpart">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="fname">First Name</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon4">Ab</span>
								<input type="text" name="fname" class="form-control" id="fname" placeholder="John" aria-describedby="basic-addon4" required>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="lname">Last Name</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon5">Ab</span>
								<input type="text" name="lname" class="form-control" id="lname" placeholder="Doe" aria-describedby="basic-addon5" required>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="email">Email Address</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">@</span>
								<input type="email" name="email" class="form-control" id="email" placeholder="name@example.com" aria-describedby="basic-addon1" required>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="phone">Phone No.</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon0">
									<span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
								</span>
								<input type="number" name="phone" class="form-control" id="phone" placeholder="1234567890" aria-describedby="basic-addon0" required>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="password1">Password</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon2">
									<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</span>
								<input type="password" name="password1" class="form-control" id="password1" placeholder="JohnDoe@123" aria-describedby="basic-addon2" required>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="password2">Confirm Password</label>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon3">
									<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</span>
								<input type="password" name="password2" class="form-control" id="password2" placeholder="JohnDoe@123" aria-describedby="basic-addon3" required>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Gender</label>
							<div class="gender-group">
								<input type="radio" name="gender" id="male" value="male" required>
								<label for="male">Male</label>
								<input type="radio" name="gender" id="female" value="female">
								<label for="female">Female</label>
								<input type="radio" name="gender" id="other" value="other">
								<label for="other">Other</label>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="birthdate">Birthdate</label>
							<input type="date" class="form-control" name="birthdate" id="birthdate" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Hobby</label>
							<div class="hobby-group">
								<input type="checkbox" id="Movies" name="hobby" class="hobby" value="Movies" required>
								<label for="Movies">Movies </label>
								<input type="checkbox" id="Cricket" name="hobby" class="hobby" value="Cricket">
								<label for="Cricket">Cricket </label>
								<input type="checkbox" id="VideoGame" name="hobby" class="hobby" value="VideoGame">
								<label for="VideoGame">VideoGame </label>
								<input type="checkbox" id="Song" name="hobby" class="hobby" value="Song">
								<label for="Song">Song </label>
								<input type="checkbox" id="Dance" name="hobby" class="hobby" value="Dance">
								<label for="Dance">Dance </label>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="profilepic">Profile Pic</label>
							<input type="file" id="profilepic" name="profilepic" required>
						</div>
					</div>
				</div>
			</div>
			<div class="ques">
				<h4>Security Questions</h4>
				<div class="row">
					<div class="form-group questionbox">
						<div class="col-md-4">
							<label for="que1">1. In What City Were You Born?</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="que1" class="form-control" id="que1" placeholder="Ex. Ahmedabad" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group questionbox">
						<div class="col-md-4">
							<label for="que2">2. What Is The Name Of Your Favourite Pet?</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="que2" class="form-control" id="que2" placeholder="Ex. Dog" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group questionbox">
						<div class="col-md-4">
							<label for="que3">3. What Is The Name Of Your First School?</label>
						</div>
						<div class="col-md-4">
							<input type="text" name="que3" class="form-control" id="que3" placeholder="Ex. Hogwarts School" required>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div id="main-container">
					<div class="container-item card">
						<h4>Address Fields</h4>
						<div class="form-group address-group">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="home">Home Address</label>
										<input type="text" name="home" class="form-control" id="home" placeholder="123B, ABC Street" required>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="city">City</label>
										<input type="text" name="city" class="form-control" id="city" placeholder="Ahmedabad" required>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="state">State</label>
										<input type="text" name="state" class="form-control" id="state" placeholder="Gujarat" required>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="country">Country</label>
										<input type="text" name="country" class="form-control" id="country" placeholder="India" required>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="pincode">Pincode</label>
										<input type="text" name="pincode" class="form-control" id="pincode" placeholder="123456" required>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<a href="javascript:void(0)" class="remove-item btn btn-danger">Remove</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<a class="btn btn-primary" id="add-more" href="javascript:;" role="button">Add Address</a>
			</div>
			
			<div class="button-group">
				<button type="reset" class="btn btn-default">Reset</button>
				<button type="submit" class="btn btn-success">Submit</button>
			</div>
		</form>
		<div>
			<a class="btn btn-primary" id="login-btn" href="index.jsp" role="button">Login</a>
		</div>
	</div>
</body>
</html>