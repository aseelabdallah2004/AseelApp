package com.example.signuplogin;


    public class Recipe {
        private String name;
        private String description;
        private String ingredients;
        private String nutrition;
        private String dietartInfo;
        private String steps;
        private  RecipeType category;
        private String photo;

     public Recipe(){

    }

        public Recipe(String name, String description, String ingredients, String nutrition, String dietartInfo, String steps, RecipeType category, String photo) {
            this.name = name;
            this.description = description;
            this.ingredients = ingredients;
            this.nutrition = nutrition;
            this.dietartInfo = dietartInfo;
            this.steps = steps;
            this.category = category;
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getNutrition() {
            return nutrition;
        }

        public void setNutrition(String nutrition) {
            this.nutrition = nutrition;
        }

        public String getDietartInfo() {
            return dietartInfo;
        }

        public void setDietartInfo(String dietartInfo) {
            this.dietartInfo = dietartInfo;
        }

        public String getSteps() {
            return steps;
        }

        public void setSteps(String steps) {
            this.steps = steps;
        }

        public RecipeType getCategory() {
            return category;
        }

        public void setCategory(RecipeType category) {
            this.category = category;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        @Override
        public String toString() {
            return "Recipe{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", ingredients='" + ingredients + '\'' +
                    ", nutrition='" + nutrition + '\'' +
                    ", dietartInfo='" + dietartInfo + '\'' +
                    ", steps='" + steps + '\'' +
                    ", category=" + category +
                    ", photo='" + photo + '\'' +
                    '}';
        }

    }
