@import java.util.*
@import dev.cs3220project1.cs3220aiapplication.models.*
@import dev.cs3220project1.cs3220aiapplication.util.JsonHelper

@param Meal meal
@param String status

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>AI Meal Assistant</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/styles1.css" />
    <link rel="stylesheet" href="css/styles2.css" />
    <style>
        .section-card { background:#fff;border:1px solid rgba(16,24,40,.08);border-radius:16px;box-shadow:0 8px 16px rgba(16,24,40,.06); }
        .breathe{ padding:1rem 1.25rem; }
        .recipe-meta{ color:#6b7280; }
        .badge-chip{ background:rgba(124,58,237,.12);border:1px solid rgba(124,58,237,.28);color:#4a246f; }
        .mono{ font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono","Courier New", monospace; }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
<nav class="navbar navbar-expand-lg navbar-dark header-bg">
    <div class="container">
        <a class="navbar-brand fw-bold text-white" href="/">MealPlanner</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navAI">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div id="navAI" class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link text-white active" href="/assistant">AI Assistant</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="/meals">My Meals</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="/about-us.html">About Us</a></li>
            </ul>
        </div>
    </div>
</nav>

<main class="flex-fill py-4">
    <div class="container">
        <div class="row g-4">
            <div class="col-lg-8">
                <h2 class="mb-3">AI Meal Assistant</h2>

                <!-- Form card -->
                <div class="section-card breathe mb-3">
                    <form id="meal-form" method="post" action="/ai" class="row g-3">
                        <div class="col-md-6">
                            <label class="fw-semibold">Meal Type</label>
                            <select class="form-select" name="mealType">
                                <option value="">Let AI choose</option>
                                <option>BREAKFAST</option>
                                <option>LUNCH</option>
                                <option>DINNER</option>
                                <option>SNACK</option>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <label class="fw-semibold">Servings</label>
                            <input type="number" class="form-control" name="servings" min="1" placeholder="e.g., 2">
                        </div>

                        <div class="col-md-6">
                            <label class="fw-semibold">Dietary Preferences</label>
                            <div class="d-flex flex-wrap gap-3">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="pref" value="vegan" id="prefVegan">
                                    <label class="form-check-label" for="prefVegan">Vegan</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="pref" value="vegetarian" id="prefVegetarian">
                                    <label class="form-check-label" for="prefVegetarian">Vegetarian</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="pref" value="gluten-free" id="prefGlutenFree">
                                    <label class="form-check-label" for="prefGlutenFree">Gluten-free</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="pref" value="keto" id="prefKeto">
                                    <label class="form-check-label" for="prefKeto">Keto</label>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label class="fw-semibold">Calories (target max)</label>
                            <input type="number" class="form-control" name="calories" min="50" step="10" placeholder="e.g., 500">
                        </div>

                        <div class="col-12">
                            <label class="fw-semibold">Optional Note</label>
                            <textarea class="form-control" name="note" rows="2" placeholder="Any constraints or flavor notes…"></textarea>
                        </div>

                        <div class="col-12 d-flex align-items-center gap-2">
                            <button class="btn btn-purple" type="submit">Generate Recipe</button>
                            <a class="btn btn-light-purple" href="/ai">Clear</a>
                            @if(status != null && !status.isBlank())
                                <span class="ms-2 small text-muted">${status}</span>
                            @endif
                        </div>
                    </form>
                </div>

                <!-- Result area -->
                <div id="meal-results">
                    @if(meal != null)
                        @ai.mealcard(meal)
                    @endif
                </div>

            </div>

            <!-- Right column: examples -->
            <div class="col-lg-4">
                <div class="light-purple-bg rounded-4 p-4 h-100">
                    <h5 class="fw-semibold mb-3">Example Inputs</h5>
                    <ul class="mb-0">
                        <li>Breakfast, 2 servings, vegetarian, ≤ 500 kcal</li>
                        <li>Dinner, gluten-free, ≤ 650 kcal, note: spicy</li>
                        <li>Snack, vegan, ≤ 300 kcal</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</main>

<footer class="footer">
    <p class="mb-0">© 2025 MealPlanner · <a href="#">Privacy</a> · <a href="#">Terms</a></p>
</footer>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
    $(function () {
        $("#meal-form").on("submit", function (e) {
            e.preventDefault(); // stop full page reload

            const $form = $(this);

            const $button = $form.find('button[type="submit"]');
            const originalText = $button.text();
            $button.prop("disabled", true).text("Generating...");

            $.ajax({
                url: "/ai/card", // the endpoint
                method: "POST",
                data: $form.serialize(), // send all form fields
                success: function (html) {
                    // Append the new card to the results area
                    $("#meal-results").prepend(html);
                },
                error: function (xhr) {
                    console.error("Error generating meal:", xhr);
                    alert("Failed to generate meal. Please try again.");
                },
                complete: function () {
                    $button.prop("disabled", false).text(originalText);
                }
            });
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
