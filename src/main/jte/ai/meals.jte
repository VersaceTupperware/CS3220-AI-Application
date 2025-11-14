@import dev.cs3220project1.cs3220aiapplication.models.Meal
@param java.util.List<Meal> meals
@param String username

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${username}'s Generated Meals</title>
</head>
<body>
<h1>Generated Meals for ${username}</h1>

<p><a href="/assistant">Generate a new meal</a></p>

<#if meals.isEmpty()>
    <p>You have no generated meals yet.</p>
</#if>

<#if !meals.isEmpty()>
    <table border="1" cellpadding="6">
        <thead>
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Created At</th>
            <th>View</th>
        </tr>
        </thead>
        <tbody>
        <#for (Meal meal : meals)>
            <tr>
                <td>${meal.name()}</td>
                <td>${meal.type()}</td>
                <td>${meal.createdAt()}</td>
                <td><a href="/meals/${meal.id()}">View</a></td>
            </tr>
        </#for>
        </tbody>
    </table>
</#if>

<p><a href="/">Home</a></p>

</body>
</html>
