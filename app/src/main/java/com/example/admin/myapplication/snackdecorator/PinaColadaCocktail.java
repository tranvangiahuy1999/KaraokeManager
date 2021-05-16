package com.example.admin.myapplication.snackdecorator;

public class PinaColadaCocktail implements Cocktail{
    Cocktail cocktail;
    public PinaColadaCocktail(Cocktail cocktail){
        this.cocktail = cocktail;
    }

    @Override
    public String mixCocktail() {
        return cocktail.mixCocktail() + mixWithCoconut();
    }

    private String mixWithCoconut(){
        return "...mixing with coconut";
    }
}
