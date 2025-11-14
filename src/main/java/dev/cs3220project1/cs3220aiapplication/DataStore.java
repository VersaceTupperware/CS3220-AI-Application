package dev.cs3220project1.cs3220aiapplication;

import dev.cs3220project1.cs3220aiapplication.models.Meal;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataStore {
    private Map<Integer, Meal> userMeals;
    private int count;

    public DataStore(){
        userMeals = new HashMap<Integer, Meal>();
        count = 0;
    }

    public Meal getMeal(int id){
        return userMeals.get(id);
    }

    public void addMeal(Meal meal){
        userMeals.put(count++, meal);
    }

    public Map<Integer, Meal> getUserMeals(){
        return userMeals;
    }

}
