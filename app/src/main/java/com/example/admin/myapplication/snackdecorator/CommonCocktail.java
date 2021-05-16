package com.example.admin.myapplication.snackdecorator;

public class CommonCocktail implements Cocktail {

    @Override
    public String mixCocktail() {
        return "Cocktail";
    }

    @Override
    public String getName() {
        return "Cocktail ";
    }

    @Override
    public float getPrice() {
        return 20000;
    }
}
