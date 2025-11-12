@param String error
@param String success

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
    <link rel="stylesheet" href="/css/styles1.css" />
    <link rel="stylesheet" href="/css/UserAccountCreation.css" />
    <title>User Sign Up Page</title>

</head>
<body class="d-flex flex-column min-vh-100">
<nav class="navbar navbar-expand-lg header-bg">
    <div class="container">
        <a class="navbar-brand text-white fw-bold" href="#">AI</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link text-white" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="#">Chat</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="#">About us</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="signup-container">
    <div class="row signup-row g-0">
        <div class="col-lg-4 col-md-5 mb-3 mb-lg-0">
            <div class="avatar-section">
                <div class="avatar">
                    <i class="bi bi-person-circle"></i>
                </div>
                <p class="welcome-text mt-4">Welcome! Create your account</p>
            </div>
        </div>

        <div class="col-lg-8 col-md-7">
            <div class="form-card">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <ul class="nav nav-tabs p-0">
                        <li class="nav-item"><a class="nav-link active" href="#">Create Account</a></li>
                    </ul>
                    <button class="btn btn-outline-secondary">Log In</button>
                </div>

                <div class="info-banner mb-3">
                    <h2>Create Account</h2>
                    <p>Fill in the details below to get started with your new account</p>
                </div>

                @if(error != null && !error.isEmpty())
                    <div class="alert alert-danger">@{error}</div>
                @endif

                @if(success != null && !success.isEmpty())
                    <div class="alert alert-success">@{success}</div>
                @endif

                <form method="post" action="/register" class="mt-3">
                    <div class="row g-3 mb-3">
                        <div class="col-md-6">
                            <label for="firstName" class="form-label">First Name</label>
                            <input name="firstName" type="text" class="form-control" id="firstName" placeholder="Enter your first name" required>
                        </div>
                        <div class="col-md-6">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input name="lastName" type="text" class="form-control" id="lastName" placeholder="Enter your last name" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email Address</label>
                        <input name="email" type="email" class="form-control" id="email" placeholder="your@email.com" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input name="password" type="password" class="form-control" id="password" placeholder="••••••••" required>
                    </div>

                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">Confirm Password</label>
                        <input name="confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder="••••••••" required>
                    </div>

                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn-signup">Sign Up</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="footer text-center py-3">
    <p class="mb-0">Already have an account? <a href="#">Log In</a></p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
