package com.example.admin.myapplication.snackdecorator;

public class SangriaCocktail implements Cocktail{
    Cocktail cocktail;
    String name;
    float price;

    public SangriaCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
        this.name = "SangriaCocktail";
        this.price = 80000;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
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
