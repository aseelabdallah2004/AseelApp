package com.example.signuplogin;

enum RecipeType { vegetarian ,vegan ,dairyFree ,breakfast , lunch , dinner ,sweet ,salty}


public class HYRecipes {
    private String ingredients;
    private double time;
    private int servings;
    private int calories;
    private String info;
    private RecipeType recipe;
    private String photo;

    public HYRecipes(String ingredients, double time, int servings, int calories, String info, RecipeType recipe, String photo) {
        this.ingredients = ingredients;
        this.time = time;
        this.servings = servings;
        this.calories = calories;
        this.info = info;
        this.recipe = recipe;
        this.photo = photo;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public RecipeType getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeType recipe) {
        this.recipe = recipe;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "HYRecipes{" +
                "ingredients='" + ingredients + '\'' +
                ", time=" + time +
                ", servings=" + servings +
                ", calories=" + calories +
                ", info='" + info + '\'' +
                ", recipe=" + recipe +
                ", photo='" + photo + '\'' +
                '}';
    }
}
