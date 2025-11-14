@import dev.cs3220project1.cs3220aiapplication.models.Meal
@param Meal meal
@param String username

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${meal.name()}</title>
</head>
<body>
<h1>${meal.name()}</h1>

<p><strong>Owner:</strong> ${username}</p>
<p><strong>Type:</strong> ${meal.type()}</p>
<p><strong>Created at:</strong> ${meal.createdAt()}</p>
<p><strong>Calories per serving:</strong> ${meal.calories()}</p>

<h2>Ingredients</h2>
<ul>
    <#for (var ingredient : meal.ingredients())>
        <li>${ingredient.quantity()} ${ingredient.unit()} ${ingredient.item()} - ${ingredient.notes()}</li>
    </#for>
</ul>

<h2>Instructions</h2>
<ol>
    <#for (var step : meal.instructions())>
        <li>${step.text()}</li>
    </#for>
</ol>

<h2>Nutrition</h2>
<pre>${meal.nutrition()}</pre>

<p>
    <a href="/meals">Back to my meals</a> |
    <a href="/assistant">Generate another</a> |
    <a href="/">Home</a>
</p>

</body>
</html>
