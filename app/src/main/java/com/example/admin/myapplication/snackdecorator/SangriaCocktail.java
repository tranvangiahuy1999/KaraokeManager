package com.example.admin.myapplication.snackdecorator;

public class SangriaCocktail implements Cocktail{
    Cocktail cocktail;
    public SangriaCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
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
