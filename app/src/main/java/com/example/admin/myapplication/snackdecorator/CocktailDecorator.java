package com.example.admin.myapplication.snackdecorator;

public class CocktailDecorator implements Cocktail {
    private Cocktail cocktail;
    @Override
    public String mixCocktail() {
        return cocktail.mixCocktail();
    }
}