package com.example.admin.myapplication.snackdecorator;

public class SangriaCocktail implements Cocktail{
    Cocktail cocktail;
    String name;
    float price;

    public SangriaCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
        this.name = "Sangria";
        this.price = 80000;
    }

    public String getName() {
        return cocktail.getName() + name;
    }

    public float getPrice() {
        return cocktail.getPrice() + price;
    }

    @Override
    public String mixCocktail() {
        return this.cocktail.mixCocktail() + mixWithFruits() + mixWithBrandy();
    }

    private String mixWithFruits(){
        return "...mixing with Fruits";
    }

    private String mixWithBrandy(){
        return "...mixing with Brandy";
    }
}
