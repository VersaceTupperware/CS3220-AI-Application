@param boolean isLoggedIn
@param String username
@param int year

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/styles1.css" />
    <link rel="stylesheet" href="UserAccountCreation.css" />
    <title>MealPlanner — Home</title>
    <style>
        .hero-box {
            background: var(--light-purple);
            border-radius: 1rem;
            min-height: 320px;
        }
        .promo-strip {
            background: rgba(124, 58, 237, 0.15);
            border-radius: 12px;
        }
        .promo-chip {
            height: 32px;
            border-radius: 8px;
            background: rgba(124, 58, 237, 0.25);
        }
        .btn-purple { background: #7c3aed; color: #fff; border: none; }
        .btn-light-purple { background: rgba(124,58,237,0.08); border: none; color: #7c3aed; }
        .light-purple-bg { background: rgba(124,58,237,0.06); }
        .header-bg { background: #6d28d9; }
        .footer { text-align: center; padding: 1rem; color: #6b7280; }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
<nav class="navbar navbar-expand-lg header-bg">
    <div class="container">
        <a class="navbar-brand text-white fw-bold" href="#">MealPlanner</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link text-white active" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="assistant">Create Meals</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="#">About Us</a></li>
            </ul>
        </div>
    </div>
</nav>

<section class="py-4">
    <div class="container">
        <div class="promo-strip p-3 p-md-4">
            <div class="row g-3 align-items-center">
                <div class="col-12 col-md-3 d-flex align-items-center">
                    <img class="rounded-circle me-3" src="robothead.png" alt="Avatar" width="48" height="48">
                    @if(isLoggedIn)
                        <div class="small text-muted">Welcome back, ${username}!</div>
                    @else
                        <div class="small text-muted">Welcome back!</div>
                    @endif
                </div>

                <div class="col-12 col-md-7">
                    <div class="d-flex gap-3">
                        <div class="promo-chip flex-fill"></div>
                        <div class="promo-chip flex-fill"></div>
                        <div class="promo-chip flex-fill"></div>
                    </div>
                </div>

                <div class="col-12 col-md-2 text-md-end">
                    @if(isLoggedIn)
                        <a class="btn btn-light w-100 w-md-auto" href="/logout">Log Out</a>
                    @else
                        <a class="btn btn-purple w-100 w-md-auto" href="/login">Log In</a>
                    @endif
                </div>
            </div>
        </div>
    </div>
</section>

<main class="flex-fill">
    <section class="py-4">
        <div class="container">
            <div class="hero-box p-4 p-md-5 shadow-sm">
                <h1 class="fw-bold mb-3">Plan meals with AI—fast.</h1>
                <p class="lead mb-4">
                    Our platform helps you generate balanced meal plans, track nutrition,
                    and personalize recipes based on your preferences and dietary needs.
                </p>
                <div class="d-flex gap-2">
                    <a class="btn btn-purple" href="/register">Get Started</a>
                    <a class="btn btn-light-purple" href="#">See a Demo</a>
                </div>
            </div>
        </div>
    </section>

    <section class="py-4">
        <div class="container">
            <div class="row g-4">
                <div class="col-md-4">
                    <div class="light-purple-bg rounded-4 p-4 h-100">
                        <h5 class="fw-semibold">Smart Meal Plans</h5>
                        <p class="mb-0 small text-muted">
                            Auto-generate weekly plans tailored to your goals, allergies, and taste.
                            Replace dishes instantly with similar nutrition profiles.
                        </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="light-purple-bg rounded-4 p-4 h-100">
                        <h5 class="fw-semibold">Nutrition Tracking</h5>
                        <p class="mb-0 small text-muted">
                            See calories, macros, and key vitamins/minerals at a glance. Export to CSV or share with a coach.
                        </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="light-purple-bg rounded-4 p-4 h-100">
                        <h5 class="fw-semibold">Grocery Shortcuts</h5>
                        <p class="mb-0 small text-muted">
                            One-click grocery lists organized by aisle and budget. Works with your preferred store.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="py-4">
        <div class="container">
            <div class="light-purple-bg rounded-4 p-4">
                <div class="d-md-flex align-items-center justify-content-between">
                    <div>
                        <h5 class="fw-semibold mb-1">Ready to start?</h5>
                        <div class="small text-muted">Create an account in seconds and generate your first plan today.</div>
                    </div>
                    <a class="btn btn-purple mt-3 mt-md-0" href="/register">Create Account</a>
                </div>
            </div>
        </div>
    </section>
</main>

<div class="footer">
    <p class="mb-0">© ${year} MealPlanner · <a href="#">Privacy</a> · <a href="#">Terms</a></p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
