package com.example.admin.myapplication.snackdecorator;

public class CocktailDecorator implements Cocktail {
    private Cocktail cocktail;
    @Override
    public String mixCocktail() {
        return cocktail.mixCocktail();
    }

    @Override
    public String getName() {
        return cocktail.getName();
    }

    @Override
    public float getPrice() {
        return cocktail.getPrice();
    }
}